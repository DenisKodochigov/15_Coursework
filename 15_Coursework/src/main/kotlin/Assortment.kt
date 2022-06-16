class Assortment {

    var list = mutableListOf<Product>()
    private val weightLS = 550.0
    private val weightMS = 300.0
    private val weightSS = 100.0
    private val weightF = 50.0
    private val timeUnloadLS = 200.toLong()
    private val timeUnloadMS = 120.toLong()
    private val timeUnloadSS = 100.toLong()
    private val timeUnloadF = 80.toLong()
    private val timeLoadLS = 200.toLong()
    private val timeLoadMS = 120.toLong()
    private val timeLoadSS = 100.toLong()
    private val timeloadF = 80.toLong()

    init {
        list.add(
            Product(
                "Food-1", EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts,
                timeUnloadF, timeloadF, weightF
            )
        )

        list.add(
            Product(
                "Food-2",
                EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts, timeUnloadF, timeloadF, weightF
            )
        )
        list.add(
            Product(
                "Food-3",
                EnumTypeProduct.FOOD,
                EnumCategory.Breads, timeUnloadF, timeloadF, weightF
            )
        )
        list.add(
            Product(
                "Food-4",
                EnumTypeProduct.FOOD,
                EnumCategory.Breads, timeUnloadF, timeloadF, weightF
            )
        )

        list.add(
            Product(
                "SmallSized-1", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, timeUnloadSS, timeLoadSS, weightSS
            )
        )

        list.add(
            Product(
                "SmallSized-2", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, timeUnloadSS, timeLoadSS, weightSS
            )
        )

        list.add(
            Product(
                "MediumSized-1", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, timeUnloadMS, timeLoadMS, weightMS
            )
        )

        list.add(
            Product(
                "MediumSized-2", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, timeUnloadMS, timeLoadMS, weightMS
            )
        )
        list.add(
            Product(
                "MediumSized-3", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, timeUnloadMS, timeLoadMS, weightMS
            )
        )
        list.add(
            Product(
                "MediumSized-4", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, timeUnloadMS, timeLoadMS, weightMS
            )
        )

        list.add(
            Product(
                "LargeSized-1", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, timeUnloadLS, timeLoadLS, weightLS
            )
        )

        list.add(
            Product(
                "LargeSized-2", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, timeUnloadLS, timeLoadLS, weightLS
            )
        )
        list.add(
            Product(
                "LargeSized-3", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, timeUnloadLS, timeLoadLS, weightLS
            )
        )
        list.add(
            Product(
                "LargeSized-4", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, timeUnloadLS, timeLoadLS, weightLS
            )
        )
    }
}
