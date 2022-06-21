import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

object DistributionCenter {

    var scope = CoroutineScope( Dispatchers.Default)

    fun openPortsFotTruck(quantityStep: Int) {
        val storage = Storage(3, 3)

        val queueTruck = Channel<Truck>()
        //Unload truck
        scope.launch(CoroutineName("LOAD1")) { loadPort(storage, queueTruck, 0) }
        scope.launch(CoroutineName("LOAD2")) { loadPort(storage, queueTruck, 1) }
        scope.launch(CoroutineName("LOAD3")) { loadPort(storage, queueTruck, 2) }
//            //Load truck
        scope.launch(CoroutineName("UNLOAD1")) { unloadPort(storage, 0) }
        scope.launch(CoroutineName("UNLOAD2")) { unloadPort(storage, 1) }
        scope.launch(CoroutineName("UNLOAD3")) { unloadPort(storage, 2) }
        scope.launch(CoroutineName("QUEUE")) {
            while (true) {
                delay(1000)
                val truck = Truck(TypeTonnage.ALL, "Truck-U${numberTruck.load}")
                truck.fillTruckProducts()
                queueTruck.send(truck)
                numberTruck.load++
            }
        }
        for (step in 0..quantityStep) Thread.sleep(1000)
        queueTruck.cancel()
        scope.cancel()
        println("\n############## END PROGRAM ###############")
    }

    // Программа для перевалки товара со склада в грузовик
    private suspend fun unloadPort(storage: Storage, numberPort: Int) {
        val port = storage.listLoadPort[numberPort] as Port
        while (true) {
            delay(100) //Без этой задержки выгрузка происходит только в одну машину, предполагаю, что это задержка на переключением
            if (!port.noBusy) {
                val truck = Truck(TypeTonnage.FOROUT, "Truck-L${numberTruck.unload}")
                numberTruck.unload++
                truck.loadFromStorageToTruck(port)
            }
        }
    }

    // Программа для перевалки товара из грузовика на склад
    private suspend fun loadPort(storage: Storage, queueTruck: Channel<Truck>, numberPort: Int) {
        val port = storage.listUnloadPort[numberPort] as Port
        while (true) {
            delay(100)
            if (!port.noBusy) {
                val truck = queueTruck.receive()
                truck.printUnloadProductTruck(port)
                port.unloadTruck(truck)
                storage.printProductInStorage()
            }
        }
    }
}