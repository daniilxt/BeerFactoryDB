package controllers

import javafx.fxml.FXML
import javafx.scene.control.Tab
import java.net.URL

import java.util.ResourceBundle

class ControllerFactory {
    @FXML
    private var resources: ResourceBundle? = null

    @FXML
    private var location: URL? = null

    @FXML
    private var tab_factory: Tab? = null

    @FXML
    private var tab_tasks: Tab? = null

    @FXML
    fun initialize() {
        assert(tab_factory != null) { "fx:id=\"tab_factory\" was not injected: check your FXML file 'Factory.fxml'." }
        assert(tab_tasks != null) { "fx:id=\"tab_tasks\" was not injected: check your FXML file 'Factory.fxml'." }
    }
}