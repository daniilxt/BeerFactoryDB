package controllers

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import pojo.Tasks
import java.net.URL
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
    private var table_res: TableView<*>? = null //table resources

    @FXML
    private var res_name: TableColumn<*, *>? = null

    @FXML
    private var res_amount: TableColumn<*, *>? = null

    @FXML
    private var res_amount_store: TableColumn<*, *>? = null

    @FXML
    private var res_unit: TableColumn<*, *>? = null

    @FXML
    private var res_price: TableColumn<*, *>? = null

    @FXML
    private var btn_handle: Button? = null

    @FXML
    private var table_task: TableView<Tasks>? = null //table one task

    @FXML
    private var table_task_id: TableColumn<*, *>? = null

    @FXML
    private var table_task_id_beer_kind: TableColumn<*, *>? = null

    @FXML
    private var table_task_beer_name: TableColumn<*, *>? = null

    @FXML
    private var table_task_amount: TableColumn<*, *>? = null

    @FXML
    private var table_task_date: TableColumn<*, *>? = null

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
    private var table_tasks_date: TableColumn<Tasks, java.sql.Date>? = null

    @FXML
    private var table_tasks_status: TableColumn<Tasks, String>? = null

    @FXML
    private var table_tasks_amount: TableColumn<Tasks, Long>? = null

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
    fun addNewCCT() {
    }

    @FXML
    fun initialize() {
        initColumns()
    }

    private fun initColumns() {
        //tasks table
        table_tasks_id?.cellValueFactory = PropertyValueFactory("idTask")
        table_tasks_id_engineer?.cellValueFactory = PropertyValueFactory("idTechnologicalEngineer")
        table_tasks_id_beerkind?.cellValueFactory = PropertyValueFactory("beerName")
        table_tasks_date?.cellValueFactory = PropertyValueFactory("date")
        table_tasks_status?.cellValueFactory = PropertyValueFactory("amount")

        val appList = mutableListOf<Tasks>()
        appList.add(Tasks(1,1,"Volkovskoe",java.sql.Date(11122441),3,"free"))
        table_tasks?.items?.clear()
        table_tasks?.items?.addAll(appList)
        //task table
        //cct table
    }
}