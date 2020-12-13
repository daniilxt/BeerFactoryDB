package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import pojo.Worker

class ControllerClient {
    private var worker: Worker? = null

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
    }
}