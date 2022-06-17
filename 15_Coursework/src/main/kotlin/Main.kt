import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


var numberTruck = NumberTruck(1, 1)

fun main() {
    val storage = Storage(3, 3)

    runBlocking {
        val queueTruck = Channel<Truck>()
        while (true) {
            //Unload truck
            launch { loadPort(storage, queueTruck, 0) }
            launch { loadPort(storage, queueTruck, 1) }
            launch { loadPort(storage, queueTruck, 2) }

            //Load truck
            launch { unloadPort(storage, 0) }
            launch { unloadPort(storage, 1) }
            launch { unloadPort(storage, 2) }

            delay(10000)
            val truck = Truck(TypeTonnage.ALL, "Truck-${numberTruck.load}")
            truck.fillTruckProducts()
            queueTruck.send(truck)
            numberTruck.load++
        }
    }
}

// Программа для перевалки товара из грузовика на склад
suspend fun loadPort(storage: Storage, queueTruck: Channel<Truck>, numberPort: Int) {
    val port = storage.listUnloadPort[numberPort]
    if (!port.noBusy) {
        for (truck in queueTruck) {
            truck.printUnloadProductTruck(port as Port)
            port.unloadTruck(truck)
//            storage.printProductInStorage()
        }
    }
}

// Программа для перевалки товара со склада в грузовик
suspend fun unloadPort(storage: Storage, numberPort: Int) {
    val port = storage.listLoadPort[numberPort]
    if (!port.noBusy) {
        val truck = Truck(TypeTonnage.FOROUT, "Truck-${numberTruck.unload}")
        numberTruck.unload++
        truck.loadFromStorageToTruck(port)
        truck.printloadProductTruck(port as Port)
        storage.printProductInStorage()
    }
}
