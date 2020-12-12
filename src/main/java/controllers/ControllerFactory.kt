package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.text.Text
import pojo.*
import java.net.URL
import java.sql.Connection
import java.sql.Date
import java.util.*


class ControllerFactory {

    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var tab_factory: Tab? = null

    @FXML
    private var btn_add_cct: Button? = null

    @FXML
    private var table_res: TableView<Recipe>? = null //table resources

    @FXML
    private var res_name: TableColumn<Recipe, String>? = null

    @FXML
    private var res_amount: TableColumn<Recipe, Long>? = null

    @FXML
    private var res_amount_store: TableColumn<Recipe, Long>? = null

    @FXML
    private var res_unit: TableColumn<Recipe, String>? = null

    @FXML
    private var res_price: TableColumn<Recipe, Long>? = null

    @FXML
    private var btn_handle: Button? = null

    @FXML
    private var table_task: TableView<Task>? = null //table one task

    @FXML
    private var table_task_id: TableColumn<Task, Long>? = null

    @FXML
    private var table_task_id_beer_kind: TableColumn<Task, Long>? = null

    @FXML
    private var table_task_beer_name: TableColumn<Task, String>? = null

    @FXML
    private var table_task_amount: TableColumn<Task, Long>? = null

    @FXML
    private var table_task_date: TableColumn<Task, Date>? = null

    @FXML
    private var btn_find_task: Button? = null

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    private var table_tasks: TableView<Tasks>? = null //table all tasks

    @FXML
    private var table_tasks_id: TableColumn<Tasks, Long>? = null

    @FXML
    private var table_tasks_id_engineer: TableColumn<Tasks, Long>? = null

    @FXML
    private var table_tasks_id_beerkind: TableColumn<Tasks, String>? = null

    @FXML
    private var table_tasks_date: TableColumn<Tasks, Date>? = null

    @FXML
    private var table_tasks_status: TableColumn<Tasks, String>? = null

    @FXML
    private var table_tasks_amount: TableColumn<Tasks, Long>? = null

    @FXML
    private var table_cct: TableView<CylindricallyTank>? = null

    @FXML
    private var cct_id: TableColumn<CylindricallyTank, Long>? = null

    @FXML
    private var cct_task: TableColumn<CylindricallyTank, Long>? = null

    @FXML
    private var cct_start: TableColumn<CylindricallyTank, Date>? = null

    @FXML
    private var cct_end: TableColumn<CylindricallyTank, Date>? = null

    @FXML
    private var cct_status: TableColumn<CylindricallyTank, String>? = null

    @FXML
    private var id_task_find: TextField? = null

    @FXML
    private var filter_date_from: DatePicker? = null

    @FXML
    private var filter_date_to: DatePicker? = null

    @FXML
    private var filter_date: Button? = null

    @FXML
    private var filter_amount: Button? = null

    @FXML
    private var filter_amount_from: TextField? = null

    @FXML
    private var filter_amount_to: TextField? = null

    @FXML
    private var cct_numbers: Text? = null

    private var worker: TechnologistEngineer? = null

    @FXML
    fun addNewCCT() {
    }

    @FXML
    fun findIdTask() {
        if (!id_task_find?.text.isNullOrEmpty()) {
            Utils.getNewConnection()
            val connection = Utils.getNewConnection()
            table_task?.items?.clear()
            if (connection != null) {
                table_task?.items?.addAll(Utils.getTask(id_task_find!!.text.toString().toLong(), connection))
            }
            if (!table_task?.items?.isEmpty()!!) {
                table_res?.items?.clear()
                if (connection != null) {
                    Utils.getRecipe(table_task!!.items[0].idBeerKind, connection)?.let { table_res?.items?.addAll(it) }
                }
                return
            }
            alert()
        }
    }

    @FXML
    fun handleTask() {
        if (!table_task?.items?.isEmpty()!!) {
            val connection = Utils.getNewConnection()
            val pair = Utils.countFreeCCT(connection)
            if (pair.first > 0) {
                val arrBuy = mutableListOf<Recipe>()
                table_res!!.items.forEachIndexed { index, it ->
                    run {
                        if (Utils.checkRes(
                                it.resName!!, it.amount * table_task!!.items[0].amount, connection!!
                            ) < it.amount
                        ) {
                            arrBuy.add(table_res!!.items[index])
                        }
                    }
                }
                println(arrBuy)
                if (arrBuy.isNotEmpty()) {
                    println("Need Buy:")
                    arrBuy.forEachIndexed { index, it ->
                        run {
                            println(
                                "${it.resName} ${(it.amount * table_task!!.items[0].amount) - it.storeAmount} ")
                            println(index)
                        }
                    }
                }
                println("Success")
            } else {
                alert()
            }
        }
        alert()
    }

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getEngineerByLogin(user.login, connection!!)
        val pair = Utils.countFreeCCT(connection)
        cct_numbers?.text = "${pair.first} / ${pair.second}"
        initCCT(connection)
        initColumns(connection)
    }

    private fun initCCT(connection: Connection) {
        //cct table
        table_cct?.items?.clear()
        Utils.showCCT(connection)?.let { table_cct?.items?.addAll(it) }

        cct_id?.cellValueFactory = PropertyValueFactory("idCCT")
        cct_task?.cellValueFactory = PropertyValueFactory("idTask")
        cct_start?.cellValueFactory = PropertyValueFactory("dateStart")
        cct_end?.cellValueFactory = PropertyValueFactory("dateEnd")
        cct_status?.cellValueFactory = PropertyValueFactory("statusCCT")
    }

    private fun initColumns(connection: Connection) {
        //tasks table
        table_tasks_id?.cellValueFactory = PropertyValueFactory("idTask")
        table_tasks_id_engineer?.cellValueFactory = PropertyValueFactory("idTechnologicalEngineer")
        table_tasks_id_beerkind?.cellValueFactory = PropertyValueFactory("beerName")
        table_tasks_date?.cellValueFactory = PropertyValueFactory("date")
        table_tasks_amount?.cellValueFactory = PropertyValueFactory("amount")
        table_tasks_status?.cellValueFactory = PropertyValueFactory("status")

        val appList = mutableListOf<Tasks>()
        appList.add(Tasks(1, 1, "Volkovskoe", Date(11122441), 3, "free"))
        Utils.getTasks(worker!!.idTechnologistEngineer, connection)?.let { appList.addAll(it) }
        table_tasks?.items?.clear()
        table_tasks?.items?.addAll(appList)

        //task table
        table_task_id?.cellValueFactory = PropertyValueFactory("idTask")
        table_task_id_beer_kind?.cellValueFactory = PropertyValueFactory("idBeerKind")
        table_task_beer_name?.cellValueFactory = PropertyValueFactory("beerName")
        table_task_amount?.cellValueFactory = PropertyValueFactory("amount")
        table_task_date?.cellValueFactory = PropertyValueFactory("date")

        //res table
        res_name?.cellValueFactory = PropertyValueFactory("resName")
        res_amount?.cellValueFactory = PropertyValueFactory("amount")
        res_amount_store?.cellValueFactory = PropertyValueFactory("storeAmount")
        res_unit?.cellValueFactory = PropertyValueFactory("unit")
        res_price?.cellValueFactory = PropertyValueFactory("resPrice")

    }

    @FXML
    private fun alert() {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Attention"
        alert.contentText = "Incorrect input"
        alert.showAndWait()
    }
}