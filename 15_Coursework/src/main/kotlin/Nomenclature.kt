object Nomenclature {

    var list = mutableListOf<Product>()
    private const val weightLS = 550.0
    private const val weightMS = 300.0
    private const val weightSS = 100.0
    private const val weightF = 50.0
    private const val timeUnloadLS = 200.toLong()
    private const val timeUnloadMS = 120.toLong()
    private const val timeUnloadSS = 100.toLong()
    private const val timeUnloadF = 80.toLong()
    private const val timeLoadLS = 180.toLong()
    private const val timeLoadMS = 100.toLong()
    private const val timeLoadSS = 80.toLong()
    private const val timeloadF = 50.toLong()

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