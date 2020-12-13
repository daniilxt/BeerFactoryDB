package controllers

import JDBC.Utils
import JDBC.dao.Role
import JDBC.dao.User
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
        //nextScreen(Role.ENGINEER) //todo del
        if (!auth_login?.text.isNullOrEmpty() && !auth_password?.text.isNullOrEmpty()) {
            println("Success")
            val connection = Utils.getNewConnection()
            val user = connection?.let { Utils.getUser(auth_login?.text.toString(), it) }
            if (user != null) {
                if (validateUser(user.password, auth_password!!.text.toString())) {
                    println("Password is Valid")
                    nextScreen(user.role)
                    return
                }
            }
            alert()
        } else {
            alert()
        }
    }

    @FXML
    fun onRegister() {
        moveToScreen("Registration")
    }

    private fun nextScreen(role: Role) {
        try {
            // auth_btn_sign?.scene?.window?.hide()
            val path = when (role) {
                Role.ENGINEER -> "Factory"
                Role.BARMAN -> "Bar"
                Role.LOADER -> "LoaderMan"
                Role.MANAGER -> "Manager"
                Role.STAFF_MANAGER -> "StaffManager"
                Role.CLIENT -> "Client"
                Role.ADMIN -> "LoaderMan"
            }
            moveToScreen(path, role)
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

    private fun moveToScreen(name: String, role: Role = Role.CLIENT) {
/*        val root: Parent = FXMLLoader.load(javaClass.getResource("../${name}.fxml"))
        val window: Stage = auth_btn_sign?.scene?.window as Stage
        window.scene = Scene(root)
        window.show()*/

        val loader = FXMLLoader()
        loader.location = javaClass.getResource("../${name}.fxml")
        val root = loader.load<Parent>()
        if (name == "Factory") {
            val contr: ControllerFactory = loader.getController()
            contr.initialize(User(auth_login!!.text.toString(), auth_password!!.text.toString(), Role.ENGINEER))
        }
        if (name == "LoaderMan") {
            val contr: ControllerLoaderMan = loader.getController()
            contr.initialize(User(auth_login!!.text.toString(), auth_password!!.text.toString(), Role.LOADER))
        }
        auth_btn_sign?.scene?.window?.hide()
        val stage = Stage()
        stage.scene = Scene(root)
        stage.show()
    }
}

