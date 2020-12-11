package pojo

import java.sql.Date

class Orders {
    var idOrder: Long = 0
    var idClient: Long = 0
    var idManager: Long = 0
    var date: Date? = null
    var status: String? = null
}