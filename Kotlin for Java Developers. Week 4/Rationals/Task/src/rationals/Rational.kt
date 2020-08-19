package rationals

import java.math.BigInteger


class Rational(var n: BigInteger, var d: BigInteger) : Comparable<Rational> {
    init {
        require(d != BigInteger.ZERO) { "Denominator cannot be zero." }

        // If denominator is negative, swap both
        if (d < BigInteger.ZERO) {
            n = -n
            d = -d
        }

        // Reduce the fraction
        val gcd = n.gcd(d)
        if (gcd != BigInteger.ONE) {
            n /= gcd
            d /= gcd
        }

    }

    override fun toString() = if (d == BigInteger.ONE) "$n"
        else "$n/$d"

    override operator fun compareTo(other: Rational) =
        if (d == other.d) n.compareTo(other.n)
        else (n * other.d).compareTo(other.n * d)

    override fun equals(other: Any?) =
        if (other == null) false
        else {
            val o = other as Rational
            n == o.n && d == o.d
        }
}

operator fun Rational.plus(other: Rational): Rational =
        Rational((n * other.d) + (other.n * d), d * other.d)

operator fun Rational.minus(other: Rational): Rational =
        Rational((n * other.d) - (other.n * d), d * other.d)

operator fun Rational.times(other: Rational): Rational =
        Rational(n * other.n, d * other.d)

operator fun Rational.div(other: Rational): Rational =
        Rational(n * other.d, d * other.n)

operator fun Rational.unaryMinus(): Rational =
        Rational(-n, d)

infix fun Int.divBy(other: Int) =
        Rational(this.toBigInteger(), other.toBigInteger())
infix fun Long.divBy(other: Long) =
        Rational(this.toBigInteger(), other.toBigInteger())
infix fun BigInteger.divBy(other: BigInteger) =
        Rational(this, other)

fun String.toRational(): Rational {
    val nums = this.split("/")
    return if (nums.size == 1) Rational(nums[0].toBigInteger(), BigInteger.ONE)
    else Rational(nums[0].toBigInteger(), nums[1].toBigInteger())
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3
    println(half)
    println(third)

    val sum: Rational = half + third
    println(sum)
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println((2 divBy -4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

