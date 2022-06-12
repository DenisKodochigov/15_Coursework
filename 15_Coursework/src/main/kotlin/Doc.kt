import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Doc(
    override var noBizzy: Boolean,
    override val typeDoc: TypeDoc,
    override val name: String,
    override var listProduct: MutableMap<Product, Int>
) : LoadTruck {
    init {
        noBizzy = false
    }

    fun openChanelUnload(truck: Truck): MutableMap<Product, Int> {

        val unLoadProduct = mutableMapOf<Product, Int>()
        if (typeDoc != TypeDoc.UNLOAD) return unLoadProduct
        noBizzy = true
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
        noBizzy = false
        return unLoadProduct
    }

    fun getStorageProduct(typeProduct: EnumCategory): MutableMap<Product, Int> {
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

    //Загружаем грузовик товаром
    override fun loadTruck(): Flow<Product> {
//        val typeProductLoad = EnumTypeProduct.values().random()
        val typeProductLoad = EnumTypeProduct.LARGESIZED
        return flow {
            listProduct.takeIf { noBizzy }?.forEach { (product, q) ->
                if (product.typeProduct == typeProductLoad) {
                    var quantity = q
                    while ((quantity > 0) && noBizzy) {
                        delay(product.timeLoad * 100)
                        emit(product)
                        if (!noBizzy) break
                        listProduct[product] = quantity - 1
                        quantity -= 1
//                            println("${product.name}=${listProduct[product]}, $bizzy; ")
                    }
                }
            }
        }
    }
}