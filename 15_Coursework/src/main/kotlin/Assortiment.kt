class Assortiment {

    var list = mutableListOf<Product>()

    init {
        list.add(
            Product(
                "Milk",
                EnumTypeProduct.FOOD,
                EnumCategory.MilksProducts,
                1, 2, 0.5
            )
        )

        list.add(
            Product(
                "Bred",
                EnumTypeProduct.FOOD,
                EnumCategory.Breds, 1, 2, 0.5
            )
        )

        list.add(
            Product(
                "Toaster", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 3, 4, 1.5
            )
        )

        list.add(
            Product(
                "Iron", EnumTypeProduct.SMALLSIZED,
                EnumCategory.SmallHousehold, 4, 5, 2.5
            )
        )

        list.add(
            Product(
                "TV", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 5, 6, 15.0
            )
        )

        list.add(
            Product(
                "MicrowaveOven", EnumTypeProduct.MEDIUMSIZED,
                EnumCategory.MediumHousehold, 1, 2, 5.5
            )
        )

        list.add(
            Product(
                "Fridge", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, 20.0
            )
        )

        list.add(
            Product(
                "Sofa", EnumTypeProduct.LARGESIZED,
                EnumCategory.LargeHousehold, 1, 2, 50.0
            )
        )
    }
}
