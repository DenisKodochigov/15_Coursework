package Product

open class Product(
    val name: String,
    var typeProduct: EnumTypeProduct,
    val category: EnumCategory,
    val timeUnload: Long,
    val timeLoad: Long,
    val weight: Double
) {
}