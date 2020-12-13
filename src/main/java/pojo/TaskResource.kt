package pojo

import java.sql.Date

data class TaskResource(
        var idTask: Long = 0,
        var resName: String? = null,
        var amount: Long = 0,
        var date: Date? = null
)