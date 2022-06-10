import kotlin.coroutines.CoroutineContext

class Storage(
    numberUnloadDock: Int,
    numberLoadDock: Int
) {
    var storageProduct = mutableMapOf<Product, Int>()


//    var unloadDoc = mutableListOf<CoroutineContext>()
//    var loadDoc = mutableListOf<CoroutineContext>()

//    init {
//        for (i in 1..numberUnloadDock) unloadDoc.add(createUnloadDoc()!!)
//        for (i in 1..numberLoadDock) loadDoc.add(createLoadDoc()!!)
//    }
//
//    fun createUnloadDoc(): CoroutineContext {
//
//        return null
//    }
//
//    fun createLoadDoc(): CoroutineContext {
//
//        return null
//    }
//
//    fun unloadProductFromStorage(product: Product, quantity: Int):Int {
//        //код по получению элементов из хранилища
//
//        // Долны бать проверки на количество на складе и временные задержки отгрузки.
//        var storageQuantity = storageProduct[product]
//        if (storageQuantity!! > quantity) storageProduct[product]?.minus(quantity)
//        else{
//            storageProduct.remove(product)
//            if (storageQuantity!! < quantity) storageQuantity-=quantity
//            else{
//
//            }
//        }
//        return storageQuantity
//    }
//
//    fun loadProductToStorage(product: Product, quantity: Int) {
//        //код по размещения продукта в хранилище
//        // Проверить на наличие этого товара на складе и добавить его.
//        storageProduct[product]?.plus(quantity)
//    }

}