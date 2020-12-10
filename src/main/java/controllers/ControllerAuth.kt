package controllers

import JDBC.Utils
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

class ControllerAuth {

    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var auth_login: TextField? = null

    @FXML
    private var auth_password: PasswordField? = null

    @FXML
    private var auth_btn_reg: Button? = null

    @FXML
    private var auth_btn_sign: Button? = null

    @FXML
    private fun alert() {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Attention"
        alert.contentText = "Incorrect input"
        alert.showAndWait()
    }


    @FXML
    fun onSignIn() {
        if (!auth_login?.text.isNullOrEmpty() && !auth_password?.text.isNullOrEmpty()) {
            println("Success")
            val connection = Utils.getNewConnection()
           println(Utils.getUser("w_eng2", connection!!))
        } else {
            alert()
        }
    }

    @FXML
    fun initialize() {

    }
}

