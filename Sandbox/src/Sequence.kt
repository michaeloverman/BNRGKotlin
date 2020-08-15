import kotlin.system.measureNanoTime

fun Int.isPrime(): Boolean {
        (2 until this).map {
                if (this % it == 0) return false
        }
        return true
}
fun main(args: Array<String>) {

        val listInNanos = measureNanoTime {
                val toList = (1..7919).toList().filter { it.isPrime() }.take(1000)
        }

        val sequenceInNanos = measureNanoTime {
                val primes = generateSequence(3) { value -> value + 2 }.filter { it.isPrime() }.take(1000)
        }

        println("List completed in $listInNanos")
        println("Sequence compl in $sequenceInNanos")
}
