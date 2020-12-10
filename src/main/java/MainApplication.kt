import controllers.ControllerAuth
import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.Stage


class MainApplication : Application() {
    @FXML
    lateinit var input: TextField

    @FXML
    lateinit var output: Label

    override fun start(stage: Stage) {
        try {

            val loader = FXMLLoader(javaClass.getResource("MainApplication.fxml"))
            stage.scene = Scene(loader.load())
            stage.title = "Beer Factory"
            stage.show()
            val controller: ControllerAuth = loader.getController()
            controller.initialize()
        } catch (ex: Exception) {

        }
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApplication::class.java, *args)
}