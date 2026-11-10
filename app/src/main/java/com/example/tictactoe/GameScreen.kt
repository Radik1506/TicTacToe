package com.example.tictactoe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable //Crea la pantalla principal
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    val board by viewModel.board.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Indica turno o quien a ganado, si es el caso
        val message = when {
            board.winner != null -> "Jugador ${board.winner} gana!"
            board.isDraw -> "Es un empate!"
            else -> "Turno: ${board.currentTurn}"
        }
        Text(text = message, fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        BoardGrid(
            cells = board.cells,
            onCellClick = { viewModel.onCellClick(it) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.restartGame() }) {
            Text(text = "Reiniciar", fontSize = 20.sp)
        }
    }
}

//funcion para dibujar la tabla de casillas
@Composable
fun BoardGrid(cells: List<CellModel>, onCellClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = false, //Desactiva el scroll
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        items(cells, key = { it.id }) { cell ->
            CellItemView(cell = cell, onClick = { onCellClick(cell.position) })
        }
    }
}

//Función para mostrar el contenido de una casilla
@Composable
fun CellItemView(cell: CellModel, onClick: () -> Unit) {
    val symbolColor = if (cell.symbol == "X") Color(0xFF1565C0) else Color(0xFFC62828)

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            .border(BorderStroke(2.dp, Color.Gray), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = cell.symbol,
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            color = symbolColor
        )
    }
}