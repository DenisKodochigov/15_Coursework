import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Port(
    override var noBusy: Boolean,
    override val name: String,
    private var storage: MovingInsideWarehouse
) : MoveProduct {
    init {
        noBusy = false
    }

    //Загружаем грузовик товаром
    override suspend fun loadTruck(metka:String): Flow<Product?> {
        val typeProductLoad = EnumTypeProduct.values().random()
//        val typeProductLoad = EnumTypeProduct.LARGESIZED
        return flow {
            while (noBusy) { //Крутит пока не загрузит весь грузовик
                val movingProduct = storage.getProduct(typeProductLoad)
                if (movingProduct != null) {
                    emit(movingProduct)
                    delay(movingProduct.timeLoad)
                    storage.removeProduct(movingProduct)
//                            print("q=${listProduct[product]}; ")
//                    println("${movingProduct.name}=${storage.getQuantity(movingProduct)}, $noBusy; ")
                } else {
                    emit(null)
//                    println("port.loadTruck  No product $typeProductLoad")
                    delay(1000) // ждем когда товар поступит на склад
                }
            }
        }
    }

    //Разгружаем грузовик
    override suspend fun unloadTruck(truck: Truck, metka: String) {
        noBusy = true
        runBlocking {
            launch {
                truck.unloadFromTruckToStorage(metka).collect {
                    storage.setProduct(it)
//                  println("Moving to storage product: ${it.name}= ${storage.getQuantity(it)}")
                }
                noBusy = false
                cancel()
            }
        }
    }
}