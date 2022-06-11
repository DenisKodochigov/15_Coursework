class Storage(
    numberUnloadDock: Int,
    numberLoadDock: Int
) {
    var storageProduct = mutableMapOf<Product, Int>()
    var listUnloadDoc = mutableListOf<Doc>()
    var listLoadDoc = mutableListOf<Doc>()
    var listLoadDocInt = mutableListOf<LoadTruck>()

    init {
        for (i in 1..numberUnloadDock) {
            val doc = Doc(false, TypeDoc.UNLOAD, "Doc ${TypeDoc.UNLOAD} N: $i", storageProduct)
            listUnloadDoc.add(doc)
            println("Create Doc ${TypeDoc.UNLOAD} N: $i")
        }
        for (i in 1..numberLoadDock) {
            val doc = Doc(false, TypeDoc.LOAD, "Doc ${TypeDoc.LOAD} N: $i", storageProduct)
            listLoadDoc.add(doc)
            listLoadDocInt.add(doc)
            println("Create Doc ${TypeDoc.LOAD} N: $i")
        }
//        val assortment = Assortment()
//        Создал пустой склад со всей ожидаемой номеклатурой
        assortment.list.forEach { storageProduct[it] = 0 }

    }

    fun inputProduct(listProduct: MutableMap<Product, Int>) {
        listProduct.forEach { (t, q) ->
            if (storageProduct[t] == null) storageProduct[t] = q
            else storageProduct[t] = storageProduct[t]!! + q
        }
    }

    fun outputProduct(doc: Doc) {
        if (doc.typeDoc != TypeDoc.LOAD) return
        // Подготовить массив только для одного товара
        doc.flowLoad()
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printLoadProductStorage() {
        val listProduct = mutableMapOf<Product, Int>()
        storageProduct.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach { (t, u) -> println("Product: ${t.name}; quantity: $u; total weight:${u * t.weight} ") }
    }

    fun copyProductToDoc(doc: LoadTruck) {
        val selectCategory = EnumCategory.values().random()
        storageProduct.forEach { p, q ->
            if (p.category == selectCategory) {
                doc.listProduct[p] = q
            }
        }
    }
}