fun main(args: Array<String>) {
//    val greetingFunction: () -> String = {
//    runSimulation("Gimbal", ::printConstructionCost) { playerName, numBuildings ->
//        val currentYear = 2020
//        println("Adding $numBuildings houses")
//        "Welcome to SimVillage, $playerName! (copyright $currentYear)"
//    }
    runSimulation()
//    println(greetingFunction("Gimbal", 2))
//    runSimulation("Gimbal", greetingFunction)
}

//inline fun runSimulation(playerName: String,  costPrinter: (Int) -> Unit, greetingFunction: (String, Int) -> String) {
//    val numBuildings = (1..3).shuffled().last()  // Randomly selects 1, 2, or 3
//    costPrinter(numBuildings)
//    println(greetingFunction(playerName, numBuildings))
//}
fun runSimulation() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("Gimbal"))
}

fun configureGreetingFunction(): (String) -> String {
    val structureType = "hospitals"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2020
        numBuildings += 1
        println("Adding $numBuildings $structureType")
        "Welcome to SimVillage, Mayor $playerName! (copyright $currentYear)"
    }
}
fun printConstructionCost(numBuildings: Int) {
    val cost = 500
    println("construction cost: ${cost * numBuildings}")
}