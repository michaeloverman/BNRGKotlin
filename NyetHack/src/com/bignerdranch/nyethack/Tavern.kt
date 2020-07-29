package com.bignerdranch.nyethack

import java.io.File
import java.util.*

const val TAVERN_NAME = "Sparkly's Spackle"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Muffler", "Sandström")
val menuList = File("data/tavern-menu-data.txt").readText().split("\n")
val firstList = File("data/first-names.txt").readText().split("\n")
val lastList = File("data/last-names.txt").readText().split("\n")
val uniquePatrons = mutableSetOf<String>()

val patronGold = mutableMapOf<String, Double>()

fun main(args: Array<String>) {

    printMenu(menuList)

    //for (patron in com.bignerdranch.nyethack.getPatronList) {
    //    println("Good evening, $patron")
    //}
    //com.bignerdranch.nyethack.getPatronList.forEachIndexed { index, patron ->
    //    println("Good evening, $patron - you're #${index + 1} in line.")
    //    com.bignerdranch.nyethack.placeOrder(patron, com.bignerdranch.nyethack.getMenuList.shuffled().first())
    //}
    (0..9).forEach {
        val first = firstList.shuffled().first()
        val last = lastList.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
//    println(com.bignerdranch.nyethack.getUniquePatrons)
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(uniquePatrons.shuffled().first(), menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalances()
}

fun performPurchase(price: Double, patronName: String): Boolean {
//    com.bignerdranch.nyethack.displayBalance()
    val totalPurse = patronGold.getValue(patronName)
    if (totalPurse - price < 0.0) return false
    patronGold[patronName] = totalPurse - price
    return true
   //
   // println("Total purse: $totalPurse")
   // println("Purchasing item for $price")

   // val remainingBalance = totalPurse - price
   // println("Remaining balance: ${"%.2f".format(remainingBalance)}")

   // val remainingGold = remainingBalance.toInt()
   // val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
   // com.bignerdranch.nyethack.getPlayerGold = remainingGold
   // com.bignerdranch.nyethack.getPlayerSilver = remainingSilver
   // com.bignerdranch.nyethack.displayBalance()
}

fun displayBalance() {
    println("com.bignerdranch.nyethack.Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price."
    println(message)

    if (performPurchase(price.toDouble(), patronName)) {

        val phrase = if (name == "Dragon's Breath") {
            "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name! Your mother is a toad's wart's nit picker...")}"
        } else {
            "$patronName says: Thanks for the $name"
        }
        println(phrase)
    } else {
        uniquePatrons.remove(patronName)
        println("Bouncer removes $patronName from the premises...")
    }
//    println('\u0954')
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[AEIOUaeiou]")) {
        when (it.value) {
            "a" -> "4"
            "A" -> "4"
            "e" -> "3"
            "E" -> "3"
            "i" -> "1"
            "I" -> "1"
            "o" -> "0"
            "O" -> "0"
            "u" -> "|_|"
            "U" -> "|_|"
            else -> it.value
        }
    }

private fun printMenu(menu: List<String>) {
    val header = "*** Welcome to $TAVERN_NAME ***"
    val width = header.length
    var types = mutableListOf<String>()
    var names = mutableListOf<String>()
    var prices = mutableListOf<String>()
    menu.forEach() {
        val list = it.split(',')
        types.add(list[0])
        names.add(list[1])
        prices.add(list[2])
    }

    println(header)
    names.forEachIndexed { index, s ->
        val n = width - (s.length + prices[index].length)
        print(s.capitalize(Locale.ENGLISH))
        print(".".repeat(n))
        println(prices[index])
    }
    println()
}

private fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}
