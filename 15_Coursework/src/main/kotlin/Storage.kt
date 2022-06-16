import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

class Storage(
    numberUnloadDock: Int,
    numberLoadDock: Int
) : MovingInsideWarehouse {
    private var storageProduct = mutableMapOf<Product, Int>()
    var listUnloadPort = mutableListOf<MoveProduct>()
    var listLoadPort = mutableListOf<MoveProduct>()

    init {
//      Создание пустого склада со всей ожидаемой номеклатурой
        Nomenclature.list.forEach { storageProduct[it] = 0 }
//      Создание портов разгрузки разгрузки
        for (i in 1..numberUnloadDock) {
            val port = Port(false, "PORT-UNLOAD_N$i", this)
            listUnloadPort.add(port)
        }
        for (i in 1..numberLoadDock) {
            val port = Port(false, "PORT-LOAD_N$i", this)
            listLoadPort.add(port)
        }
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printLoadProductToStorage() {
        var currentTypeProduct = EnumTypeProduct.LARGESIZED
        print("\n${Date()}: State Storage:")
        storageProduct.forEach { (p, q) ->
            if (currentTypeProduct == p.typeProduct) {
                print("${p.name.padStart(13, ' ')}=${q.toString().padEnd(3, ' ')}; ")
            } else {
                currentTypeProduct = p.typeProduct
                print(
                    "\n${currentTypeProduct.toString().padEnd(12, ' ')}:${p.name.padStart(15, ' ')}=${
                        q.toString().padEnd(3, ' ')
                    }; "
                )
            }
        }
        println("\n")
    }

    @Synchronized
    override fun setProduct(product: Product) {
        if (storageProduct[product]!! > 0) {
            storageProduct[product] = storageProduct[product]!! + 1
        } else {
            storageProduct[product] = 1
        }
    }

    @Synchronized
    override fun getProduct(typeProductLoad: EnumTypeProduct): Product? {
        storageProduct.forEach { (product, _) -> //Делаем прерываение по циклу, если грузовик загружен
            if (product.typeProduct == typeProductLoad) {         //Згружаме только опеределенный тип товара
                if (storageProduct[product]!! > 0) {
                    return product
                }
            }
        }
        return null
    }

    @Synchronized
    override fun removeProduct(product: Product) {
        if (storageProduct[product]!! > 0) {
            storageProduct[product] = storageProduct[product]!! - 1
        }
    }

    @Synchronized
    override fun getQuantity(product: Product): Int = storageProduct[product]!!
}