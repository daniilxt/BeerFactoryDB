package controllers

import JDBC.Utils
import JDBC.dao.Role
import encryptor.BaseCoder
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.net.URL
import java.util.*


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
            val user = Utils.getUser(auth_login?.text.toString(), connection!!)
            if (user != null) {
                if (validateUser(user.password, auth_password!!.text.toString())) {
                    println("Password is Valid")
                    nextScreen(user.role)
                }
            }
        } else {
            alert()
        }
    }

    private fun nextScreen(role: Role) {
        try {
            // auth_btn_sign?.scene?.window?.hide()
            val path = when (role) {
                Role.ENGINEER -> {
                    "Factory"
                }
                else -> {
                    "MainApplication"
                }
            }
            val root: Parent = FXMLLoader.load(javaClass.getResource("../${path}.fxml"))
            val window: Stage = auth_btn_sign?.scene?.window as Stage
            window.scene = Scene(root)
            window.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun validateUser(password: String, inputPwd: String): Boolean {
        return password == BaseCoder.encode(inputPwd)
    }

    @FXML
    fun initialize() {

    }
}

