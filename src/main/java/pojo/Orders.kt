package pojo

import java.sql.Date

data class Orders(
    var idOrder: Long = 0,
    var manager: Long = 0,
    var date: Date? = null,
    var status: String? = null
)