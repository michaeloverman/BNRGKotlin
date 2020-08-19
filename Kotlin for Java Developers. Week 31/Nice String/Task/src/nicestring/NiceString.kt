package nicestring

fun String.isNice(): Boolean {
    val one = !this.contains("b[u|a|e]".toRegex())
//    val two = "aeiou".sumBy { c ->
//        this.count { it == c }
//    } >= 3
    val two = count { it in "aeiou" } >= 3 // From course video
//    val three = this.zipWithNext().filter { it.first == it.second }.isNotEmpty()
    val three = this.zipWithNext().any { it.first == it.second } // From course video
    return listOf(one, two, three).count { it } >= 2
}