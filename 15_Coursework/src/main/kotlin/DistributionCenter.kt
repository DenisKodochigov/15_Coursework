import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

object DistributionCenter {
    var runningProgram = true

    fun openPortsFotTruck(quantityStep: Int) {
        val storage = Storage(3, 3)

        runBlocking {
            val queueTruck = Channel<Truck>()
            //Unload truck
            launch { loadPort(storage, queueTruck, 0) }
            launch { loadPort(storage, queueTruck, 1) }
            launch { loadPort(storage, queueTruck, 2) }
            //Load truck
            launch { unloadPort(storage, 0) }
            launch { unloadPort(storage, 1) }
            launch { unloadPort(storage, 2) }
            launch {
                while (runningProgram) {
                    delay(1000)
                    val truck = Truck(TypeTonnage.ALL, "Truck-U${numberTruck.load}")
                    truck.fillTruckProducts()
                    queueTruck.send(truck)
                    numberTruck.load++
                }
                cancel()
            }
            launch {
                for (step in 0..quantityStep) delay(1000)
                runningProgram = false
                queueTruck.cancel()
                cancel()
            }
        }
    }

    // Программа для перевалки товара со склада в грузовик
    private suspend fun unloadPort(storage: Storage, numberPort: Int) {
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
    private suspend fun loadPort(storage: Storage, queueTruck: Channel<Truck>, numberPort: Int) {
        val port = storage.listUnloadPort[numberPort] as Port
        while (runningProgram) {
            delay(100)
            if (!port.noBusy) {
                val truck = queueTruck.receive()
                truck.printUnloadProductTruck(port)
                port.unloadTruck(truck)
//                storage.printProductInStorage()
            }
        }
    }
}