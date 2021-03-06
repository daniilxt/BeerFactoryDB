package controllers

import JDBC.Utils
import JDBC.dao.Role
import JDBC.dao.User
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import javafx.util.Callback
import pojo.*
import java.sql.Connection
import java.sql.Date
import java.util.*
import kotlin.math.abs


class ControllerManager {
    @FXML private var tab_loader: TabPane? = null
    @FXML private var table_tasks: TableView<LoaderTask>? = null
    @FXML private var table_tasks_number: TableColumn<Long, Long>? = null
    @FXML private var table_tasks_id_res: TableColumn<LoaderTask, Long>? = null
    @FXML private var table_tasks_id_alc: TableColumn<LoaderTask, Long>? = null
    @FXML private var table_tasks_date: TableColumn<LoaderTask, Date>? = null
    @FXML private var table_tasks_status: TableColumn<LoaderTask, String>? = null
    @FXML private var btn_buy: Button? = null
    @FXML private var btn_buy1: Button? = null
    @FXML private var btn_buy2: Button? = null
    @FXML private var tab_res: Tab? = null
    @FXML private var tab_manager: Tab? = null
    @FXML private var tab_task: Tab? = null
    @FXML private var filter_date: Button? = null
    @FXML private var table_cart: TableView<OrderPosition>? = null
    @FXML private var table_cart_name: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_amount: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_cart_unit: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_price: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_res_list: TableView<Orders>? = null
    @FXML private var table_res_list_id: TableColumn<Orders, Long>? = null
    @FXML private var table_res_list_manager: TableColumn<Orders, Long>? = null
    @FXML private var table_res_list_date: TableColumn<Orders, Date>? = null
    @FXML private var table_res_list_status: TableColumn<Orders, String>? = null
    @FXML private var tab_alc: Tab? = null
    @FXML private var btn_create_alc_task: Button? = null
    @FXML private var btn_show_alc: Button? = null
    @FXML private var feld_show_alc: TextField? = null
    @FXML private var btn_create_res_task: Button? = null
    @FXML private var btn_create_give: Button? = null
    @FXML private var btn_show_res: Button? = null
    @FXML private var btn_exit: Button? = null
    @FXML private var btn_back_res: Button? = null
    @FXML private var btn_back_alc: Button? = null
    @FXML private var btn_back_task: Button? = null
    @FXML private var feld_show_res: TextField? = null
    @FXML private var table_cart1: TableView<OrderPosition>? = null
    @FXML private var table_cart_name1: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_amount1: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_cart_unit1: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_price1: TableColumn<OrderPosition, Long>? = null

    @FXML private var table_lc_list: TableView<Orders>? = null
    @FXML private var table_lc_list_name: TableColumn<Orders, Long>? = null
    @FXML private var table_lc_list_manager: TableColumn<Orders, Long>? = null
    @FXML private var table_lc_list_date: TableColumn<Orders, Date>? = null
    @FXML private var table_lc_list_status: TableColumn<Orders, String>? = null

    @FXML private var table_lc_list1: TableView<Orders>? = null
    @FXML private var table_lc_list_name1: TableColumn<Orders, Long>? = null
    @FXML private var table_lc_list_manager1: TableColumn<Orders, Long>? = null
    @FXML private var table_lc_list_date1: TableColumn<Orders, Date>? = null
    @FXML private var table_lc_list_status1: TableColumn<Orders, String>? = null

    @FXML private var table_cart11: TableView<OrderPosition>? = null
    @FXML private var table_cart_name11: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_amount11: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_cart_unit11: TableColumn<OrderPosition, String>? = null
    @FXML private var table_cart_price11: TableColumn<OrderPosition, Long>? = null
    @FXML private var switch_engineer:ComboBox<String>? = null

    private var worker: Worker? = null
    val dataResAlc = mutableListOf<OrderPosition>()
    val dataRes = mutableListOf<OrderPosition>()
    var currentAlcoTask: Orders? = null
    var currentResTask: Orders? = null
    var currentClientTask: Orders? = null


