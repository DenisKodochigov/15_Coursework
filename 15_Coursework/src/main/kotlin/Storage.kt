class Storage(
    numberUnloadDock: Int,
    numberLoadDock: Int
) {
    private var storageProduct = mutableMapOf<Product, Int>()
    var listUnloadPort = mutableListOf<Port>()
    var listLoadPort = mutableListOf<Port>()
    var listLoadDocInt = mutableListOf<LoadTruck>()

    init {
//        Создал пустой склад со всей ожидаемой номеклатурой
        assortment.list.forEach { storageProduct[it] = 50 }

        for (i in 1..numberUnloadDock) {
            val port = Port(false, TypePort.UNLOAD, "Doc-${TypePort.UNLOAD}_N:$i", storageProduct)
            listUnloadPort.add(port)
            println("Create Doc-${TypePort.UNLOAD}_N:$i")
        }
        for (i in 1..numberLoadDock) {
            val port = Port(false, TypePort.LOAD, "Doc-${TypePort.LOAD}_N:$i", storageProduct)
            listLoadPort.add(port)
            listLoadDocInt.add(port)
            println("Create Doc-${TypePort.LOAD}_N:$i")
        }
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printLoadProductToStorage() {
        val rollListProduct = mutableMapOf<Product, Int>()
//        storageProduct.forEach { (t, u) ->
//            if (rollListProduct[t] != null) rollListProduct[t] = rollListProduct[t]!! + u
//            else rollListProduct[t] = u
//        }
        var currentTypeProduct = EnumTypeProduct.FOOD
        println("\nState Storage:")
        storageProduct.forEach { (p, q) ->
            if (currentTypeProduct == p.typeProduct) {
                print("${p.name}: $q; ")
            } else {
                currentTypeProduct = p.typeProduct
                print("\n${currentTypeProduct}: ${p.name}: $q; ")
            }
        }
        println("")
    }

    fun outputProduct(port: Port) {
        if (port.typePort != TypePort.LOAD) return
        // Подготовить массив только для одного товара
        port.flowLoad()
    }

    fun inputProduct(listProduct: MutableMap<Product, Int>) {
        listProduct.forEach { (t, q) ->
            if (storageProduct[t] == null) storageProduct[t] = q
            else storageProduct[t] = storageProduct[t]!! + q
        }
    }

}