package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import pojo.BeerMenu
import pojo.Client
import pojo.Worker
import java.util.*


class ControllerClient {

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    private var reg_name: TextField? = null

    @FXML
    private var reg_second_name: TextField? = null

    @FXML
    private var reg_middle_name: TextField? = null

    @FXML
    private var reg_phone: TextField? = null

    @FXML
    private var reg_date: TextField? = null

    @FXML
    private var tab_factory: Tab? = null

    @FXML
    private var filter_date: Button? = null

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
    private var filter_date1: Button? = null

    @FXML
    private var table_orders_punct: TableView<*>? = null

    @FXML
    private var table_orders_punct_name: TableColumn<*, *>? = null

    @FXML
    private var table_orders_punct_type: TableColumn<*, *>? = null

    @FXML
    private var table_orders_punct_amount: TableColumn<*, *>? = null

    @FXML
    private var table_orders_punct_price: TableColumn<*, *>? = null

    @FXML
    private var table_orders: TableView<*>? = null

    @FXML
    private var table_orders_num: TableColumn<*, *>? = null

    @FXML
    private var table_orders_manager_id: TableColumn<*, *>? = null

    @FXML
    private var table_orders_sate: TableColumn<*, *>? = null

    @FXML
    private var table_orders_status: TableColumn<*, *>? = null

    @FXML
    private var find_num: TextField? = null

    @FXML
    private var btn_find: Button? = null

    @FXML
    private var btn_clear: Button? = null

    private var client: Client? = null

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        client = connection?.let { Utils.getClientByLogin(it, user.login) }
        if (client != null) {
            reg_name?.text = client!!.nameClient
            reg_second_name?.text = client!!.secondNameClient
            reg_middle_name?.text = client!!.middleNameClient
            reg_phone?.text = client!!.phoneClient
            reg_date?.text = client!!.age.toString()
        }
        //table menu
        table_beer_menu_name?.cellValueFactory = PropertyValueFactory("Name")
        table_beer_menu_type?.cellValueFactory = PropertyValueFactory("Type")
        table_beer_menu_amount?.cellValueFactory = PropertyValueFactory("Amount")
        table_beer_menu_price?.cellValueFactory = PropertyValueFactory("Price")


        val data = mutableListOf<BeerMenu>()
        table_beer_menu?.items?.clear()
        Utils.getBeerMenu(connection!!)?.let { table_beer_menu?.items?.addAll(it) }
        table_beer_menu?.columns?.add(addButtonColumn("Action", "add") {
            val beer = BeerMenu(it.name, it.type, 1, it.price)
            data.add(beer)
            val count = data.count { i1 -> i1.name == beer.name }
            if (count > 1) {
                val first = data.first { it2 -> it2.name == beer.name }
                val index = data.indexOf(first)
                data.removeAll { item -> item.name == beer.name }
                val newBeer = BeerMenu(first.name, first.type, first.amount + beer.amount, first.price + beer.price)
                data.add(index, newBeer)
            }
            updateCart(data)
            //todo
        })

        //table cart
        table_cart_num?.cellFactory = LineNumbersCellFactory()
        table_cart_name?.cellValueFactory = PropertyValueFactory("Name")
        table_cart_type?.cellValueFactory = PropertyValueFactory("Type")
        table_cart_amount?.cellValueFactory = PropertyValueFactory("Amount")
        table_cart_price?.cellValueFactory = PropertyValueFactory("Price")
        table_cart?.columns?.add(addButtonColumn("Action", "del") {
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
