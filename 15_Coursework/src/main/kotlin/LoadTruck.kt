import kotlinx.coroutines.flow.Flow

interface LoadTruck {
    var listProduct: MutableMap<Product,Int>
    var noBizzy:Boolean
    val typePort: TypePort
    val name:String

    fun loadTruck(): Flow<Product>
}