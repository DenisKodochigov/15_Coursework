import kotlinx.coroutines.flow.Flow

interface MoveProduct {
    var noBusy:Boolean
    val name:String

    suspend fun loadTruck(metka:String): Flow<Product?>
    suspend fun unloadTruck(truck: Truck, metka:String)
}