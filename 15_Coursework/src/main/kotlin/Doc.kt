import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Doc(override var bizzy: Boolean, override val typeDoc: TypeDoc, val name: String,
          override var listProduct: MutableMap<Product, Int>
):LoadTruck {

    fun openChanelUnload(truck: Truck): MutableMap<Product, Int> {

        val unLoadProduct = mutableMapOf<Product, Int>()
        if (typeDoc != TypeDoc.UNLOAD) return unLoadProduct
        bizzy = true
        runBlocking {
            launch {
                truck.flowUnload().collect {
                    var quantity = unLoadProduct[it]
                    if (quantity == null) quantity = 0
                    unLoadProduct[it] = quantity + 1
//                    println("Product: ${it.name}, quantity: $quantity")
                }
            }
        }
        bizzy = false
        return unLoadProduct
    }

    fun getStorageProduct(typeProduct:EnumCategory):MutableMap<Product,Int>{
        val loadProduct = mutableMapOf<Product, Int>()
        return loadProduct
    }

    fun flowLoad(): Flow<Product> {
        val loadProduct = mutableMapOf<Product, Int>()
        return flow {
            while (loadProduct.isNotEmpty()) {
                loadProduct.forEach { (product, q) ->
                    var quantity = q
                    while (quantity > 0) {
                        delay(product.timeLoad * 100)
                        emit(product)
                        quantity -= 1
                    }
                }
            }
        }
    }

    override fun loadTruck():Flow<Product> {
//        val loadProduct = mutableMapOf<Product, Int>()
        return flow {
            while (listProduct.isNotEmpty()) {
                listProduct.forEach { (product, q) ->
                    var quantity = q
                    while (quantity > 0) {
                        delay(product.timeLoad * 100)
                        emit(product)
                        quantity -= 1
                    }
                }
            }
        }
    }
}