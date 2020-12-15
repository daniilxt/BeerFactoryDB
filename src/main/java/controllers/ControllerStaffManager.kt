package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
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
    @FXML private var btn_back_workers: Button? = null
    @FXML private var tab_clients: Tab? = null
    var list: ObservableList<Client> = FXCollections.observableArrayList()

    @FXML
    private fun alert(text: String = "Incorrect input") {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Attention"
        alert.contentText = text
        alert.showAndWait()
    }

    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    private var worker: pojo.Worker? = null
    private var listIndex = mutableListOf<Int>()

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)

        initButtons(connection)
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

        btn_clients?.setOnAction { tab_loader!!.selectionModel!!.select(tab_clients) }
        btn_workers?.setOnAction { tab_loader!!.selectionModel!!.select(tab_workers) }

        btn_back_clients?.setOnAction { tab_loader!!.selectionModel!!.select(tab_manager) }
        btn_back_workers?.setOnAction { tab_loader!!.selectionModel!!.select(tab_manager) }

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