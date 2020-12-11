package JDBC

import JDBC.dao.Role
import JDBC.dao.User
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
        val sql2 = "SELECT * FROM User WHERE Login = '$idEngineer'"
        val sql = "SELECT t.IdTask, BeerStorage.Name NameBeer, t.IdTechnologicalEngineer, t.Amount,t.Date,t.Status\n" +
                "from Task t " +
                " inner join BeerStorage on BeerStorage.IdBeerKind = t.IdBeerKind\n" +
                "where t.IdTechnologicalEngineer = 1"
        try {
            val resultSet = connection.createStatement().executeQuery(sql)
            return if (!resultSet.next()) {
                null
            } else {
                getFromResultSet<Tasks>(resultSet) {
                    Tasks(resultSet.getLong("IdTask"), resultSet.getLong("IdTechnologicalEngineer"),
                            resultSet.getString("NameBeer"), resultSet.getDate("Date"),
                            resultSet.getLong("Amount"), resultSet.getString("Status"))
                }
            }
        } catch (ex: SQLException) {
            println(ex)
        }
        return  null
    }

    @Throws(SQLException::class)
    private fun <T> getFromResultSet(resultSet: ResultSet, action: () -> T): List<T>? {
        val records: MutableList<T> = ArrayList<T>()
        do {
            val tmp: T = action()
            records.add(tmp)
        } while (resultSet.next())
        return records
    }
}