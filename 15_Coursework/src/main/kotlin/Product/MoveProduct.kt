package Product

import Product.Product
import Truck.Truck
import kotlinx.coroutines.flow.Flow

interface MoveProduct {
    val name: String
    suspend fun loadTruck(): Flow<Product?>
    suspend fun unloadTruck(truck: Truck)
}