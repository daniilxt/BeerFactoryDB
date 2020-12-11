package pojo

import java.sql.Date

data class CylindricallyTank(
    var idCCT: Long = 0,
    var idTask: Long = 0,
    var dateStart: Date? = null,
    var dateEnd: Date? = null,
    var statusCCT: String? = null
)