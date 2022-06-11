open class Product(
    val name: String,
    override var typeProduct: EnumTypeProduct,
    val category: EnumCategory,
    val timeUnload: Long,
    val timeLoad: Long,
    val weight: Double
) : CategoryProduct(category, typeProduct) {
}