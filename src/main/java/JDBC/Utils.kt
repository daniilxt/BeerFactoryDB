package JDBC

import JDBC.dao.Role
import JDBC.dao.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Utils {
    @Throws(SQLException::class)
    fun getNewConnection(): Connection? {
        //Class.forName("com.mysql.jdbc.Driver")
        val dbURL = "jdbc:mariadb://localhost:3306/bdIlasha"
        val user = "root"
        val password = "root"
        val connection = DriverManager.getConnection(dbURL, user, password)
        if (connection.isValid(1)) {
            println("Connection successful")
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
}