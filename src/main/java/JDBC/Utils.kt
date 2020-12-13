package JDBC

import JDBC.dao.Role
import JDBC.dao.User
import pojo.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


object Utils {
    @Throws(SQLException::class)
    fun getNewConnection(): Connection? {
        //Class.forName("com.mysql.jdbc.Driver")
        var connection: Connection? = null
        try {
            //val dbURL = "jdbc:mariadb://localhost:3306/bdIlasha"
            val dbURL = "jdbc:mariadb://localhost:3306/testwind"
            val user = "root"
            val password = "root"
            connection = DriverManager.getConnection(dbURL, user, password)
            if (connection.isValid(1)) {
                println("Connection successful")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return connection
    }

    fun getUser(login: String, connection: Connection): User? {

        val sql = "SELECT * FROM User WHERE Login = '$login'"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)
            if (resultSet.next()) {
                return User(
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        Role.valueOf(resultSet.getString("Role").toUpperCase())
                )
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getTasks(idEngineer: Long, connection: Connection): List<Tasks>? {
        val sql = "SELECT t.IdTask, BeerStorage.Name NameBeer, t.IdTechnologicalEngineer, t.Amount,t.Date,t.Status\n" +
                "from Task t " +
                " inner join BeerStorage on BeerStorage.IdBeerKind = t.IdBeerKind\n" +
                "where t.IdTechnologicalEngineer = $idEngineer"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)
            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet<Tasks>(resultSet) {
                    Tasks(
                            resultSet.getLong("IdTask"), resultSet.getLong("IdTechnologicalEngineer"),
                            resultSet.getString("NameBeer"), resultSet.getDate("Date"),
                            resultSet.getLong("Amount"), resultSet.getString("Status")
                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getTask(idTask: Long, connection: Connection): Task? {
        val sql = "select t.IdTask,BeerStorage.IdBeerKind, BeerStorage.Name NameBeer, t.Amount,t.Date\n" +
                "from Task t\n" +
                "         inner join BeerStorage on BeerStorage.IdBeerKind = t.IdBeerKind\n" +
                "where t.IdTask = $idTask"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    Task(
                            resultSet.getLong("IdTask"), resultSet.getLong("IdBeerKind"),
                            resultSet.getString("NameBeer"), resultSet.getLong("Amount"),
                            resultSet.getDate("Date")
                    )
                }?.get(0)
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getRecipe(idBeerKind: Long, connection: Connection): List<Recipe>? {
        val sql = "select ResourceStorage.Name as ResName,\n" +
                "       RecipeList.Amount,\n" +
                "       ResourceStorage.Amount  ResAmount, ResourceStorage.Unit, ResourceStorage.Price ResPrice\n" +
                "from RecipeList\n" +
                "         inner join ResourceStorage on RecipeList.IdResource = ResourceStorage.IdResource\n" +
                "         inner join BeerStorage on RecipeList.IdBeerKind = BeerStorage.IdBeerKind\n" +
                "where BeerStorage.IdBeerKind = ${idBeerKind}\n" +
                "  and BeerStorage.Type != 'Import' "
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    Recipe(
                            resultSet.getString("ResName"), resultSet.getLong("Amount"),
                            resultSet.getLong("ResAmount"), resultSet.getString("Unit"),
                            resultSet.getLong("ResPrice")
                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun countFreeCCT(connection: Connection?): Pair<Long, Long> {
        val sql1 = "select count(c.StatusCCT) from CylindricallyConicalTank c\n" +
                "where c.StatusCCT = 'Free' "
        val sql2 = "select count(c.StatusCCT) from CylindricallyConicalTank c\n"
        try {
            var resultSet = connection!!.createStatement()?.executeQuery(sql1)
            val first = if (resultSet!!.next()) {
                resultSet.getLong(1)
            } else 0
            resultSet = connection.createStatement()?.executeQuery(sql2)
            val second = if (resultSet!!.next()) {
                resultSet.getLong(1)
            } else 0

            return Pair(first, second)
        } catch (ex: SQLException) {
            println(ex)
        }
        return Pair(0, 0)
    }

    fun showCCT(connection: Connection): List<CylindricallyTank>? {
        val sql = "select * from CylindricallyConicalTank"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    CylindricallyTank(
                            resultSet.getLong(1), resultSet.getLong(2), resultSet.getDate(3),
                            resultSet.getDate(4), resultSet.getString(5)
                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getWorkerByLogin(login: String, connection: Connection): Worker? {
        val sql = "select * from Workers WK where WK.Login = '${login}'"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    Worker(
                            resultSet.getLong("ID_WORKER"),
                            resultSet.getString("Name"), resultSet.getString("SecondName"),
                            resultSet.getString("MiddleName"), resultSet.getString("Phone"),
                            resultSet.getDate("DateJoin")
                    )
                }?.get(0)
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun checkRes(resName: String, amount: Long, connection: Connection): Long {
        val sql = "set @num =0;"
        val sql2 = "call validateResources(${amount},'${resName}',@num);"
        val sql3 = "select @num;"
        try {
            connection.createStatement().executeQuery(sql)
            connection.createStatement().executeQuery(sql2)
            val resultSet = connection.createStatement().executeQuery(sql3)
            return if (resultSet!!.next()) {
                resultSet.getLong(1)
            } else 0
        } catch (ex: SQLException) {
            println(ex)
        }
        return 0
    }

    fun getBeerMenu(connection: Connection): List<BeerMenu>? {
        val sql = "select * from BeerMenu"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)
            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    BeerMenu(
                            resultSet.getString("Name"), resultSet.getString("Type"),
                            resultSet.getLong("Amount"), resultSet.getLong("Price")
                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getLoaderTasks(connection: Connection, idLoader: Long): List<LoaderTask>? {
        val sql = "select * from loadertask LT\n" +
                "where LT.IdLoaderMan = ${idLoader}"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    LoaderTask(
                            resultSet.getLong("IdLoaderTask"), resultSet.getLong("IdResBuy"),
                            resultSet.getLong("IdImportAlc"), resultSet.getDate("Date"),
                            resultSet.getString("Status")
                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getAndCheckTask(connection: Connection, idResBuy: Long, idImportAlc: Long): Pair<Long?, Long?>? {
        var sql = "select * from ResBuy RB\n" +
                "where RB.Status != 'system' and RB.IdResBuy = $idResBuy"
        try {
            var pair1: Long? = null
            var resultSet = connection.createStatement().executeQuery(sql)
            if (resultSet.next()) {
                pair1 = resultSet.getLong("IdResBuy")
            }
            sql = "select * from ImportAlcBuy IAB\n" +
                    "where IAB.Status != 'system' and IAB.IdImportAlcBuy = ${idImportAlc}"
            var pair2: Long? = null
            resultSet = connection.createStatement().executeQuery(sql)
            if (resultSet.next()) {
                pair2 = resultSet.getLong("IdImportAlcBuy")
            }
            return Pair(pair1, pair2)

        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getTaskResources(connection: Connection, first: Long): List<TaskResource>? {
        val sql = "select Name, RBP.Number, Date\n" +
                "from ResBuyPosition RBP\n" +
                "         inner join ResBuy RB on RBP.IdResBuy = RB.IdResBuy\n" +
                "         inner join ResourceStorage RS on RBP.IdResource = RS.IdResource\n" +
                "where RB.IdResBuy = ${first}\n"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    TaskResource(
                            resultSet.getString("Name"), resultSet.getLong("Number"),
                            resultSet.getDate("Date")

                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    fun getTaskResourcesAlc(connection: Connection, second: Long): List<TaskResource>? {
        val sql = "select Name, IAB.Number, Date\n" +
                "from ImportAlcBuyPosition IAB\n" +
                "inner join ImportAlcBuy I on IAB.IdImportAlcBuy = I.IdImportAlcBuy\n" +
                "inner join beerstorage b on IAB.IdBeerKind = b.IdBeerKind\n" +
                "where I.IdImportAlcBuy = ${second}"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)

            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet(resultSet) {
                    TaskResource(
                            resultSet.getString("Name"), resultSet.getLong("Number"),
                            resultSet.getDate("Date")

                    )
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return null
    }

    @Throws(SQLException::class)
    private fun <T> getFromResultSet(resultSet: ResultSet, action: () -> T): List<T>? {
        val records: MutableList<T> = ArrayList()
        do {
            val tmp: T = action()
            records.add(tmp)
        } while (resultSet.next())
        return records
    }
}