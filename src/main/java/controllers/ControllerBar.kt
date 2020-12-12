package controllers

import JDBC.Utils
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import pojo.BeerMenu
import java.net.URL
import java.util.*


class ControllerBar {

    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var tab_factory: Tab? = null

    @FXML
    private var btn_buy: Button? = null

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    private var table_beer_menu: TableView<BeerMenu>? = null

    @FXML
    private var table_beer_menu_name: TableColumn<BeerMenu, String>? = null

    @FXML
    private var table_beer_menu_type: TableColumn<BeerMenu, String>? = null

    @FXML
    private var table_beer_menu_amount: TableColumn<BeerMenu, Long>? = null

    @FXML
    private var table_beer_menu_price: TableColumn<BeerMenu, Long>? = null

/*    @FXML
    private var table_beer_menu_btn: TableColumn<BeerMenu, Button>? = null*/

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
    fun findIdTask(event: ActionEvent?) {
    }

    @FXML
    fun initialize() {
        initMenu()
    }

    @FXML
    fun onButtonClick(action: ActionEvent) {

    }

    private fun initMenu() {
        table_beer_menu_name?.cellValueFactory = PropertyValueFactory("Name")
        table_beer_menu_type?.cellValueFactory = PropertyValueFactory("Type")
        table_beer_menu_amount?.cellValueFactory = PropertyValueFactory("Amount")
        table_beer_menu_price?.cellValueFactory = PropertyValueFactory("Price")

        val connection = Utils.getNewConnection()
        table_beer_menu?.items?.clear()
        Utils.getBeerMenu(connection!!)?.let { table_beer_menu?.items?.addAll(it) }
        d()

    }

    private fun d() {
        val colBtn: TableColumn<BeerMenu, Void> = TableColumn("Add")

        val cellFactory: Callback<TableColumn<BeerMenu?, Void?>?, TableCell<BeerMenu?, Void?>?> =
            Callback {
                object : TableCell<BeerMenu?, Void?>() {
                    private val btn = Button("Action")
                    override fun updateItem(item: Void?, empty: Boolean) {
                        super.updateItem(item, empty)
                        btn.setOnAction { event ->
                            val BeerMenu: BeerMenu? = tableView.items[index]
                            println("??")
                            println(tableView.items[index])
                        }
                        graphic = if (empty) {
                            null;
                        } else {
                            btn;
                        }
                    }
                }
            }

        colBtn.setCellFactory(cellFactory)
        table_beer_menu?.columns?.add(colBtn)
    }
}