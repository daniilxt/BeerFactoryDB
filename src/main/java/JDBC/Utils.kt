package JDBC

import JDBC.dao.Role
import JDBC.dao.User
import pojo.CylindricallyTank
import pojo.Recipe
import pojo.Task
import pojo.Tasks
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

    fun getTask(idEngineer: Long, connection: Connection): Task? {
        val sql = "select t.IdTask,BeerStorage.IdBeerKind, BeerStorage.Name NameBeer, t.Amount,t.Date\n" +
                "from Task t\n" +
                "         inner join BeerStorage on BeerStorage.IdBeerKind = t.IdBeerKind\n" +
                "where t.IdTechnologicalEngineer = $idEngineer"
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