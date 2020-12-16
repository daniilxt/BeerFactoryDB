package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import pojo.*
import java.sql.Connection
import java.sql.Date
import java.util.*


class ControllerManager {
    @FXML
    private var tab_loader: TabPane? = null

    @FXML
    private var table_tasks: TableView<LoaderTask>? = null

    @FXML
    private var table_tasks_number: TableColumn<Long, Long>? = null

    @FXML
    private var table_tasks_id_res: TableColumn<LoaderTask, Long>? = null

    @FXML
    private var table_tasks_id_alc: TableColumn<LoaderTask, Long>? = null

    @FXML
    private var table_tasks_date: TableColumn<LoaderTask, Date>? = null

    @FXML
    private var table_tasks_status: TableColumn<LoaderTask, String>? = null

    @FXML
    private var btn_buy: Button? = null

    @FXML
    private var btn_buy1: Button? = null

    @FXML
    private var btn_buy2: Button? = null

    @FXML
    private var tab_res: Tab? = null

    @FXML
    private var filter_date: Button? = null

    @FXML
    private var table_cart: TableView<OrderPosition>? = null

    @FXML
    private var table_cart_name: TableColumn<OrderPosition, String>? = null

    @FXML
    private var table_cart_amount: TableColumn<OrderPosition, Long>? = null

    @FXML
    private var table_cart_unit: TableColumn<OrderPosition, String>? = null

    @FXML
    private var table_cart_price: TableColumn<OrderPosition, Long>? = null

    @FXML
    private var table_res_list: TableView<Orders>? = null

    @FXML
    private var table_res_list_id: TableColumn<Orders, Long>? = null

    @FXML
    private var table_res_list_manager: TableColumn<Orders, Long>? = null

    @FXML
    private var table_res_list_date: TableColumn<Orders, Date>? = null

    @FXML
    private var table_res_list_status: TableColumn<Orders, String>? = null

    @FXML
    private var tab_alc: Tab? = null

    @FXML
    private var btn_create_alc_task: Button? = null

    @FXML
    private var btn_show_alc: Button? = null

    @FXML
    private var feld_show_alc: TextField? = null

    @FXML
    private var btn_create_res_task: Button? = null

    @FXML
    private var btn_show_res: Button? = null

    @FXML
    private var feld_show_res: TextField? = null

    @FXML
    private var table_cart1: TableView<OrderPosition>? = null

    @FXML
    private var table_cart_name1: TableColumn<OrderPosition, String>? = null

    @FXML
    private var table_cart_amount1: TableColumn<OrderPosition, Long>? = null

    @FXML
    private var table_cart_unit1: TableColumn<OrderPosition, String>? = null

    @FXML
    private var table_cart_price1: TableColumn<OrderPosition, Long>? = null

    @FXML
    private var table_lc_list: TableView<Orders>? = null

    @FXML
    private var table_lc_list_name: TableColumn<Orders, Long>? = null

    @FXML
    private var table_lc_list_manager: TableColumn<Orders, Long>? = null

    @FXML
    private var table_lc_list_date: TableColumn<Orders, Date>? = null

    @FXML
    private var table_lc_list_status: TableColumn<Orders, String>? = null
    private var worker: Worker? = null
    val dataResAlc = mutableListOf<OrderPosition>()
    val dataRes = mutableListOf<OrderPosition>()
    var currentAlcoTask: Orders? = null
    var currentResTask: Orders? = null


    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    @FXML
    fun initialize(user: User) {
        var connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)

        initTables(connection)
        initButtons(connection)
    }

    private fun initButtons(connection: Connection) {
        btn_create_alc_task?.setOnAction {
            if (table_cart1?.items?.isNotEmpty()!! && currentAlcoTask != null) {
                // curr.manager this is barman
                var loader = 1
                val nowDate = Date(Calendar.getInstance().time.time)
                Utils.createLoaderAlcoTask(connection, worker!!.idWorker, currentAlcoTask!!.manager, table_cart1!!.items, loader, nowDate, currentAlcoTask!!.idOrder)
                table_cart1?.items?.clear()
                table_lc_list?.items?.remove(currentResTask)
                table_tasks?.items?.clear()
                Utils.getLoaderTasks(connection, worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }
            }
        }

        btn_create_res_task?.setOnAction {
            if (table_cart?.items?.isNotEmpty()!! && currentResTask != null) {
                // curr.manager this is engineer
                var loader = 1
                val nowDate = Date(Calendar.getInstance().time.time)
                Utils.createLoaderResTask(connection, worker!!.idWorker, currentResTask!!.manager, table_cart1!!.items, loader, nowDate, currentResTask!!.idOrder)
                table_cart?.items?.clear()
                table_lc_list?.items?.remove(currentAlcoTask)
                table_tasks?.items?.clear()
                Utils.getLoaderTasks(connection, worker!!.idWorker)?.let { table_tasks!!.items.addAll(it) }
            }
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
            currentAlcoTask = it
            createTasksResAlc(dataResAlc, connection, it)
            //todo
        })
        //position of task barman
        table_cart_name1?.cellValueFactory = PropertyValueFactory("beerName")
        table_cart_amount1?.cellValueFactory = PropertyValueFactory("type")
        table_cart_unit1?.cellValueFactory = PropertyValueFactory("amount")
        table_cart_price1?.cellValueFactory = PropertyValueFactory("price")


        // tasks via engineer
        Utils.getEngineerResOrders(connection, worker!!.idWorker)?.let { table_res_list?.items?.addAll(it) }
        table_res_list_id?.cellValueFactory = PropertyValueFactory("idOrder")
        table_res_list_manager?.cellValueFactory = PropertyValueFactory("manager")
        table_res_list_date?.cellValueFactory = PropertyValueFactory("date")
        table_res_list_status?.cellValueFactory = PropertyValueFactory("status")
        table_res_list?.columns?.add(addButtonColumn("Action", "handle") {
            currentResTask = it
            createTasksRes(dataResAlc, connection, it)
            //todo
        })

        //position of task engineer
        table_cart_name?.cellValueFactory = PropertyValueFactory("beerName") // this is res name
        table_cart_amount?.cellValueFactory = PropertyValueFactory("type")
        table_cart_unit?.cellValueFactory = PropertyValueFactory("amount")
        table_cart_price?.cellValueFactory = PropertyValueFactory("price")

    }

    private fun createTasksRes(data: MutableList<OrderPosition>, connection: Connection, it: Orders?) {
        table_cart?.items?.clear()

        Utils.getEngineerResOrdersPosition(connection, it!!.idOrder)?.let { it1 ->
            table_cart?.items?.addAll(it1
            )
        }
    }

    private fun createTasksResAlc(dataResAlc: MutableList<OrderPosition>, connection: Connection, it: Orders?) {
        table_cart1?.items?.clear()

        Utils.getBarmanAlcOrdersPosition(connection, it!!.idOrder)?.let { it1 ->
            table_cart1?.items?.addAll(it1
            )
        }
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