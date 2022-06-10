class Truck(tonnageTruck: Tonnage) {
    private var currentTonnage = 0.0
    private var loadProductTruck = mutableMapOf<Product, Int>()

    init {
        val assortiment = Assortiment()
        val product = assortiment.list.random()
        var typeProductFOOD = false
        if (product.typeProduct == EnumTypeProduct.FOOD) typeProductFOOD = true

        while (true) {
            val product = assortiment.list.random()
            val quantity = (1..100).random()
            if (tonnageTruck.tonnage <= (currentTonnage + product.weight * quantity)) break
            if (!((product.typeProduct == EnumTypeProduct.FOOD) xor typeProductFOOD)) {
                loadProductTruck[product] = quantity
                currentTonnage += product.weight * quantity
            }
        }
    }
}