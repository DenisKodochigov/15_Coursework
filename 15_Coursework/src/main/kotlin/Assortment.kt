class Assortment {

    var list = mutableListOf<Product>()
    var weightTest=500.0

    init {
        list.add(
            Product(
                "Food-1",
                EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts,
                1, 2, weightTest
            )
        )

        list.add(
            Product(
                "Food-2",
                EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts, 1, 1, weightTest
            )
        )
        list.add(
            Product(
                "Food-3",
                EnumTypeProduct.FOOD,
                EnumCategory.Breds, 1, 1, weightTest
            )
        )
        list.add(
            Product(
                "Food-4",
                EnumTypeProduct.FOOD,
                EnumCategory.Breds, 1, 1, weightTest
            )
        )

        list.add(
            Product(
                "SmallSized-1", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 1, 1, weightTest
            )
        )

        list.add(
            Product(
                "SmallSized-2", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 1, 1, weightTest
            )
        )

        list.add(
            Product(
                "MediumSized-1", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 1, weightTest
            )
        )

        list.add(
            Product(
                "MediumSized-2", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 1, weightTest
            )
        )
        list.add(
            Product(
                "MediumSized-3", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 1, weightTest
            )
        )
        list.add(
            Product(
                "MediumSized-4", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 1, weightTest
            )
        )

        list.add(
            Product(
                "LargeSized-1", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, weightTest
            )
        )

        list.add(
            Product(
                "LargeSized-2", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, weightTest
            )
        )
        list.add(
            Product(
                "LargeSized-3", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, weightTest
            )
        )
        list.add(
            Product(
                "LargeSized-4", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 1, weightTest
            )
        )
    }
}
