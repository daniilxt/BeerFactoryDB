package pojo

import java.sql.Date

data class Task(
    var idTask: Long = 0,
    var idBeerKind: Long = 0,
    var beerName: String? = null,
    var amount: Long = 0,
    var date: Date? = null
)