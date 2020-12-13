package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import pojo.LoaderTask
import pojo.TaskResource
import pojo.Worker
import java.net.URL
import java.sql.Connection
import java.util.*


class ControllerLoaderMan {
    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var tab_loader: TabPane? = null

    @FXML
    private var tab_res: Tab? = null

    @FXML
    private var tab_alc: Tab? = null

    @FXML
    private var btn_load_res: Button? = null

    @FXML
    private var btn_buy1: Button? = null

    @FXML
    private var table_tasks: TableView<LoaderTask>? = null

    @FXML
    private var table_tasks_number: TableColumn<Long, Long>? = null

    @FXML
    private var table_tasks_id_res: TableColumn<LoaderTask, Long>? = null

    @FXML
    private var table_tasks_id_alc: TableColumn<LoaderTask, Long>? = null

    @FXML
    private var table_tasks_date: TableColumn<LoaderTask, java.sql.Date>? = null

    @FXML
    private var table_tasks_status: TableColumn<LoaderTask, String>? = null

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    private var table_res: TableView<TaskResource>? = null

    @FXML
    private var table_res_name: TableColumn<TaskResource, String>? = null

    @FXML
    private var table_res_count: TableColumn<TaskResource, Long>? = null

    @FXML
    private var table_res_date: TableColumn<TaskResource, Date>? = null

    @FXML
    private var table_res_store: TableView<*>? = null

    @FXML
    private var table_res_store_name: TableColumn<*, *>? = null

    @FXML
    private var table_res_store_amount: TableColumn<*, *>? = null

    @FXML
    private var table_res_store_unit: TableColumn<*, *>? = null

    @FXML
    private var table_res_store_price: TableColumn<*, *>? = null

    @FXML
    private var tab_tasks1: Tab? = null

    @FXML
    private var table_res_alc_store_: TableView<*>? = null

    @FXML
    private var table_res_alc_store_name: TableColumn<*, *>? = null

    @FXML
    private var table_res_alc_store_amount: TableColumn<*, *>? = null

    @FXML
    private var table_res_alc_store_type: TableColumn<*, *>? = null

    @FXML
    private var table_res_alc_store_price: TableColumn<*, *>? = null

    @FXML
    private var table_res_alc: TableView<TaskResource>? = null

    @FXML
    private var table_res_alc_name: TableColumn<TaskResource, String>? = null

    @FXML
    private var table_res_alc_count: TableColumn<TaskResource, Long>? = null

    private var worker: Worker? = null

    @FXML
    private var table_res_alc_date: TableColumn<TaskResource, Date>? = null

    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    @FXML
    fun initialize(user: User) {
        btn_load_res!!.setOnAction {
            tab_loader!!.selectionModel!!.select(tab_res)
            print("tab")

        }

        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
        val data = mutableListOf<LoaderTask>()
        table_tasks?.items?.clear()
        Utils.getLoaderTasks(connection, worker!!.idWorker)?.let { data.addAll(it) }
        table_tasks?.items?.addAll(data)

        table_tasks_number?.cellValueFactory = PropertyValueFactory("idLoaderTask")
        table_tasks_id_res?.cellValueFactory = PropertyValueFactory("idResBuy")
        table_tasks_id_alc?.cellValueFactory = PropertyValueFactory("idImportAlc")
        table_tasks_date?.cellValueFactory = PropertyValueFactory("date")
        table_tasks_status?.cellValueFactory = PropertyValueFactory("status")

        table_tasks?.columns?.add(addButtonColumn("Action", "handle") {
            println(it)
            data.remove(it)
            updateTasks(data)
            executeTask(it)
            //todo
        })

        //table positions res
        val dataRes = mutableListOf<TaskResource>()
        table_res_name?.cellValueFactory = PropertyValueFactory("resName")
        table_res_count?.cellValueFactory = PropertyValueFactory("amount")
        table_res_date?.cellValueFactory = PropertyValueFactory("date")
        table_res?.columns?.add(addButtonColumn("Action", "handle") {
            dataRes.clear()
            table_res?.items?.let { it1 -> dataRes.addAll(it1) }
            dataRes.remove(it)
            updateTasksRes(dataRes)
            //todo
        })

        //table alc positions
        val dataResAlc = mutableListOf<TaskResource>()
        table_res_alc_name?.cellValueFactory = PropertyValueFactory("resName")
        table_res_alc_count?.cellValueFactory = PropertyValueFactory("amount")
        table_res_alc_date?.cellValueFactory = PropertyValueFactory("date")
        table_res_alc?.columns?.add(addButtonColumn("Action", "handle") {
            dataResAlc.clear()
            table_res_alc?.items?.let { it1 -> dataResAlc.addAll(it1) }
            dataRes.remove(it)
            updateTasksResAlc(dataRes)
            //todo
        })
    }

    private fun executeTask(data: LoaderTask) {
        val connection = Utils.getNewConnection()
        val pair: Pair<Long?, Long?>? = connection?.let { Utils.getAndCheckTask(it, data.idResBuy, data.idImportAlc) }
        if (pair != null) {
            if (pair.first != null) {
                handleTableRes(pair.first!!)

                return
            }
            if (pair.second != null) {
                handleTableAlc(pair.second!!)
                return
            }
        }
    }

    // first tab
    private fun updateTasksRes(data: MutableList<TaskResource>) {
        table_res?.items?.clear()
        table_res?.items?.addAll(data)
    }

    private fun updateTasksResAlc(data: MutableList<TaskResource>) {
        table_res_alc?.items?.clear()
        table_res_alc?.items?.addAll(data)
    }

    private fun updateTasks(data: MutableList<LoaderTask>) {
        table_tasks?.items?.clear()
        table_tasks?.items?.addAll(data)
    }

    //second tab
    private fun handleTableRes(first: Long) {
        val dataRes = mutableListOf<TaskResource>()
        val connection: Connection? = Utils.getNewConnection()
        Utils.getTaskResources(connection!!, first)?.let { dataRes.addAll(it) }
        tab_loader?.selectionModel?.select(tab_res)
        table_res?.items?.clear()
        table_res?.items?.addAll(dataRes)

        println("Handled 1")
    }

    // todo fix dublicate
    private fun handleTableAlc(second: Long) {
        val dataResAlc = mutableListOf<TaskResource>()
        val connection: Connection? = Utils.getNewConnection()
        Utils.getTaskResourcesAlc(connection!!, second)?.let { dataResAlc.addAll(it) }
        tab_loader?.selectionModel?.select(tab_alc)
        table_res_alc?.items?.clear()
        table_res_alc?.items?.addAll(dataResAlc)

        println("Handled 2")

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