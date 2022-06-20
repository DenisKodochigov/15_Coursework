import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Truck(typeTonnage: TypeTonnage, var name: String) {
    var tonnageTruck = selectTonnage(typeTonnage)
    private var currentTonnage = 0.0
    private var kitProductTruck = mutableMapOf<Product, Int>()

    //В зависимости от типа тонажа возвращается случайный тонаж из представленных в перечислении
    // или случайный тонаж из двух наименьших по грузоподъемности
    private fun selectTonnage(typeTonnage: TypeTonnage): Tonnage {
        val listSmallerTonnage = mutableListOf(Tonnage.LARGE, Tonnage.LARGE)
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

    //Заполнение грузовика товарами перед тем как встать в очередь на разгрузку
    fun fillTruckProducts() {
        var typeProductFOOD = false  //Переменная - грузовик с пищевыми продуктами или с промышленными.
        if (EnumTypeProduct.values().random() == EnumTypeProduct.FOOD) typeProductFOOD = true
        while (true) {
            val product = Nomenclature.list.random()
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
    fun printUnloadProductTruck(port: Port) {
        print("${port.name} unloading ${name}(${tonnageTruck.volume}):")
        val listProduct = mutableMapOf<Product, Int>()
        kitProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> print(" ${t.name}=$u(${u * t.weight});") }
        println("")
    }

    //Печать состава продуктов в загруженный грузовик.
    fun printloadProductTruck(port: Port) {
        print("${port.name} loading ${name}(${tonnageTruck.volume}):")
        val listProduct = mutableMapOf<Product, Int>()
        kitProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> print(" ${t.name}=$u(${u * t.weight});") }
        println("")
    }

    //Загружаем в грузовик
    suspend fun loadFromStorageToTruck(port: MoveProduct) {
        val portO = port as Port
        portO.noBusy = true
        runBlocking {
            launch {
                port.loadTruck().collect {
                    if (it != null) {
                        val quantity = kitProductTruck[it] ?: 0  //определяю количество в машине полученного товара
                        if (tonnageTruck.volume >= (currentTonnage + it.weight) && DistributionCenter.runningProgram) { //Проверяю поместиться ли он в машине
                            kitProductTruck[it] = quantity + 1
                            currentTonnage += it.weight
                        } else {
                            portO.noBusy = false
                            cancel()
                        }
                    }
                }
            }
        }
    }

    //Разгружаем грузовик
    suspend fun unloadFromTruckToStorage(): Flow<Product> {
        return flow {
            for (productTruck in kitProductTruck) {
                var quantity = productTruck.value
                while (quantity > 0 && DistributionCenter.runningProgram) {
                    delay(productTruck.key.timeUnload)
                    emit(productTruck.key)
                    quantity -= 1
                }
                if(!DistributionCenter.runningProgram) break
            }
        }
    }
}