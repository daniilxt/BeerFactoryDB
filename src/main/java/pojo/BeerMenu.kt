package pojo

import javafx.scene.control.Button

data class BeerMenu(
    var name: String? = null,
    var type: String? = null,
    var amount: Long = 0,
    var price: Long = 0
/*    var button:Button*/
)/*{
    init{
        button.setOnAction {  }
    }
}*/