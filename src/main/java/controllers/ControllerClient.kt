package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.util.Callback
import pojo.BeerMenu
import pojo.Client
import pojo.OrderPosition
import pojo.Orders
import java.sql.Connection
import java.util.*


class ControllerClient {

    @FXML private var tab_tasks: Tab? = null
    @FXML private var reg_name: TextField? = null
    @FXML private var reg_second_name: TextField? = null
    @FXML private var reg_middle_name: TextField? = null
    @FXML private var reg_phone: TextField? = null
    @FXML private var reg_date: TextField? = null
    @FXML private var tab_factory: Tab? = null
    @FXML private var btn_buy: Button? = null
    @FXML private var table_cart: TableView<BeerMenu>? = null
    @FXML private var table_cart_num: TableColumn<Long, Long>? = null
    @FXML private var table_cart_name: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_type: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_amount: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_cart_price: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_beer_menu: TableView<BeerMenu>? = null
    @FXML private var table_beer_menu_name: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_type: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_amount: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_beer_menu_price: TableColumn<BeerMenu, Long>? = null
    @FXML private var filter_date1: Button? = null
    @FXML private var table_orders_punct: TableView<OrderPosition>? = null
    @FXML private var table_orders_punct_name: TableColumn<OrderPosition, String>? = null
    @FXML private var table_orders_punct_type: TableColumn<OrderPosition, String>? = null
    @FXML private var table_orders_punct_amount: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_orders_punct_price: TableColumn<OrderPosition, Long>? = null
    @FXML private var table_orders: TableView<Orders>? = null
    @FXML private var table_orders_num: TableColumn<Orders, Long>? = null
    @FXML private var table_orders_manager_id: TableColumn<Orders, Long>? = null
    @FXML private var table_orders_date: TableColumn<Orders, Date>? = null
    @FXML private var table_orders_status: TableColumn<Orders, String>? = null
    @FXML private var find_num: TextField? = null
    @FXML private var btn_find: Button? = null
    @FXML private var btn_clear: Button? = null
    @FXML private var btn_no_alc: CheckBox? = null
    @FXML private var btn_go_orders: Button? = null
    @FXML private var btn_go_buy: Button? = null
    @FXML private var btn_clear_cart: Button? = null
    @FXML private var tab_loader: TabPane? = null
    @FXML private var tab_profile: Tab? = null
    @FXML private var tab_buy: Tab? = null
    @FXML private var tab_orders: Tab? = null
    @FXML private var btn_back_orders: Button? = null
    @FXML private var btn_back_menu: Button? = null

    private var client: Client? = null

    @FXML
    private fun alert(str: String = "Incorrect input") {
        val alert = Alert(Alert.AlertType.ERROR)
        alert.title = "Attention"
        alert.contentText = str
        alert.showAndWait()
    }

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


        var data = mutableListOf<BeerMenu>()
        var dataList: MutableList<BeerMenu>
        val dataCopy = Utils.getBeerMenu(connection!!)
        btn_no_alc?.setOnAction {
            dataList = if (btn_no_alc!!.isSelected) {
                dataCopy!!.filter { it.type == "Import" }.toMutableList()
            } else {
                dataCopy as MutableList<BeerMenu>
            }
            table_beer_menu?.items?.clear()
            table_beer_menu?.items?.addAll(dataList)
        }
        btn_clear_cart?.setOnAction { table_cart?.items?.clear()
            data.clear()
        }
        btn_buy?.setOnAction {
            if (table_cart!!.items?.isNotEmpty()!!) {
                val nowDate = java.sql.Date(Calendar.getInstance().time.time)
                val manager = Utils.getCountManagers(connection)
                val rnds = (1..manager).random()
                Utils.createOrder(connection, table_cart!!.items, rnds, nowDate, client!!.idClient)

                data.clear()

                // todo  del duplicate
                table_cart?.items?.clear()
                val dataOrders = mutableListOf<Orders>()
                table_orders?.items?.clear()
                Utils.getOrdersByClient(connection, client!!.idClient)?.let { dataOrders.addAll(it) }
                table_orders?.items?.addAll(dataOrders)

            }
        }
        btn_find?.setOnAction {
            if (find_num!!.text.isNotEmpty()) {
                try {
                    val num = find_num!!.text.toLong()
                    showOrders(num, connection)

                } catch (ex: Exception) {
                    alert()
                }
            }
        }
        btn_clear?.setOnAction { table_orders_punct?.items?.clear() }
        //move
        btn_go_orders?.setOnAction { tab_loader!!.selectionModel!!.select(tab_orders) }
        btn_go_buy?.setOnAction { tab_loader!!.selectionModel!!.select(tab_buy) }
        btn_back_menu?.setOnAction { tab_loader!!.selectionModel!!.select(tab_profile) }
        btn_back_orders?.setOnAction { tab_loader!!.selectionModel!!.select(tab_profile) }

        table_beer_menu?.items?.clear()
        Utils.getBeerMenu(connection)?.let { table_beer_menu?.items?.addAll(it) }
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

        //table orders
        val dataOrders = mutableListOf<Orders>()
        table_orders?.items?.clear()
        Utils.getOrdersByClient(connection, client!!.idClient)?.let { dataOrders.addAll(it) }
        table_orders?.items?.addAll(dataOrders)

        table_orders_num?.cellValueFactory = PropertyValueFactory("idOrder")
        table_orders_manager_id?.cellValueFactory = PropertyValueFactory("manager")
        table_orders_date?.cellValueFactory = PropertyValueFactory("date")
        table_orders_status?.cellValueFactory = PropertyValueFactory("status")
        table_orders?.columns?.add(addButtonColumn("Action", "show") {
            println(it)
            table_orders_punct?.items?.clear()
            showOrders(it.idOrder, connection)
            //todo
        })
        table_orders_punct_name?.cellValueFactory = PropertyValueFactory("beerName")
        table_orders_punct_type?.cellValueFactory = PropertyValueFactory("type")
        table_orders_punct_amount?.cellValueFactory = PropertyValueFactory("amount")
        table_orders_punct_price?.cellValueFactory = PropertyValueFactory("price")

    }

    private fun showOrders(idOrder: Long, connection: Connection) {
        table_orders_punct?.items?.clear()
        val tmp = Utils.getOrderPositionByOrderId(connection, client!!.idClient, idOrder)
        println(tmp)
        tmp?.let { it1 -> table_orders_punct?.items?.addAll(it1) }
        if (tmp == null) {
            alert("Order not exists")
        }
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
