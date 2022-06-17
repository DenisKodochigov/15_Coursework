import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var numberTruck = NumberTruck(1, 1)

fun main() {
    val storage = Storage(3, 3)
    val runningProgram = true
    runBlocking {
        val queueTruck = Channel<Truck>()
        //Unload truck
        launch { loadPort(storage, queueTruck, 0, runningProgram) }
        launch { loadPort(storage, queueTruck, 1, runningProgram) }
        launch { loadPort(storage, queueTruck, 2, runningProgram) }
        //Load truck
        launch { unloadPort(storage, 0, runningProgram) }
        launch { unloadPort(storage, 1, runningProgram) }
        launch { unloadPort(storage, 2, runningProgram) }
        while (true) {
            delay(1000)
            val truck = Truck(TypeTonnage.ALL, "Truck-U${numberTruck.load}")
            truck.fillTruckProducts()
            queueTruck.send(truck)
            numberTruck.load++
        }
    }
}

// Программа для перевалки товара со склада в грузовик
suspend fun unloadPort(storage: Storage, numberPort: Int, runningProgram: Boolean) {
    val port = storage.listLoadPort[numberPort] as Port
    while (runningProgram) {
        delay(100) //Без этой задержки выгрузка происходит только в одну машину, предполагаю, что это задержка на переключением
        if (!port.noBusy) {
            val truck = Truck(TypeTonnage.FOROUT, "Truck-L${numberTruck.unload}")
            numberTruck.unload++
            truck.loadFromStorageToTruck(port)
            truck.printloadProductTruck(port)
            storage.printProductInStorage()
        }
    }
}

// Программа для перевалки товара из грузовика на склад
suspend fun loadPort(
    storage: Storage, queueTruck: Channel<Truck>,
    numberPort: Int, runningProgram: Boolean
) {
    val port = storage.listUnloadPort[numberPort] as Port
    while (runningProgram) {
        delay(100)
        if (!port.noBusy) {
            val truck = queueTruck.receive()
            truck.printUnloadProductTruck(port)
            port.unloadTruck(truck)
//        storage.printProductInStorage()
        }
    }
}

