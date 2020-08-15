fun main(args: Array<String>) {
    val valuesToAdd = listOf(1, 18, 73, 3, 44, 6, 1, 33, 2, 22, 5, 7)
        .filter { it >= 5 }.windowed(2, 2).map { it[0] * it[1] }.sum()

    println(valuesToAdd)
}