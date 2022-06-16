import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


var numberTruck = NumberTruck(0, 0)

fun main() {
    val storage = Storage(3, 3)

    runBlocking {
        val queueTruck = Channel<Truck>(3)
        while (true) {
            //Unload truck
            launch { loadPort(storage, queueTruck, 0) }
            launch { loadPort(storage, queueTruck, 1) }
            launch { loadPort(storage, queueTruck, 2) }

            //Load truck
            launch { unloadPort(storage, 0) }
            launch { unloadPort(storage, 1) }
            launch { unloadPort(storage, 2) }

            delay(1000)
            val truck = Truck(TypeTonnage.ALL, "Truck-${numberTruck.load}")
            truck.fillTruckProducts()
            println("send ${truck.name}")
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
            println("Receive truck ${truck.name}, port=$numberPort, busy port=${port.noBusy}")
            port.unloadTruck(truck)
            storage.printLoadProductToStorage()
        }
    }
}

// Программа для перевалки товара со склада в грузовик
suspend fun unloadPort(storage: Storage, numberPort: Int) {
    val port = storage.listLoadPort[numberPort]
    if (!port.noBusy) {
        numberTruck.unload++
        val truck = Truck(TypeTonnage.FOROUT, "Truck-${numberTruck.unload}")
        truck.loadFromStorageToTruck(port)
//        storage.printLoadProductToStorage()
    }
}
