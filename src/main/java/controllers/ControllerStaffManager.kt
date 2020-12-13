package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Tab
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import pojo.Worker

class ControllerStaffManager {
    private var worker: Worker? = null

    @FXML
    private var tab_factory: Tab? = null

    @FXML
    private var btn_buy: Button? = null

    @FXML
    private var btn_buy1: Button? = null

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    private var filter_date: Button? = null

    @FXML
    private var table_beer_menu: TableView<*>? = null

    @FXML
    private var table_beer_menu_name: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_type: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_amount: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_price: TableColumn<*, *>? = null

    @FXML
    private var tab_tasks1: Tab? = null

    @FXML
    private var table_beer_menu2: TableView<*>? = null

    @FXML
    private var table_beer_menu_name2: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_type1: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_amount1: TableColumn<*, *>? = null

    @FXML
    private var table_beer_menu_price2: TableColumn<*, *>? = null

    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    @FXML
    fun initialize(user: User) {
        var connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
    }
}