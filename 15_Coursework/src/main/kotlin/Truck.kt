import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Truck(typeTonnage: TypeTonnage, private var name: String) {
    private var tonnageTruck = selectTonnage(typeTonnage)
    private var currentTonnage = 0.0
    private var kitProductTruck = mutableMapOf<Product, Int>()

    //В зависимости от типа тонажа возвращается случайный тонаж из представленных в перечислении
    // или случайный тонаж из двух наименьших по грузоподъемности
    private fun selectTonnage(typeTonnage: TypeTonnage): Tonnage {

        val listSmallerTonnage = mutableListOf(Tonnage.LARGE, Tonnage.LARGE)
//        return Tonnage.SMALL
        return if (typeTonnage == TypeTonnage.FOROUT) {
            Tonnage.values().forEach {
                if (listSmallerTonnage[0].volume > it.volume) listSmallerTonnage[0] = it
                if (listSmallerTonnage[0].volume != it.volume) {
                    if (listSmallerTonnage[1].volume > it.volume) listSmallerTonnage[1] = it
                }
            }
            listSmallerTonnage.random()
        } else {
            Tonnage.values().random()
        }
    }

    //Заполнение грузовика товарами
    fun fillTruckProducts() {

        var typeProductFOOD = false  //Переменная - грузовик с пищевыми продуктами или с промышленными.
        if (EnumTypeProduct.values().random() == EnumTypeProduct.FOOD) typeProductFOOD = true

        while (true) {
            val product = assortment.list.random()
            if (tonnageTruck.volume < product.weight.toInt()) continue
            val quantity = (1..(tonnageTruck.volume / product.weight).toInt()).random()
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
    fun printLoadProductTruck(port: Port) {
        print("The ${port.name} have truck ${name}, include product: ")
        val listProduct = mutableMapOf<Product, Int>()
        kitProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> print("${t.name}=$u; weight:${u * t.weight} ;") }
        println("")
    }

    //Загружаем в грузовик
    suspend fun loadFromStorageToTruck(port: MoveProduct, metka: String) {
        port.noBusy = true
        runBlocking {
            launch {
                port.loadTruck(metka).collect {
                    if (it != null) {
                        val quantity = kitProductTruck[it] ?: 0  //определяю количество в машине полученного товара
                        if (tonnageTruck.volume >= (currentTonnage + it.weight)) { //Проверяю поместиться ли он в машине
                            kitProductTruck[it] = quantity + 1
                            currentTonnage += it.weight
//                          print("${port.name}, $name; ")
                        } else {
                            println("No product !!!!")
//                            cancel()
                        }
//                        print("$metka ")
                    }
                }
            }
        }
        print("$name ${tonnageTruck}($currentTonnage) full filling next product:")
        kitProductTruck.forEach { (p, q) -> print("${p.name}:$q; ") }
        println("")
        port.noBusy = false
    }

    //Разгружаем грузовик
    suspend fun unloadFromTruckToStorage(metka: String): Flow<Product> {
        return flow {
            while (kitProductTruck.isNotEmpty()) {
                val product: Product = kitProductTruck.firstNotNullOf { (p, _) -> p }
                var quantity = kitProductTruck[product] ?: 0
                while (quantity > 0) {
                    delay(product.timeUnload)
                    emit(product)
                    quantity -= 1
                }
//                println("From $name to storage moving: ${product.name}=${kitProductTruck[product]}")
                kitProductTruck.remove(product)
            }
        }
    }
}