package pojo

import java.sql.Date

data class Tasks(
    var idTask: Long = 0,
    var idTechnologicalEngineer: Long = 0,
    var beerName:String? = null,
    var date: Date? = null,
    var amount: Long = 0,
    var status: String? = null
)