    @FXML
    fun findIdTask(event: ActionEvent?) {
    }
    @FXML
    private fun alert(text: String = "Incorrect input", type: Alert.AlertType = Alert.AlertType.ERROR) {
        val alert = Alert(type)
        alert.title = "Attention"
        alert.contentText = text
        alert.showAndWait()
    }
    @FXML
    fun alertConfirm(text: String = "", arr: List<OrderPosition>, connection: Connection) {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Create"
        alert.headerText = "Are you want to create task to engineer?"
        alert.contentText = text

        // option != null.
        val option = alert.showAndWait()
        when {
            option.get() == ButtonType.OK -> {
                println("OK")
                createRequest(arr, connection)
            }
            option.get() == ButtonType.CANCEL -> {
                println("CANCELLED")
            }
            else -> {
                println("??")
            }
        }
    }
    private fun createRequest(arr: List<OrderPosition>, connection: Connection) {
        val nowDate = Date(Calendar.getInstance().time.time)
        val indexHash = switch_engineer?.value?.indexOf('#')
        val engineerId = switch_engineer?.value?.substring(indexHash!! + 1)
        if (engineerId != null) {
            Utils.createEngineerTask(connection, engineerId, arr, nowDate)
            table_lc_list1?.items?.remove(currentClientTask)
            alert("Request sent. Wait some time.", Alert.AlertType.INFORMATION)
        } else {
            alert("Pick engineer!")
        }
    }

    @FXML
    fun initialize(user: User) {
        var connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
        val engineer = Utils.getEngineers(connection)
        engineer?.map { "${it.name} ${it.secondName} #${it.idWorker}" }?.toList()?.let {
            switch_engineer
                    ?.items?.addAll(it)
        }

        initTables(connection)
        initButtons(connection)
        Utils.getLoaderTasksViaManager(connection,worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }
    }

