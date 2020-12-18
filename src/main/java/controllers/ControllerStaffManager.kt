package controllers

import JDBC.Utils
import JDBC.dao.Role
import JDBC.dao.User
import encryptor.BaseCoder
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import javafx.util.Callback
import pojo.Client
import pojo.Worker
import java.sql.Connection
import java.sql.Date
import java.util.*

class ControllerStaffManager {
    @FXML private var btn_clients: Button? = null
    @FXML private var btn_workers: Button? = null
    @FXML private var table_clients: TableView<Client>? = null
    @FXML private var table_clients_second_name: TableColumn<Client, String>? = null
    @FXML private var table_clients_name: TableColumn<Client, String>? = null
    @FXML private var table_clients_middle_name: TableColumn<Client, String>? = null
    @FXML private var table_clients_phone: TableColumn<Client, String>? = null
    @FXML private var table_clients_age: TableColumn<Client, Date>? = null
    @FXML private var table_clients_date_join: TableColumn<Client, Date>? = null
    @FXML private var table_clients_date_jdismiss: TableColumn<Client, Date>? = null
    @FXML private var btn_show_client: Button? = null
    @FXML private var find_client: TextField? = null
    @FXML private var tab_loader: TabPane? = null
    @FXML private var table_workers: TableView<Worker>? = null
    @FXML private var table_workers_name: TableColumn<Worker, String?>? = null
    @FXML private var table_workers_second_name: TableColumn<Worker, String>? = null
    @FXML private var table_workers_middle_name: TableColumn<Worker, String>? = null
    @FXML private var table_workers_phone: TableColumn<Worker, String>? = null
    @FXML private var table_workers_age: TableColumn<Worker, Long>? = null
    @FXML private var table_workers_date_join: TableColumn<Worker, Date>? = null
    @FXML private var table_workers_date_dismiss: TableColumn<Worker, Date>? = null
    @FXML private var find_worker: TextField? = null
    @FXML private var tab_workers: Tab? = null
    @FXML private var btn_clients_clear: Button? = null
    @FXML private var btn_workers_show: Button? = null
    @FXML private var btn_workers_clear: Button? = null
    @FXML private var tab_manager: Tab? = null
    @FXML private var btn_back_clients: Button? = null
    @FXML private var btn_exit: Button? = null
    @FXML private var btn_back_workers: Button? = null
    @FXML private var btn_back_create: Button? = null
    @FXML private var tab_clients: Tab? = null

    @FXML private var reg_date: DatePicker? = null
    @FXML private var reg_name: TextField? = null
    @FXML private var reg_second_name: TextField? = null
    @FXML private var reg_middle_name: TextField? = null
    @FXML private var reg_phone: TextField? = null
    @FXML private var reg_btn: Button? = null
    @FXML private var reg_password: PasswordField? = null
    @FXML private var reg_login: TextField? = null
    @FXML private var switch_role: ComboBox<String>? = null
    @FXML private var reg_password_conf: PasswordField? = null
    @FXML private var btn_reg: Button? = null
    @FXML private var btn_restore: Button? = null

    var list: ObservableList<Client> = FXCollections.observableArrayList()

    @FXML
    private fun alert(text: String = "Incorrect input",type:Alert.AlertType = Alert.AlertType.ERROR) {
        val alert = Alert(type)
        alert.title = "Attention"
        alert.contentText = text
        alert.showAndWait()
    }

    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    private var worker: Worker? = null
    private var listIndex = mutableListOf<Int>()

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
        switch_role?.items?.addAll("client", "barman", "manager", "staff_manager", "manager", "engineer","loader")

        initButtons(connection)
        btn_reg?.setOnAction { registration(connection) }

        Utils.getClientsList(connection)?.let { list.addAll(it) }
        table_clients_second_name?.cellValueFactory = PropertyValueFactory("secondNameClient")
        table_clients_name?.cellValueFactory = PropertyValueFactory("nameClient")
        table_clients_middle_name?.cellValueFactory = PropertyValueFactory("middleNameClient")
        table_clients_phone?.cellValueFactory = PropertyValueFactory("phoneClient")
        table_clients_age?.cellValueFactory = PropertyValueFactory("age")
        table_clients_date_join?.cellValueFactory = PropertyValueFactory("dateJoin")
        table_clients_date_jdismiss?.cellValueFactory = PropertyValueFactory("dateDismiss")
        table_clients?.columns?.add(addButtonColumn("Action", "delete") {
            println(it)
            dismissClient(connection, it)
            //todo
        })
        table_clients?.items?.clear()
        table_clients?.items = list


