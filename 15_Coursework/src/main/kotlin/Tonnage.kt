enum class Tonnage(var volume:Int) {
    SMALL(5000),
    MEDIUM(15000),
    LARGE(25000);

    //Ищем два наименьших по грузоподъемности
    fun twoSmallerTonnage():List<Tonnage>{
        val listSmallerTonage= mutableListOf(LARGE,LARGE)

        Tonnage.values().forEach {
            if(listSmallerTonage[0].volume > it.volume) listSmallerTonage[0] = it
            if(listSmallerTonage[0].volume != it.volume) {
                if(listSmallerTonage[1].volume > it.volume) listSmallerTonage[1] = it
            }
        }
        return listSmallerTonage
    }
}