package pojo

import java.sql.Date

data class LoaderTask(
    var idLoaderTask: Long = 0,
    var idResBuy: Long = 0,
    var idImportAlc: Long = 0,
    var date: Date? = null,
    var status: String? = null
)