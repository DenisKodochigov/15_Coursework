import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Truck(var tonnageTruck: Tonnage) {
    private var currentTonnage = 0.0
    private var loadProductTruck = mutableMapOf<Product, Int>()

    //Заполнение грузовика товарами
    fun fillTruckProducts() {
//        val assortment = Assortment()
        var typeProductFOOD = false  //Переменная - грузовик с пищевыми продуктами или с промышленными.
        if (EnumTypeProduct.values().random() == EnumTypeProduct.FOOD) typeProductFOOD = true

        while (true) {
            val product = assortment.list.random()
            if (tonnageTruck.tonnage < product.weight.toInt()) continue
            val quantity = (1..(tonnageTruck.tonnage / product.weight.toInt())).random()
            if (tonnageTruck.tonnage <= (currentTonnage + product.weight * quantity)) break //Проверка массы товара с грузоподъемностью.
            if (!((product.typeProduct == EnumTypeProduct.FOOD) xor typeProductFOOD)) { //Фильтрация по категориям товара
                if (loadProductTruck[product] != null) {
                    loadProductTruck[product] = loadProductTruck[product]!! + quantity
                } else loadProductTruck[product] = quantity
                currentTonnage += product.weight * quantity
            }
        }
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printLoadProductTruck() {
        println("Content truck")
        val listProduct = mutableMapOf<Product, Int>()
        loadProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> println("Product: ${t.name}; quantity: $u; total weight:${u * t.weight} ") }
    }

    fun flowUnload(): Flow<Product> {
        return flow {
            while (loadProductTruck.isNotEmpty()) {
                val product: Product = loadProductTruck.firstNotNullOf { (p, _) -> p }
                var quantity = loadProductTruck[product]
                if (quantity != null) {
                    while (quantity > 0) {
                        delay(product.timeUnload * 100)
                        emit(product)
                        quantity -= 1
                    }
                }
                loadProductTruck.remove(product)
            }
        }
    }

    fun openChanelLoad(doc: LoadTruck){
        val unLoadProduct = mutableMapOf<Product, Int>()
        if (doc.typeDoc != TypeDoc.LOAD) return
        doc.bizzy = true
        runBlocking {
            launch {
                doc.loadTruck().collect {
                    var quantity = unLoadProduct[it]
                    if (quantity == null) quantity = 0
                    loadProductTruck[it] = quantity + 1
//                    println("Product: ${it.name}, quantity: $quantity")
                }
            }
        }
        doc.bizzy = false
    }
}