enum class Tonnage(var tonnage:Int) {
    SMALL(5000),
    MEDIUM(15000),
    LARGE(25000);

    //Ищем два наименьших по грузоподъемности
    fun twoSmallerTonnage():List<Tonnage>{
        val listSmallerTonage= mutableListOf(LARGE,LARGE)

        Tonnage.values().forEach {
            if(listSmallerTonage[0].tonnage > it.tonnage) listSmallerTonage[0] = it
            if(listSmallerTonage[0].tonnage != it.tonnage) {
                if(listSmallerTonage[1].tonnage > it.tonnage) listSmallerTonage[1] = it
            }
        }
        return listSmallerTonage
    }
}