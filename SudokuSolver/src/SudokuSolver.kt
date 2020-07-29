import kotlin.system.measureNanoTime

var matrix = arrayOf(
    arrayOf(8, 0, 0, 0, 0, 0, 0, 0, 0),
    arrayOf(0, 0, 3, 6, 0, 0, 0, 0, 0),
    arrayOf(0, 7, 0, 0, 9, 0, 2, 0, 0),
    arrayOf(0, 5, 0, 0, 0, 7, 0, 0, 0),
    arrayOf(0, 0, 0, 0, 4, 5, 7, 0, 0),
    arrayOf(0, 0, 0, 1, 0, 0, 0, 3, 0),
    arrayOf(0, 0, 1, 0, 0, 0, 0, 6, 8),
    arrayOf(0, 0, 8, 5, 0, 0, 0, 1, 0),
    arrayOf(0, 9, 0, 0, 0, 0, 4, 0, 0)
)

fun main() {
    val runs = 100;
    var totalTime: Long = 0;
    for (x in 1..runs) {
        totalTime += measureNanoTime { solve() }
    }
    println("Average solve time over $runs runs: ${totalTime / runs}")
//    println("Total branches checked: $branchesTested")
}

//var branchesTested = 0
fun solve() {
//    branchesTested++
    // Check every square
    for (x in 0..8) {
        for (y in 0..8) {
            // If it's empty...
            if (matrix[x][y] == 0) {
                // ...check all options
                for (value in 1..9) {
                    // If it's legal...
                    if (possible(x,y,value)) {
                        // ...fill it in...
                        matrix[x][y] = value
                        // ...and solve the now reduced matrix
                        solve()
                        matrix[x][y] = 0
                    }
                }
                return
            }
        }
    }
//    printMatrix()
//    print("More? ")
//    readLine()
}

fun possible(x: Int, y: Int, value: Int): Boolean {
    // check row
    for (i in 0..8) {
        if (matrix[x][i] == value) return false
    }
    // check column
    for (i in 0..8) {
        if (matrix[i][y] == value) return false
    }
    // check box
    val boxX = (x / 3) * 3
    val boxY = (y / 3) * 3
    for (i in boxX..(boxX + 2)) {
        for (j in boxY..(boxY + 2)) {
            if (matrix[i][j] == value) return false
        }
    }
    return true
}

fun printMatrix() {
    for (x in 0..8) {
        val horzspacer = if (x % 3 == 0) "+-----------+-----------+-----------+" else "|           |           |           |"
        println(horzspacer)
        for (y in 0..8) {
            val vertspacer = if (y % 3 == 0) "|" else " "
            print(vertspacer)
            print(" ${matrix[x][y]} ")
        }
        println("|")
    }
    println("+-----------+-----------+-----------+")
}