package com.bignerdranch.nyethack

fun main(args: Array<String>) {

    val player = Player("schplatz")
    player.castFireball()

    var currentRoom: Room = TownSquare()
    println(currentRoom.description())
    println(currentRoom.load())

    // Aura
    val auraColor = player.auraColor()

    // com.bignerdranch.nyethack.Player status
    printPlayerStatus(player)

    player.auraColor()
}

private fun printPlayerStatus(player: Player) =
    println("(Aura: ${player.auraColor()}) (Blessed: ${if (player.isBlessed) "YES" else "NO"})\n${player.name} ${player.formatHealthStatus()}")


