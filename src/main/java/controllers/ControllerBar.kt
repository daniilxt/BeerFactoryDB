package controllers

import JDBC.Utils
import JDBC.dao.User
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import javafx.util.Callback
import pojo.BeerMenu
import pojo.OrderPosition
import pojo.Recipe
import pojo.Worker
import java.net.URL
import java.sql.Connection
import java.sql.Date
import java.util.*
import kotlin.math.abs


class ControllerBar { @FXML
private var resources: ResourceBundle? = null
    @FXML private var location: URL? = null
    @FXML private var tab_loader: TabPane? = null
    @FXML private var tab_factory: Tab? = null
    @FXML private var btn_buy: Button? = null
    @FXML private var tab_tasks: Tab? = null
    @FXML private var table_beer_menu: TableView<BeerMenu>? = null
    @FXML private var table_beer_menu_name: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_type: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_amount: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_beer_menu_price: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_cart: TableView<BeerMenu>? = null
    @FXML private var table_cart_num: TableColumn<Long, Long>? = null
    @FXML private var table_cart_name: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_type: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_amount: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_cart_price: TableColumn<BeerMenu, Long>? = null
    @FXML private var filter_date_from: DatePicker? = null
    @FXML private var filter_date_to: DatePicker? = null
    @FXML private var filter_date: Button? = null
    @FXML private var filter_amount: Button? = null
    @FXML private var filter_amount_from: TextField? = null
    @FXML private var filter_amount_to: TextField? = null
    @FXML private var tab_buy: Tab? = null
    @FXML private var tab_request: Tab? = null
    @FXML private var btn_back_menu: Button? = null
    @FXML private var btn_clear_cart: Button? = null
    @FXML private var btn_no_alc: CheckBox? = null
    @FXML private var btn_buy1: Button? = null
    @FXML private var tab_buy1: Tab? = null
    @FXML private var btn_back_menu1: Button? = null
    @FXML private var btn_create_request: Button? = null
    @FXML private var table_cart1: TableView<BeerMenu>? = null
    @FXML private var table_cart_num1: TableColumn<Long, Long>? = null
    @FXML private var table_cart_name1: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_type1: TableColumn<BeerMenu, String>? = null
    @FXML private var table_cart_amount1: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_cart_price1: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_beer_menu1: TableView<BeerMenu>? = null
    @FXML private var table_beer_menu_name1: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_type1: TableColumn<BeerMenu, String>? = null
    @FXML private var table_beer_menu_amount1: TableColumn<BeerMenu, Long>? = null
    @FXML private var table_beer_menu_price1: TableColumn<BeerMenu, Long>? = null
    @FXML private var brn_clear: Button? = null
    @FXML private var list_manager: ComboBox<String>? = null
    @FXML private var btn_go_buy: Button? = null

    @FXML
    private var btn_go_request: Button? = null
    @FXML
    private var btn_exit: Button? = null

    private var worker: Worker? = null
    val data = mutableListOf<BeerMenu>()
    val dataRequest = mutableListOf<BeerMenu>()


    @FXML
    fun findIdTask(event: ActionEvent?) {
    }

    @FXML
    private fun alert(str: String = "Incorrect input", type: Alert.AlertType = Alert.AlertType.ERROR) {
        val alert = Alert(type)
        alert.title = "Attention"
        alert.contentText = str
        alert.showAndWait()
    }

    @FXML
    fun alertConfirm(text: String = "", arr: List<BeerMenu>, connection: Connection) {
        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.title = "Dele this positions"
        alert.headerText = "We haven't enough beer, please delete"
        alert.contentText = text

        // option != null.
        val option = alert.showAndWait()
        when {
            option.get() == ButtonType.OK -> {
                println("OK")
            }
            option.get() == ButtonType.CANCEL -> {
                println("CANCELLED")

            }
            else -> {
                println("??")

            }
        }
    }

