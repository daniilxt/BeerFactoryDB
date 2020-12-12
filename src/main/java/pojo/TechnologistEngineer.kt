package pojo

import java.sql.Date

data class TechnologistEngineer(
    var idTechnologistEngineer: Long = 0,
    var name: String? = null,
    var secondName: String? = null,
    var middleName: String? = null,
    var phone: String? = null,
    var dateJoin: Date? = null,
    var dateDismiss: Date? = null)