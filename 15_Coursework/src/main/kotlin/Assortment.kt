class Assortment {

    var list = mutableListOf<Product>()
    var weightTest=200.0

    init {
        list.add(
            Product(
                "Milk",
                EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts,
                1, 2, weightTest
            )
        )

        list.add(
            Product(
                "Bred",
                EnumTypeProduct.FOOD,
                EnumCategory.Breds, 1, 2, weightTest
            )
        )

        list.add(
            Product(
                "Toaster", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 1, 4, weightTest
            )
        )

        list.add(
            Product(
                "Iron", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 1, 5, weightTest
            )
        )

        list.add(
            Product(
                "TV", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 6, weightTest
            )
        )

        list.add(
            Product(
                "MicrowaveOven", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 2, weightTest
            )
        )

        list.add(
            Product(
                "Fridge", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, weightTest
            )
        )

        list.add(
            Product(
                "Sofa", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, weightTest
            )
        )
    }
}
