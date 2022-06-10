open class Product(
    val name: String,
    override var typeProduct: EnumTypeProduct,
    val category: EnumCategory,
    val timeUnload: Int,
    val timeLoad: Int,
    val weight: Double
) : CategoryProduct(category, typeProduct) {
}