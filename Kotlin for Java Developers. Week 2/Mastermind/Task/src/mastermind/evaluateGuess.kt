package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    // AFTER seeing solution in Coursera
    val rightPosition = secret.zip(guess).count { it.first == it.second }

    val correctChars = "ABCDEF".sumBy { c ->
        Math.min(secret.count { it == c }, guess.count { it == c })
    }

    return Evaluation(rightPosition, correctChars - rightPosition)
/* MY First Draft, which passed...
    var right = 0
    var wrong = 0
    var filtGuess = guess
    var mySecret = secret
    // Find correct guesses in correct position
    guess.forEachIndexed { i, c ->
        if (c == mySecret.get(i)) {
            right++
            // replace char, so we don't count them again
            mySecret = mySecret.replaceRange(i, i+1, ".")
            filtGuess = filtGuess.replaceRange(i, i+1, ".")
        }
    }
    // rest of guess's chars
    filtGuess.filter { it != '.' }.forEach { c ->
        if (mySecret.contains(c)) {
            wrong++
            // remove from secret so we only count once
            mySecret = mySecret.replaceFirst(c, '.')
        }
    }
    return Evaluation(right, wrong)

 */
}