    private fun initButtons(connection: Connection) {
        btn_create_alc_task?.setOnAction {
            if (table_cart1?.items?.isNotEmpty()!! && currentAlcoTask != null) {
                // curr.manager this is barman
                var loader = 1
                val nowDate = Date(Calendar.getInstance().time.time)
                Utils.createLoaderAlcoTask(connection, worker!!.idWorker, currentAlcoTask!!.manager, table_cart1!!.items, loader, nowDate, currentAlcoTask!!.idOrder)
                table_cart1?.items?.clear()
                table_lc_list?.items?.remove(currentAlcoTask)
                table_tasks?.items?.clear()
               Utils.getLoaderTasksViaManager(connection,worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }
            }
        }

        btn_create_res_task?.setOnAction {
            if (table_cart?.items?.isNotEmpty()!! && currentResTask != null) {
                // curr.manager this is engineer
                //TODO OOOOOOOOOOO
                var loader = 1
                val nowDate = Date(Calendar.getInstance().time.time)
                Utils.createLoaderResTask(connection, worker!!.idWorker, currentResTask!!.manager, table_cart!!.items, loader, nowDate, currentResTask!!.idOrder)
                table_cart?.items?.clear()
                table_res_list?.items?.remove(currentResTask)
                table_tasks?.items?.clear()
                Utils.getLoaderTasksViaManager(connection, worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }

            }
        }
        btn_create_give?.setOnAction {
            if (table_cart11?.items?.isNotEmpty()!! && currentClientTask != null) {
                val arrBuy = mutableListOf<OrderPosition>()
                var bigStr = ""
                table_cart11!!.items.forEachIndexed { index, it1 ->
                    run {
                        val tmp = Utils.checkBeer(it1.beerName!!, it1.amount, connection)
                        if (tmp < 0) {
                            val tmpElement = table_cart11!!.items[index].copy(amount = abs(tmp))
                            arrBuy.add(tmpElement)
                            bigStr += "${it1.beerName} ${abs(tmp)}\n "
                        }
                    }
                }
                if (arrBuy.isNotEmpty()) {
                    val basic = arrBuy.filter { it.type != "Import" }
                    val import = arrBuy.filter { it.type == "Import" }
                    println("import ${import}")
                    println("not import ${basic}")
                    if (basic.isNotEmpty()) {
                        alertConfirm(bigStr, basic, connection)
                    } else if (import.isNotEmpty()) {
                        //task
                        println("create alco task")
                        var loader = 1
                        var barman = 1L
                        val nowDate = Date(Calendar.getInstance().time.time)
                        Utils.createLoaderAlcoTask(connection, worker!!.idWorker, barman, import, loader, nowDate, currentClientTask!!.idOrder)
                        table_cart11?.items?.clear()
                        table_lc_list1?.items?.remove(currentClientTask)
                        table_tasks?.items?.clear()
                        Utils.getLoaderTasksViaManager(connection, worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }
                    }
                } else {
                    if (Utils.completeOrder(connection, currentClientTask!!, table_cart11?.items!!)) {
                        alert("Order success", Alert.AlertType.INFORMATION)
                        table_cart11?.items?.clear()
                    }
                }
            } else {
                alert("List is empty")
            }
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

        btn_back_res?.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_manager)
        }
        btn_back_alc?.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_manager)
        }
        btn_back_task?.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_manager)
        }
        btn_buy?.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_res)
        }
        btn_buy2?.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_alc)
        }
    }

    private fun initTables(connection: Connection) {

        val data = mutableListOf<LoaderTask>()

        table_tasks?.items?.clear()
        Utils.getLoaderTasks(connection, worker!!.idWorker)?.let { data.addAll(it) }
        table_tasks?.items?.addAll(data)

        // created tasks table
        table_tasks_number?.cellValueFactory = PropertyValueFactory("idLoaderTask")
        table_tasks_id_res?.cellValueFactory = PropertyValueFactory("idResBuy")
        table_tasks_id_alc?.cellValueFactory = PropertyValueFactory("idImportAlc")
        table_tasks_date?.cellValueFactory = PropertyValueFactory("date")
        table_tasks_status?.cellValueFactory = PropertyValueFactory("status")

        // tasks via barman

        Utils.getBarmanAlcOrders(connection, worker!!.idWorker)?.let { table_lc_list?.items?.addAll(it) }
        table_lc_list_name?.cellValueFactory = PropertyValueFactory("idOrder")
        table_lc_list_manager?.cellValueFactory = PropertyValueFactory("manager")
        table_lc_list_date?.cellValueFactory = PropertyValueFactory("date")
        table_lc_list_status?.cellValueFactory = PropertyValueFactory("status")
        table_lc_list?.columns?.add(addButtonColumn("Action", "handle") {
            table_cart1?.items?.clear()
            currentAlcoTask = it
            createTasksResAlc(dataResAlc, connection, it)
            //todo
        })
        //position of task barman
        table_cart_name1?.cellValueFactory = PropertyValueFactory("beerName")
        table_cart_amount1?.cellValueFactory = PropertyValueFactory("amount")
        table_cart_unit1?.cellValueFactory = PropertyValueFactory("type")
        table_cart_price1?.cellValueFactory = PropertyValueFactory("price")


        // tasks via engineer
        Utils.getEngineerResOrders(connection, worker!!.idWorker)?.let { table_res_list?.items?.addAll(it) }
        table_res_list_id?.cellValueFactory = PropertyValueFactory("idOrder")
        table_res_list_manager?.cellValueFactory = PropertyValueFactory("manager")
        table_res_list_date?.cellValueFactory = PropertyValueFactory("date")
        table_res_list_status?.cellValueFactory = PropertyValueFactory("status")
        table_res_list?.columns?.add(addButtonColumn("Action", "handle") {
            table_cart?.items?.clear()
            currentResTask = it
            createTasksRes(dataResAlc, connection, it)
            //todo
        })

        //position of task client
        table_cart_name11?.cellValueFactory = PropertyValueFactory("beerName") // this is res name
        table_cart_amount11?.cellValueFactory = PropertyValueFactory("amount")
        table_cart_unit11?.cellValueFactory = PropertyValueFactory("type")
        table_cart_price11?.cellValueFactory = PropertyValueFactory("price")


        Utils.getClientOrders(connection, worker!!.idWorker)?.let { table_lc_list1?.items?.addAll(it) }
        table_lc_list_name1?.cellValueFactory = PropertyValueFactory("idOrder")
        table_lc_list_manager1?.cellValueFactory = PropertyValueFactory("manager")
        table_lc_list_date1?.cellValueFactory = PropertyValueFactory("date")
        table_lc_list_status1?.cellValueFactory = PropertyValueFactory("status")
        table_lc_list1?.columns?.add(addButtonColumn("Action", "handle") {
            table_cart11?.items?.clear()
            currentClientTask = it
            createTasksClient(dataResAlc, connection, it)
            //todo
        })

        //position of task engineer
        table_cart_name?.cellValueFactory = PropertyValueFactory("beerName") // this is res name
        table_cart_amount?.cellValueFactory = PropertyValueFactory("amount")
        table_cart_unit?.cellValueFactory = PropertyValueFactory("type")
        table_cart_price?.cellValueFactory = PropertyValueFactory("price")

    }

    private fun createTasksClient(dataResAlc: MutableList<OrderPosition>, connection: Connection, it: Orders?) {
        table_cart11?.items?.clear()

        Utils.getClientOrdersPosition(connection, it!!.idOrder)?.let { it1 -> table_cart11?.items?.addAll(it1) }
    }

    private fun createTasksRes(data: MutableList<OrderPosition>, connection: Connection, it: Orders?) {
        table_cart?.items?.clear()

        Utils.getEngineerResOrdersPosition(connection, it!!.idOrder)?.let { it1 ->
            table_cart?.items?.addAll(it1)
        }
    }

    private fun createTasksResAlc(dataResAlc: MutableList<OrderPosition>, connection: Connection, it: Orders?) {
        table_cart1?.items?.clear()

        Utils.getBarmanAlcOrdersPosition(connection, it!!.idOrder)?.let { it1 -> table_cart1?.items?.addAll(it1) }
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