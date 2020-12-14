package controllers;

import JDBC.Utils
import JDBC.dao.Role
import JDBC.dao.User
import encryptor.BaseCoder
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.stage.Stage
import pojo.Client
import pojo.Worker
import java.net.URL
import java.sql.Connection
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
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
    fun onClick(event: ActionEvent?) {
        println("STAGE 000")

    }

    @FXML
    private fun alert(text: String) {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Attention"
        alert.contentText = text
        alert.showAndWait()
    }

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)

        reg_btn?.setOnAction {
            println("STAGE 0")
            registration(connection)
        }
    }

    private fun registration(connection: Connection) {
        if (
                reg_name?.text?.isNotEmpty()!! &&
                reg_conf_password?.text?.isNotEmpty()!! &&
                reg_login?.text?.isNotEmpty()!! &&
                reg_password?.text?.isNotEmpty()!! &&
                reg_phone?.text?.isNotEmpty()!! &&
                reg_middle_name?.text?.isNotEmpty()!! &&
                reg_second_name?.text?.isNotEmpty()!! &&
                reg_date?.value != null
        ) {
            println("STAGE 1")
            if (reg_conf_password!!.text.toString() == reg_password!!.text.toString()) {
                println("STAGE 2")
                val date: java.sql.Date = java.sql.Date.valueOf(reg_date!!.value)
                val nowDate = java.sql.Date(Calendar.getInstance().time.time)

                if (!Utils.checkLogin(connection, reg_login!!.text.toString())) {
                    println("STAGE 3")

                    BaseCoder.encode(reg_password!!.text.toString())?.let {
                        val user = Utils.createAccount(connection, reg_login!!.text.toString(), it, Role.CLIENT)
                        if (user.first) {
                            println("STAGE 4")

                            val client = Utils.createClient(connection,
                                    Client(
                                            reg_name!!.text.toString(), reg_second_name!!.text.toString(),
                                            reg_middle_name!!.text.toString(), reg_phone!!.text.toString(),
                                            date as java.sql.Date?, nowDate, user.second
                                    )
                            )
                            if (client) {
                                println("удаляем логин")
                                Utils.deleteAccount(connection, reg_login!!.text.toString())
                                alert("This user exists")
                            }
                            alert("SUCCESS")
                            moveToScreen()
                        }
                    }
                    return
                }
                alert("This login is exist")
            }
            return
        }
        alert("Input all fields")
    }

    private fun moveToScreen() {
        val loader = FXMLLoader()
        loader.location = javaClass.getResource("../${"MainApplication"}.fxml")
        val root = loader.load<Parent>()
        reg_btn?.scene?.window?.hide()
        val stage = Stage()
        stage.scene = Scene(root)
        stage.show()
    }
}



