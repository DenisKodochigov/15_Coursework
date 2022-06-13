import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Truck(var tonnageTruck: Tonnage, var name:String) {
    var currentTonnage = 0.0
    var kitProductTruck = mutableMapOf<Product, Int>()

    //Заполнение грузовика товарами
    fun fillTruckProducts() {
//        val assortment = Assortment()
        var typeProductFOOD = false  //Переменная - грузовик с пищевыми продуктами или с промышленными.
        if (EnumTypeProduct.values().random() == EnumTypeProduct.FOOD) typeProductFOOD = true

        while (true) {
            val product = assortment.list.random()
            if (tonnageTruck.volume < product.weight.toInt()) continue
            val quantity = (1..(tonnageTruck.volume / product.weight.toInt())).random()
            if (tonnageTruck.volume <= (currentTonnage + product.weight * quantity)) break //Проверка массы товара с грузоподъемностью.
            if (!((product.typeProduct == EnumTypeProduct.FOOD) xor typeProductFOOD)) { //Фильтрация по категориям товара
                if (kitProductTruck[product] != null) {
                    kitProductTruck[product] = kitProductTruck[product]!! + quantity
                } else kitProductTruck[product] = quantity
                currentTonnage += product.weight * quantity
            }
        }
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printLoadProductTruck() {
        print("Content truck load next product: ")
        val listProduct = mutableMapOf<Product, Int>()
        kitProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> print("${t.name}=$u; weight:${u * t.weight} ;") }
        println("")
    }

    fun flowUnload(): Flow<Product> {
        return flow {
            while (kitProductTruck.isNotEmpty()) {
                val product: Product = kitProductTruck.firstNotNullOf { (p, _) -> p }
                var quantity = kitProductTruck[product]
                if (quantity != null) {
                    while (quantity > 0) {
                        delay(product.timeUnload * 10)
                        emit(product)
                        quantity -= 1
                    }
                }
                kitProductTruck.remove(product)
            }
        }
    }
    //Принимаем товар со склада и загружаем в грузовик
    fun loadFromStorageToTruck(port: LoadTruck){
        var nT = name
        if (port.typePort != TypePort.LOAD) return
        port.noBizzy = true
        runBlocking {
            launch {
                port.loadTruck().collect {
                    var quantity = kitProductTruck[it]
                    if (quantity == null) quantity = 0
                    if(tonnageTruck.volume >= (currentTonnage + it.weight)){
                        kitProductTruck[it] = quantity + 1
                        currentTonnage += it.weight
//                        print("${doc.name}; $name  ")
                    }else{
                        port.noBizzy = false
                    }
                }
            }
        }
        print("\nTruck ${tonnageTruck}($currentTonnage) full filling next product:")
        kitProductTruck.forEach { (p, q) -> print("${p.name}:$q; ") }
        println("")
        port.noBizzy = false
    }
}