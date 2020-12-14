package pojo

data class OrderPosition(
    var beerName: String? = null,
    var type: String? = null,
    var amount: Long = 0,
    var price: Long = 0
)