        // table workers
        val dataWorkers = mutableListOf<Worker>()
        Utils.getWorkers(connection)?.let { dataWorkers.addAll(it) }
        table_workers?.items?.clear()
        table_workers?.items?.addAll(dataWorkers)

        table_workers_second_name?.cellValueFactory = PropertyValueFactory("name")
        table_workers_name?.cellValueFactory = PropertyValueFactory("secondName")
        table_workers_middle_name?.cellValueFactory = PropertyValueFactory("middleName")
        table_workers_phone?.cellValueFactory = PropertyValueFactory("phone")
        table_workers_age?.cellValueFactory = PropertyValueFactory("worksDays")
        table_workers_date_join?.cellValueFactory = PropertyValueFactory("dateJoin")
        table_workers_date_dismiss?.cellValueFactory = PropertyValueFactory("dateDismiss")
        table_workers?.columns?.add(addButtonColumn("Action", "delete") {
            println(it)
            dismissWorker(connection, it)
            //todo
        })
    }
    private fun registration(connection: Connection) {
        if (
                reg_name?.text?.isNotEmpty()!! &&
                reg_password_conf?.text?.isNotEmpty()!! &&
                reg_login?.text?.isNotEmpty()!! &&
                reg_password?.text?.isNotEmpty()!! &&
                reg_phone?.text?.isNotEmpty()!! &&
                reg_middle_name?.text?.isNotEmpty()!! &&
                reg_second_name?.text?.isNotEmpty()!! &&
                reg_date?.value != null
        ) {
            println("STAGE 1")
            if (reg_password_conf!!.text.toString().trim() == reg_password!!.text.toString().trim()) {
                println("STAGE 2")
                val date: Date = Date.valueOf(reg_date!!.value)
                val nowDate = Date(Calendar.getInstance().time.time)

                if (!Utils.checkLogin(connection, reg_login!!.text.toString())  && !Utils.checkUserExists(connection, reg_phone!!.text.toString().trim())) {
                    println("STAGE 3")

                    val role = Role.valueOf(switch_role!!.value.toUpperCase())
                    BaseCoder.encode(reg_password!!.text.toString().trim())?.let {
                        val user = Utils.createAccount(connection, reg_login!!.text.toString().trim(), it, role)
                        if (user.first) {
                            println("STAGE 4")

                            val client = Utils.createHuman(connection,
                                    Client(
                                            reg_name!!.text.toString().trim(), reg_second_name!!.text.toString().trim(),
                                            reg_middle_name!!.text.toString().trim(), reg_phone!!.text.toString().trim(),
                                            date as Date?, nowDate,  idUser= user.second
                                    ),role
                            )
                            if (client) {
                                println("удаляем логин")
                                //Utils.deleteAccount(connection, reg_login!!.text.toString().trim())
                                alert("This user exists")
                                return
                            }
                            alert("SUCCESS",Alert.AlertType.INFORMATION)
                            //moveToScreen()
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

    private fun initButtons(connection: Connection) {
        btn_show_client?.setOnAction {
            if (find_client!!.text.isNotEmpty()) {
                val tmpArray = mutableListOf<Client>()
                table_clients?.items?.forEach {
                    val str = it.nameClient?.toLowerCase() + it.secondNameClient?.toLowerCase() + it?.middleNameClient?.toLowerCase()
                    if (str.contains(find_client!!.text.toLowerCase())) {
                        tmpArray.add(it)
                    }
                }
                table_clients?.items?.clear()
                table_clients?.items?.addAll(tmpArray)
            } else {
                alert()
            }
        }
        btn_clients_clear?.setOnAction {
            table_clients?.items?.clear()
            Utils.getClientsList(connection)?.let { table_clients?.items?.addAll(it) }
        }
        btn_workers_show?.setOnAction {
            if (find_worker!!.text.isNotEmpty()) {
                val tmpArray = mutableListOf<Worker>()
                table_workers?.items?.forEach {
                    val str = it.name?.toLowerCase() + it.secondName?.toLowerCase() + it?.middleName?.toLowerCase()
                    if (str.contains(find_worker!!.text.toLowerCase())) {
                        tmpArray.add(it)
                    }
                }
                table_workers?.items?.clear()
                table_workers?.items?.addAll(tmpArray)
            } else {
                alert()
            }
        }
        btn_workers_clear?.setOnAction {
            table_workers?.items?.clear()
            Utils.getWorkers(connection)?.let { table_workers?.items?.addAll(it) }
        }
        btn_exit?.setOnAction {
            val loader = FXMLLoader()
            loader.location = javaClass.getResource("../${"MainApplication"}.fxml")
            val root = loader.load<Parent>()
            btn_exit?.scene?.window?.hide()
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()
        }

        btn_clients?.setOnAction { tab_loader!!.selectionModel!!.select(tab_clients) }
        btn_workers?.setOnAction { tab_loader!!.selectionModel!!.select(tab_workers) }

        btn_back_clients?.setOnAction { tab_loader!!.selectionModel!!.select(tab_manager) }
        btn_back_workers?.setOnAction { tab_loader!!.selectionModel!!.select(tab_manager) }
        btn_back_create?.setOnAction { tab_loader!!.selectionModel!!.select(tab_manager) }

        btn_restore?.setOnAction {
            val dialog: Dialog<Pair<String, String>> = Dialog()
            dialog.title = "Restore password"

            val loginButtonType = ButtonType("OK", ButtonBar.ButtonData.OK_DONE)
            dialog.dialogPane.buttonTypes.addAll(loginButtonType, ButtonType.CANCEL)

            val gridPane = GridPane()
            gridPane.hgap = 20.0
            gridPane.vgap = 20.0
            gridPane.padding = Insets(20.0, 150.0, 10.0, 10.0)

            val from = TextField()
            from.promptText = "Login"
            val to = PasswordField()
            to.promptText = "Enter password"
            val toConf = PasswordField()
            toConf.promptText = "Confirm password"
            gridPane.add(from, 0, 0)
            gridPane.add(Label("To:"), 1, 0)
            gridPane.add(to, 2, 0)
            gridPane.add(Label("To confirm:"), 1, 1)
            gridPane.add(toConf, 2, 1)
            dialog.dialogPane.content = gridPane
            // Request focus on the username field by default.
            Platform.runLater { from.requestFocus() }

            // Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter { dialogButton ->
                if (dialogButton === loginButtonType) {
                    return@setResultConverter Pair(from.text, to.text)
                }
                null
            }
            val result = dialog.showAndWait()
            result.ifPresent { pair: Pair<String?, String?> ->
                if (to.text == toConf.text) {
                    changePassword(connection, login = from.text.toString().trim(), newPassword = to.text.toString().trim())
                } else {
                    alert("Passwords don't match")
                }
            }
        }
    }

    private fun changePassword(connection: Connection, login: String, newPassword: String) {
        val tmp = Utils.checkLogin(connection, login)
        if (!tmp) {
            alert("Login not exist")
        } else {
            when (BaseCoder.encode(newPassword)?.let { Utils.updatePasswordByLogin(connection, login, it) }) {
                true -> alert("SUCCESS", Alert.AlertType.INFORMATION)
                else -> alert("ERROR")
            }
        }
    }

    private fun dismissWorker(connection: Connection, it: Worker) {
        it.login?.let { it1 -> Utils.deleteAccount(connection, it1) }
        table_workers?.items?.clear()
        Utils.getWorkers(connection)?.let { table_workers?.items?.addAll(it) }
    }

    private fun dismissClient(connection: Connection, it: Client) {
        table_clients?.items?.clear()
        val date = Date(Calendar.getInstance().time.time)
        Utils.deleteClient(connection, it.idClient, it.idUser, date)
        val tmpData = Utils.getClientsList(connection) as MutableList<Client>
        list.addAll(tmpData)
        table_clients?.items = list
    }

    private fun <T> addButtonColumn(columnName: String, btnName: String, func: (it: T) -> Unit): TableColumn<T, Void> {
        val colBtn: TableColumn<T, Void> = TableColumn(columnName)
        val cellFactory: Callback<TableColumn<T?, Void?>?, TableCell<T?, Void?>?> =
                Callback {
                    object : TableCell<T?, Void?>() {
                        private val btn = Button(btnName)
                        override fun updateItem(item: Void?, empty: Boolean) {
                            super.updateItem(item, empty)
                            btn.setOnAction {
                                val type: T? = tableView.items[index]
                                type?.let { it1 -> func(it1) }
                                //btn.isVisible = false
                            }
                            graphic = if (empty) {
                                null
                            } else {
                                btn
                            }
                        }
                    }
                }

        colBtn.cellFactory = cellFactory
        return colBtn
    }

}