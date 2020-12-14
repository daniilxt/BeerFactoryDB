package pojo

import java.sql.Date

data class Client(
        var nameClient: String? = null,
        var secondNameClient: String? = null,
        var middleNameClient: String? = null,
        var phoneClient: String? = null,
        var age: Date? = null,
        var dateJoin: Date? = null,
        var idUser: Long = 0,
        var idClient:Long = -1
)