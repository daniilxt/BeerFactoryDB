package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.text.Text
import javafx.stage.Stage
import pojo.*
import java.lang.Math.abs
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
    @FXML
    private var btn_cct_handle: Button? = null
    @FXML
    private var btn_clear_list: Button? = null
    @FXML
    private var btn_exit: Button? = null

    @FXML
    private var id_cct: TextField? = null
    private var worker: Worker? = null

    @FXML
    private var list_manager: ComboBox<String>? = null

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
    fun alertConfirm(text: String = "", arr: List<Recipe>, connection: Connection) {
        val alert = Alert(AlertType.CONFIRMATION)
        alert.title = "Buy resources"
        alert.headerText = "Are you want to create resource request?"
        alert.contentText = text
        println("buy arr: ${arr}")

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

    private fun createRequest(arr: List<Recipe>, connection: Connection) {
        arr.forEach {
            println(it)
        }

        val nowDate = Date(Calendar.getInstance().time.time)
        println(nowDate)
        println(worker!!.idWorker)
        println(arr)
        val indexHash = list_manager?.value?.indexOf('#')
        val managerId = list_manager?.value?.substring(indexHash!! + 1)
        println(managerId)
        if (managerId != null) {
            worker?.idWorker?.let { it1 -> Utils.createResOrder(connection, arr, it1, managerId.toLong(), nowDate) }
            alert("Request sent. Wait some time.", AlertType.INFORMATION)
        } else {
            alert("Pick manager!")
        }
    }

    @FXML
    fun handleTask() {
        if (!table_task?.items?.isEmpty()!!) {
            val tmpObj = table_tasks?.items?.find { it.idTask == id_task_find?.text.toString().toLong() }
            if (tmpObj != null && tmpObj.status == "Done") {
                alert("This task was completed earlier", AlertType.INFORMATION)
                return
            }
            val connection = Utils.getNewConnection()
            val pair = Utils.countFreeCCT(connection)
            if (pair.first > 0) {
                val arrBuy = mutableListOf<Recipe>()
                table_res!!.items.forEachIndexed { index, it ->
                    run {
                        val tmp = it.amount * table_task!!.items[0].amount
                        println(tmp)
                        if (Utils.checkRes(
                                        it.resName!!, tmp, connection!!
                                ) < it.amount
                        ) {
                            //val element = arrBuy.add(table_res!!.items[index])
                            val tmpElement = table_res!!.items[index].copy(amount = kotlin.math.abs(it.storeAmount- tmp))
                            arrBuy.add(tmpElement)
                        }
                    }
                }
                if (arrBuy.isNotEmpty()) {
                    var bigStr = ""
                    println("Need Buy:")
                    arrBuy.forEachIndexed { index, it ->
                        run {
                            println(
                                    "${it.resName} ${it.amount} ${it.unit} ")
                           // val tmp = kotlin.math.abs(it.storeAmount - (it.amount * table_task!!.items[0].amount))
                            println(index)
                            bigStr += "${it.resName} ${it.amount} ${it.unit}\n "
                        }
                    }
                    connection?.let { alertConfirm(bigStr, arrBuy, it) }
                } else {
                    alert("Process...", AlertType.INFORMATION)
                    table_task?.items?.first()?.let {
                        table_res?.items?.let { it1 ->
                            if (connection != null) {
                                createBeer(connection, it1, it)
                            }
                        }
                    }
                }
            } else {
                alert("No free CCT")
            }
        } else {
            alert("Empty recipe!")
        }
    }

    private fun createBeer(connection: Connection, items: List<Recipe>, task: Task) {
        val freeIndex = table_cct?.items?.find { it.statusCCT == "FREE" }?.idCCT
        val nowDate = Date(Calendar.getInstance().time.time)
        val tmpDate = nowDate.toLocalDate().plusDays(40)
        val dateEnd = Date.valueOf(tmpDate)
        if (freeIndex != null) {
            Utils.setToCCT(connection, freeIndex, task.idTask, nowDate, dateEnd, "WORK",task.amount)
            table_cct?.items?.clear()
            Utils.showCCT(connection)?.let { table_cct?.items?.addAll(it) }
            val pair = Utils.countFreeCCT(connection)
            cct_numbers?.text = "${pair.first} / ${pair.second}"
        }
    }

    val oldArray = mutableListOf<Tasks>()
    val tasksArray = mutableListOf<Tasks>()

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
        val manager = Utils.getManagers(connection)
        manager?.map { "${it.name} ${it.secondName} #${it.idWorker}" }?.toList()?.let { list_manager?.items?.addAll(it) }

        var pair = Utils.countFreeCCT(connection)
        cct_numbers?.text = "${pair.first} / ${pair.second}"
        initCCT(connection)
        initColumns(connection)
        btn_exit?.setOnAction {
            val loader = FXMLLoader()
            loader.location = javaClass.getResource("../${"MainApplication"}.fxml")
            val root = loader.load<Parent>()
            btn_exit?.scene?.window?.hide()
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()
        }


        Utils.getTasks(worker!!.idWorker, connection)?.let { tasksArray.addAll(it) }

        btn_clear_list?.setOnAction {
            println("clearr taskarr: ${tasksArray}")

            table_tasks?.items?.clear()
            table_tasks?.items?.addAll(tasksArray)
        }
        btn_cct_handle?.setOnAction {
            if (id_cct?.text?.isNotEmpty()!!) {
                if (Utils.handleTaskFromCCTById(connection, id_cct!!.text.toString().toLong())) {
                    alert("Success Handled", AlertType.INFORMATION)
                } else {
                    alert("Something went wrong")
                }
            } else {
                alert("Empty id CCT")
            }

            table_cct?.items?.clear()
            Utils.showCCT(connection)?.let { table_cct?.items?.addAll(it) }
            pair = Utils.countFreeCCT(connection)
            cct_numbers?.text = "${pair.first} / ${pair.second}"
        }


        filter_date?.setOnAction {
            if (filter_date_from?.value != null && filter_date_to?.value != null) {
                table_tasks?.items?.let { it1 -> oldArray.addAll(it1) }
                table_tasks?.items?.clear()
                val dateFrom = Date.valueOf(filter_date_from?.value)
                val dateTo = Date.valueOf(filter_date_to?.value)
                table_tasks?.items?.addAll(oldArray.filter { (it.date!! >= dateFrom) && (it.date!! <= dateTo) }.toMutableList())
                oldArray.clear()
            } else alert()
        }
        filter_amount?.setOnAction {
            if (filter_amount_from?.text.toString().isNotEmpty() && filter_amount_to?.text.toString().isNotEmpty()) {
                table_tasks?.items?.let { it1 -> oldArray.addAll(it1) }
                table_tasks?.items?.clear()
                val from = filter_amount_from?.text.toString().toLong()
                val to = filter_amount_to?.text.toString().toLong()
                table_tasks?.items?.addAll(oldArray.filter { (it.amount >= from) && (it.amount <= to) }.toMutableList())
                oldArray.clear()
            } else alert()
        }
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
        //appList.add(Tasks(1, 1, "Volkovskoe", Date(11122441), 3, "free"))
        Utils.getTasks(worker!!.idWorker, connection)?.let { appList.addAll(it) }
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
    private fun alert(text: String = "Incorrect input", type: AlertType = AlertType.ERROR) {
        val alert = Alert(type)
        alert.title = "Attention"
        alert.contentText = text
        alert.showAndWait()
    }
}