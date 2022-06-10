class Truck(tonnageTruck: Tonnage) {
    var currentTonnage = 0.0
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
                if (loadProductTruck[product] != null) loadProductTruck[product] = loadProductTruck[product]!! + quantity
                else loadProductTruck[product] = quantity
                currentTonnage += product.weight * quantity
            }
        }
    }

    fun printLoadProductTruck(){
        val listProduct = mutableMapOf<Product, Int>()
        loadProductTruck.forEach { (t, u) ->
            if (listProduct[t] != null) listProduct[t] = listProduct[t]!! + u
            else listProduct[t] = u
        }
        listProduct.forEach{(t, u) -> println("Product: ${t.name}; quantity: $u; total weight:${u * t.weight} ")}
    }
}