import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var assortment = Assortment()

var numberTruckLoad = 0
var numberTruckUnload = 0

fun main() {
    var stepGame = 0

    val storage = Storage(2, 3)
    while (true) {
        runBlocking {
            launch {
                val port = storage.listLoadDocInt[0]
                numberTruckLoad++
                val truck = Truck(Tonnage.values().random(), "Truck-$numberTruckLoad")
                truck.loadFromStorageToTruck(port)
            }
            launch {
                val doc = storage.listLoadDocInt[1]
                numberTruckLoad++
                val truck = Truck(Tonnage.values().random(), "Truck-$numberTruckLoad")
                truck.loadFromStorageToTruck(doc)
            }
            launch {
                val doc = storage.listLoadDocInt[2]
                numberTruckLoad++
                val truck = Truck(Tonnage.values().random(), "Truck-$numberTruckLoad")
                truck.loadFromStorageToTruck(doc)
            }
        }
//    while (stepGame < 100) {
//        for (doc in storage.listUnloadDoc) {
//            if (!doc.noBizzy) {
////                val truck = Truck(Tonnage.values().random(),"Truck-$numberTruckLoad")
//                val truck = Truck(Tonnage.SMALL, "Truck-$numberTruckLoad")
//                truck.fillTruckProducts()
//                print("To ${doc.name} pulled up ")
//                truck.printLoadProductTruck()
//                storage.inputProduct(doc.openChanelUnload(truck))
////                storage.printLoadProductStorage()
//                stepGame++
//            }
//        }

//        for (doc in storage.listLoadDocInt) {
//            if (!doc.noBizzy) {
//                //           val truck = Truck(Tonnage.values().random(),"Truck-$numberTruckLoad")
//                runBlocking {
//                    launch {
//                        numberTruckLoad++
//                        val truck = Truck(Tonnage.SMALL, "Truck-$numberTruckLoad")
//                        println("\nTo ${doc.name} pulled up truck ${truck.tonnageTruck}")
//                        truck.loadFromStorageToTruck(doc)
//                    }
//                }
//                storage.printLoadProductToStorage()
//            }
//        }
//        stepGame++
    }

    println("############################################################")
    storage.printLoadProductToStorage()
}