package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
//    var x = this
//    var two = x.filterNotNull()
//    var two2 = two.foldIndexed(mutableListOf<T>()) { idx, list, elem -> if (list.last() == elem) (list.subList(0, idx) + merge(elem)).toMutableList() else (list + elem).toMutableList() }
//    println(two2)
    var l: MutableList<T> = this.filterNotNull().toMutableList()
//    println(l)
    var result = mutableListOf<T>()
    while (l.size > 1) {
        val check = l.take(2)
//        println("Checking: $check")
        if (check[0] == check[1]) {
            result.add(merge(check[0]))
            l.removeAt(0)
            l.removeAt(0)
//            println("Merged: $result, $l")
        } else {
            result.add(check[0])
            l.removeAt(0)
//            println("Not-md: $result, $l")
        }
    }
    if (l.isNotEmpty()) result.add(l.last())
    return result
}

