package pojo

data class Recipe(
    var resName: String? = null,
    var amount: Long = 0,
    var storeAmount: Long = 0,
    var unit: String? = null,
    var resPrice: Long = 0
)