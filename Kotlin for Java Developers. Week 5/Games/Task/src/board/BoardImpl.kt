package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = MySquareBoard(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = MyGameBoard(createSquareBoard(width))

class MyGameBoard<T>(val squareBoard: SquareBoard) : GameBoard<T>, SquareBoard by squareBoard {
    private val map = mutableMapOf<Cell, T?>() // data
    override val width = squareBoard.width // not sure why this is needed...
    init { // initialize map full of nulls for each cell
        squareBoard.getAllCells().forEach {
            map[it] = null
        }
    }

    override fun get(cell: Cell): T? = map[cell]

    override fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return map.filter { predicate(it.value) }.keys.toList()
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        map.forEach { if (predicate(it.value)) return it.key }
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        map.forEach { if (predicate(it.value)) return true }
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        map.forEach { if (!predicate(it.value)) return false }
        return true
    }

}


class MySquareBoard(override val width: Int) : SquareBoard {
    private val cells: List<Cell>
    init {
        val list = mutableListOf<Cell>()
        (1..width).forEach { i ->
            (1..width).forEach { j ->
                list.add(Cell(i, j))
            }
        }
        cells = list.toList()
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (!(i in 1..width && j in 1..width)) return null
        return cells[(i - 1) * width + j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        require (i in 1..width && j in 1..width) { "Cell index out of range." }
        return cells[(i - 1) * width + j - 1]
    }

    override fun getAllCells() = cells

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val range = jRange.filter { it in 1..width }
        var list = mutableListOf<Cell>()
        (range).forEach { j ->
            list.add(getCell(i, j))
        }
        return list.toList()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val range = iRange.filter { it in 1..width }
        var list = mutableListOf<Cell>()
        (range).forEach { i ->
            list.add(getCell(i, j))
        }
        return list.toList()
    }

    override fun Cell.getNeighbour(direction: Direction) = when(direction) {
        UP -> getCellOrNull(i - 1, j)
        DOWN -> getCellOrNull(i + 1, j)
        LEFT -> getCellOrNull(i, j - 1)
        RIGHT -> getCellOrNull(i, j + 1)
    }

}