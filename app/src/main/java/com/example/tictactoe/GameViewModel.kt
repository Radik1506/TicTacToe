package com.example.tictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _board = MutableStateFlow(BoardModel.createInitialBoard())
    val board: StateFlow<BoardModel> = _board.asStateFlow()

    fun onCellClick(position: Int) {
        _board.update { it.makeMove(position) }
    }

    fun restartGame() {
        _board.value = BoardModel.createInitialBoard()
    }
}