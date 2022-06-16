import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var assortment = Assortment()
var numberTruckLoad = 0
var numberTruckUnload = 0

fun main() {
    var stepGame = 0

    val storage = Storage(3, 3)
    while (true) {
        runBlocking {
            while (true) {
                //Unload truck
                launch { loadPort(storage, 0) }
//                launch { loadPort(storage, 1) }
//                launch { loadPort(storage, 2) }

                //Load truck
                launch { unloadPort(storage, 0) }
//                launch { unloadPort(storage, 1) }
//                launch { unloadPort(storage, 2) }
                stepGame++
                delay(1000)
//                println("Step game: $stepGame Unload port: ${storage.listUnloadPort[0].noBusy}, load port: ${storage.listLoadPort[0].noBusy}")
            }
        }
    }

//    println("############################################################")
//    storage.printLoadProductToStorage()
}

// Программа для перевалки товара из грузовика на склад
suspend fun loadPort(storage: Storage, numberPort: Int) {
    val port = storage.listUnloadPort[numberPort]
    if (!port.noBusy) {
        numberTruckLoad++
        val truck = Truck(TypeTonnage.ALL, "Truck-$numberTruckLoad")
        truck.fillTruckProducts()
//        truck.printLoadProductTruck(port as Port)
        port.unloadTruck(truck, "U$numberPort")
        storage.printLoadProductToStorage()
    } else {
//        println("${port.name} Busy")
    }
}

// Программа для перевалки товара со склада в грузовик
suspend fun unloadPort(storage: Storage, numberPort: Int) {
    val port = storage.listLoadPort[numberPort]
    if (!port.noBusy) {
        numberTruckUnload++
        val truck = Truck(TypeTonnage.FOROUT, "Truck-$numberTruckUnload")
//        truck.printLoadProductTruck(port as Port)
        truck.loadFromStorageToTruck(port, "L$numberPort")
//        storage.printLoadProductToStorage()
    } else {
//        println("${port.name} Busy")
    }

}
