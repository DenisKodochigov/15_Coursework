import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val runningProgram = true
    runBlocking {
        launch { loadPort(0, runningProgram, 1) }
        launch { loadPort(1, runningProgram,5) }
        launch { loadPort(2, runningProgram,9) }
        while (runningProgram) {
            delay(1000)
            println("MAIN")
        }
        //Unload truck
    }
}

// Программа для перевалки товара из грузовика на склад
suspend fun loadPort(numberPort: Int, runningProgram: Boolean, perQ:Int) {
    var per = perQ
    while (runningProgram) {
        delay(1000)
        per++
        println("LOADPORT $numberPort $per")
    }
}
