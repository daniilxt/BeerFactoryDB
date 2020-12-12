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

    @FXML
    private var table_cart: TableView<BeerMenu>? = null

    @FXML
    private var table_cart_num: TableColumn<Long, Long>? = null

    @FXML
    private var table_cart_name: TableColumn<BeerMenu, String>? = null

    @FXML
    private var table_cart_type: TableColumn<BeerMenu, String>? = null

    @FXML
    private var table_cart_amount: TableColumn<BeerMenu, Long>? = null

    @FXML
    private var table_cart_price: TableColumn<BeerMenu, Long>? = null


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
        val data = mutableListOf<BeerMenu>()
        table_beer_menu?.items?.clear()
        Utils.getBeerMenu(connection!!)?.let { table_beer_menu?.items?.addAll(it) }
        table_beer_menu?.columns?.add(addButtonColumn("Action","add") {
            println(it)
            data.add(it)
            updateCart(data)
            //todo
        })

        table_cart_num?.cellFactory = LineNumbersCellFactory()
        table_cart_name?.cellValueFactory = PropertyValueFactory("Name")
        table_cart_type?.cellValueFactory = PropertyValueFactory("Type")
        table_cart_amount?.cellValueFactory = PropertyValueFactory("Amount")
        table_cart_price?.cellValueFactory = PropertyValueFactory("Price")
        table_cart?.columns?.add(addButtonColumn("Action","del") {
            println(it)
            data.remove(it)
            updateCart(data)
            //todo
        })
    }

    private fun updateCart(data: MutableList<BeerMenu>) {
        table_cart?.items?.clear()
        table_cart?.items?.addAll(data)
    }

    private fun <T> addButtonColumn(columnName:String,btnName:String,func: (it: T) -> Unit): TableColumn<T, Void> {
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

//todo fix bug
class LineNumbersCellFactory<T, E> : Callback<TableColumn<T, E>?, TableCell<T, E>> {
    override fun call(param: TableColumn<T, E>?): TableCell<T, E> {
        return object : TableCell<T, E>() {
            override fun updateItem(item: E, empty: Boolean) {
                super.updateItem(item, empty)
                if (!empty) {
                    setText("${tableRow.index + 1}")
                } else {
                    setText("")
                }
            }
        }
    }
}