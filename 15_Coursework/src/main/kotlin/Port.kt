import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Port(
    var noBusy: Boolean,
    override val name: String,
    private var storage: MovingInsideWarehouse
) : MoveProduct {

    //Загружаем грузовик товаром
    override suspend fun loadTruck(): Flow<Product?> {
        val typeProductLoad = EnumTypeProduct.values().random()
        return flow {
            while (noBusy && DistributionCenter.runningProgram) { //Крутит пока не загрузит весь грузовик
                val movingProduct = storage.getProduct(typeProductLoad)
                if (movingProduct != null) {
                    emit(movingProduct)
                    delay(movingProduct.timeLoad)
                    storage.removeProduct(movingProduct)
                } else {
//                    println("$name not product !!! $typeProductLoad  number truck ${numberTruck.unload}")
                    emit(null)
                    delay(1000) // ждем когда товар поступит на склад
                }
            }
        }
    }

    //Разгружаем грузовик
    override suspend fun unloadTruck(truck: Truck) {
        noBusy = true
        runBlocking {
            launch {
                truck.unloadFromTruckToStorage().collect {
                    storage.setProduct(it)
                }
                noBusy = false
                cancel()
            }
        }
    }
}