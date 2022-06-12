import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.w3c.dom.Element

class Storage(
    numberUnloadDock: Int,
    numberLoadDock: Int
) {
    private var storageProduct = mutableMapOf<Product, Int>()
    var listUnloadDoc = mutableListOf<Doc>()
    var listLoadDoc = mutableListOf<Doc>()
    var listLoadDocInt = mutableListOf<LoadTruck>()

    init {
//        Создал пустой склад со всей ожидаемой номеклатурой
        assortment.list.forEach { storageProduct[it] = 50 }

        for (i in 1..numberUnloadDock) {
            val doc = Doc(false, TypeDoc.UNLOAD, "Doc-${TypeDoc.UNLOAD}_N:$i", storageProduct)
            listUnloadDoc.add(doc)
            println("Create Doc-${TypeDoc.UNLOAD}_N:$i")
        }
        for (i in 1..numberLoadDock) {
            val doc = Doc(false, TypeDoc.LOAD, "Doc-${TypeDoc.LOAD}_N:$i", storageProduct)
            listLoadDoc.add(doc)
            listLoadDocInt.add(doc)
            println("Create Doc-${TypeDoc.LOAD}_N:$i")
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

    fun outputProduct(doc: Doc) {
        if (doc.typeDoc != TypeDoc.LOAD) return
        // Подготовить массив только для одного товара
        doc.flowLoad()
    }

    fun inputProduct(listProduct: MutableMap<Product, Int>) {
        listProduct.forEach { (t, q) ->
            if (storageProduct[t] == null) storageProduct[t] = q
            else storageProduct[t] = storageProduct[t]!! + q
        }
    }

}