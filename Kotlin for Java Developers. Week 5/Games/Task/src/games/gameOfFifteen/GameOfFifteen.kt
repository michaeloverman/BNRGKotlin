package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game = GameOfFifteen(initializer)

class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)

    override fun initialize() {
        board.getAllCells().forEachIndexed { index, cell ->
            board[cell] = initializer.initialPermutation.getOrNull(index)
        }
    }

    override fun canMove() = true

    override fun hasWon() = board.filter { it != null }.map { board[it] } == (1..15).toList()

    override fun processMove(direction: Direction) {
        var openCell = board.filter { it == null }.first()
        val dir = when (direction) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.RIGHT -> Direction.LEFT
            Direction.LEFT -> Direction.RIGHT
        }
        var neighbor = with(board) { openCell.getNeighbour(dir) } ?: return
        board[openCell] = board[neighbor]
        board[neighbor] = null
    }

    override fun get(i: Int, j: Int): Int? {
        return board[Cell(i, j)]
    }

}