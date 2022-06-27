package Product

class Storage : MovingInsideWarehouse {
    private var storageProduct = mutableMapOf<Product, Int>()

    init {
//      Создание пустого склада со всей ожидаемой номеклатурой
        Nomenclature.list.forEach { storageProduct[it] = 0 }
    }

    //Печать состава продуктов в подЪезжающем грузовике.
    fun printProductInStorage() {
        var currentTypeProduct = EnumTypeProduct.LARGESIZED
        print("State Product.Product.Storage:")
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
        println("")
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