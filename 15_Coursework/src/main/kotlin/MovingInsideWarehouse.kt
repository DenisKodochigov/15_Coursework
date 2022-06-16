interface MovingInsideWarehouse {
    //Извлекаем товар со склада с проверокой на ноль
    fun getProduct(typeProductLoad: EnumTypeProduct): Product?

    //Помещаем товар на склад с увеличением количества на 1
    fun setProduct(product: Product)

    // Уменьшаем количество на одну единицу измерения
    fun removeProduct(product: Product)

    // Запрос количества
    fun getQuantity(product: Product): Int
}