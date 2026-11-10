package com.example.tictactoe


//Clase de las celdas
data class CellModel(
    val id: Int,
    val position: Int,
    val symbol: String = ""
)
//Clase general del juego
data class BoardModel(
    val cells: List<CellModel>,
    val currentTurn: String = "X",
    val winner: String? = null,        // Simbolos: "X", "O", ""
    val isDraw: Boolean = false,
    val isGameOver: Boolean = false
) {
    //Función que se utiliza para hacer un movimiento
    fun makeMove(position: Int): BoardModel {
        if (isGameOver) return this
        if (cells[position].symbol.isNotEmpty()) return this

        val newCells = cells.map {
            if (it.position == position) it.copy(symbol = currentTurn) else it
        }
        val checked = copy(cells = newCells).checkWinner()

        // Solo pasa el turno si el juego no se ha acabado
        return if (checked.isGameOver) checked
        else checked.copy(currentTurn = if (currentTurn == "X") "O" else "X")
    }

    // Busca una linia en la que se haya ganado
    fun checkWinner(): BoardModel {
        for ((a, b, c) in winningLines) {
            val symbol = cells[a].symbol
            if (symbol.isNotEmpty() &&
                symbol == cells[b].symbol &&
                symbol == cells[c].symbol
            ) {
                return copy(winner = symbol, isGameOver = true)
            }
        }
        if (cells.all { it.symbol.isNotEmpty() }) {
            return copy(isDraw = true, isGameOver = true)
        }
        return this
    }

    companion object {
        //Definicion de las posibles lineas ganadoras
        private val winningLines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),   // lineas
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),   // columnas
            listOf(0, 4, 8), listOf(2, 4, 6)                     // diagonales
        )
        //Función de creación de la tabla
        fun createInitialBoard(): BoardModel =
            BoardModel(cells = List(9) { CellModel(id = it, position = it) })
    }
}