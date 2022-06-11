import kotlinx.coroutines.flow.Flow

interface LoadTruck {
    var listProduct: MutableMap<Product,Int>
    var bizzy:Boolean
    val typeDoc: TypeDoc
    fun loadTruck(): Flow<Product>
}