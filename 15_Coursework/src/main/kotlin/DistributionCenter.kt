import port.Port
import product.Storage
import truck.Truck
import truck.TypeTonnage
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

object DistributionCenter {

    var scope = CoroutineScope( Dispatchers.Default)

    fun openPortsFotTruck(quantityStep: Int, numberLoadPort:Int, numberUnloadPort:Int) {
        val storage = Storage()

        val queueTruck = Channel<Truck>()
        //Unload truck
        for (i in 1.. numberLoadPort){
            scope.launch(CoroutineName("LOAD$i")) { loadPort(storage, queueTruck, i) }

        }
        //Load truck
        for (i in 1.. numberUnloadPort){
            scope.launch(CoroutineName("UNLOAD$i")) { unloadPort(storage, i) }
        }

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
        val port = Port(false, "PORT-LOAD_N$numberPort", storage)
        while (true) {
            if (!port.noBusy) {
                val truck = Truck(TypeTonnage.FOROUT, "Truck-L${numberTruck.unload}")
                numberTruck.unload++
                truck.loadFromStorageToTruck(port)
            }
        }
    }

    // Программа для перевалки товара из грузовика на склад
    private suspend fun loadPort(storage: Storage, queueTruck: Channel<Truck>, numberPort: Int) {
        val port = Port(false, "PORT-UNLOAD_N$numberPort", storage)
        while (true) {
            if (!port.noBusy) {
                val truck = queueTruck.receive()
                truck.printUnloadProductTruck(port)
                port.unloadTruck(truck)
                storage.printProductInStorage()
            }
        }
    }
}