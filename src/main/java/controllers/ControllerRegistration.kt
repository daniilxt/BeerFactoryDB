package controllers;

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import pojo.Worker
import java.net.URL
import java.util.*


class ControllerRegistration {
    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var reg_date: DatePicker? = null

    @FXML
    private var reg_name: TextField? = null

    @FXML
    private var reg_second_name: TextField? = null

    @FXML
    private var reg_middle_name: TextField? = null

    @FXML
    private var reg_phone: TextField? = null

    @FXML
    private var reg_btn: Button? = null

    @FXML
    private var reg_password: PasswordField? = null

    @FXML
    private var reg_login: TextField? = null

    @FXML
    private var reg_conf_password: PasswordField? = null
    private var worker: Worker? = null

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
    }
}


