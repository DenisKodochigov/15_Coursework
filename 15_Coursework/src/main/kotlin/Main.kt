var  assortment = Assortment()

fun main() {

    val storage = Storage(1, 2)
    for (doc in storage.listUnloadDoc) {
        if (!doc.bizzy) {
            val truck = Truck(Tonnage.values().random())
            truck.fillTruckProducts()
            truck.printLoadProductTruck()
            storage.inputProduct(doc.openChanelUnload(truck))
        }
    }
    for (doc in storage.listLoadDocInt) {
        if (!doc.bizzy) {
            val truck = Truck(Tonnage.values().random())
            storage.copyProductToDoc(doc)
            truck.openChanelLoad(doc)
//            storage.outputProduct(doc)
            truck.printLoadProductTruck()
        }
    }
    println("############################################################")
    storage.printLoadProductStorage()
}