    @FXML
    fun initialize(user: User) {
        val connection = Utils.getNewConnection()
        worker = Utils.getWorkerByLogin(user.login, connection!!)
        val manager = Utils.getManagers(connection)
        manager?.map { "${it.name} ${it.secondName} #${it.idWorker}" }?.toList()?.let { list_manager?.items?.addAll(it) }

        initMenu(connection)
        initButtons(connection)

        btn_clear_cart?.setOnAction {
            table_cart?.items?.clear()
            data.clear()
        }
        btn_buy?.setOnAction {
            if (table_cart!!.items?.isNotEmpty()!!) {
                val nowDate = java.sql.Date(Calendar.getInstance().time.time)
                println(nowDate)
                println(worker!!.idWorker)
                println(table_cart!!.items)

                val arrBuy = mutableListOf<BeerMenu>()
                var bigStr = ""
                table_cart!!.items.forEachIndexed { index, it1 ->
                    run {
                        val tmp = Utils.checkBeer(it1.name!!, it1.amount, connection)
                        if (tmp < 0) {
                            val tmpElement = table_cart!!.items[index].copy(amount = abs(tmp))
                            arrBuy.add(tmpElement)
                            bigStr += "${it1.name} ${abs(tmp)}\n "
                        }
                    }
                }
                if (arrBuy.isNotEmpty()) {
                    alertConfirm(bigStr, arrBuy, connection)
                } else {
                    val nowDate = java.sql.Date(Calendar.getInstance().time.time)
                    println(nowDate)
                    println(worker!!.idWorker)
                    println(table_cart!!.items)
                    worker?.idWorker?.let { it1 -> Utils.createBarmanOrder(connection, table_cart!!.items, it1, nowDate) }
                    data.clear()
                    table_beer_menu?.items?.clear()
                    Utils.getBeerMenu(connection)?.let { table_beer_menu?.items?.addAll(it) }
                    alert("All complete", Alert.AlertType.INFORMATION)
                }
            } else {
                alert()
            }
        }

        btn_create_request?.setOnAction {
            if (table_cart1!!.items?.isNotEmpty()!! && !list_manager?.value.isNullOrEmpty()) {
                val nowDate = java.sql.Date(Calendar.getInstance().time.time)
                println(nowDate)
                println(table_cart1!!.items)

                println(worker!!.idWorker)
                println(table_cart!!.items)
                val indexHash = list_manager?.value?.indexOf('#')
                val managerId = list_manager?.value?.substring(indexHash!! + 1)
                println(managerId)
                worker?.idWorker?.let { it1 -> Utils.createAlcoOrder(connection, table_cart1!!.items, it1, managerId!!.toLong(), nowDate) }
                table_cart1?.items?.clear()
                dataRequest.clear()
            } else {
                alert()
            }
        }
        brn_clear?.setOnAction {
            table_cart1?.items?.clear()
            dataRequest.clear()
        }
        btn_exit?.setOnAction {
            val loader = FXMLLoader()
            loader.location = javaClass.getResource("../${"MainApplication"}.fxml")
            val root = loader.load<Parent>()
            btn_exit?.scene?.window?.hide()
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()
        }
    }

    private fun initButtons(connection: Connection) {

        btn_go_buy?.setOnAction { tab_loader!!.selectionModel!!.select(tab_buy) }
        btn_go_request?.setOnAction { tab_loader!!.selectionModel!!.select(tab_request) }
        btn_back_menu?.setOnAction { tab_loader!!.selectionModel!!.select(tab_factory) }
        btn_back_menu1?.setOnAction { tab_loader!!.selectionModel!!.select(tab_factory) }
    }

    @FXML
    fun onButtonClick(action: ActionEvent) {

    }

    private fun initMenu(connection: Connection) {
        table_beer_menu_name?.cellValueFactory = PropertyValueFactory("Name")
        table_beer_menu_type?.cellValueFactory = PropertyValueFactory("Type")
        table_beer_menu_amount?.cellValueFactory = PropertyValueFactory("Amount")
        table_beer_menu_price?.cellValueFactory = PropertyValueFactory("Price")

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

        // table request alcohol
        table_beer_menu_name1?.cellValueFactory = PropertyValueFactory("Name")
        table_beer_menu_type1?.cellValueFactory = PropertyValueFactory("Type")
        table_beer_menu_amount1?.cellValueFactory = PropertyValueFactory("Amount")
        table_beer_menu_price1?.cellValueFactory = PropertyValueFactory("Price")

        table_beer_menu1?.items?.clear()
        Utils.getBeerMenu(connection)?.let { table_beer_menu1?.items?.addAll(it.filter { i2 -> i2.type == "Import" }.toMutableList()) }
        table_beer_menu1?.columns?.add(addButtonColumn("Action", "add") {
            val beer = BeerMenu(it.name, it.type, 1, it.price)
            dataRequest.add(beer)
            val count = dataRequest.count { i1 -> i1.name == beer.name }
            if (count > 1) {
                val first = dataRequest.first { it2 -> it2.name == beer.name }
                val index = dataRequest.indexOf(first)
                dataRequest.removeAll { item -> item.name == beer.name }
                val newBeer = BeerMenu(first.name, first.type, first.amount + beer.amount, first.price + beer.price)
                dataRequest.add(index, newBeer)
            }
            updateCart2(dataRequest)
            //todo
        })


        table_cart_num1?.cellFactory = LineNumbersCellFactory()
        table_cart_name1?.cellValueFactory = PropertyValueFactory("Name")
        table_cart_type1?.cellValueFactory = PropertyValueFactory("Type")
        table_cart_amount1?.cellValueFactory = PropertyValueFactory("Amount")
        table_cart_price1?.cellValueFactory = PropertyValueFactory("Price")
        table_cart1?.columns?.add(addButtonColumn("Action", "del") {
            data.remove(it)
            updateCart2(data)
            //todo
        })
    }

    private fun updateCart2(dataRequest: MutableList<BeerMenu>) {
        table_cart1?.items?.clear()
        table_cart1?.items?.addAll(dataRequest)
    }

    private fun updateCart(dataRequest: MutableList<BeerMenu>) {
        table_cart?.items?.clear()
        table_cart?.items?.addAll(dataRequest)
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