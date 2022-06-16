import kotlinx.coroutines.flow.Flow

interface MoveProduct {
    var noBusy: Boolean
    val name: String
    suspend fun loadTruck(): Flow<Product?>
    suspend fun unloadTruck(truck: Truck)
}