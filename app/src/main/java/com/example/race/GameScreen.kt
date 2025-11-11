package com.example.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {

    val currentScore = gameViewModel.score // 讀取分數
    val circleRadius = gameViewModel.getCircleRadius() // 讀取半徑
    val myName = "羅婉薰"

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, end = 8.dp)
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "姓名: $myName | 分數: $currentScore",
                fontSize = 18.sp,
                color = Color.Black
            )
        }

        Canvas (modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume() // 告訴系統已經處理了這個事件
                    gameViewModel.MoveCircle( dragAmount.x, dragAmount.y)
                }
            }


        ) {
            // 繪製圓形
            drawCircle(
                color = Color.Red,
                radius =circleRadius,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )
        }


        Text(text = message + gameViewModel.screenWidthPx.toString() + "*"
                + gameViewModel.screenHeightPx.toString())

        Button(
            onClick = { gameViewModel.startGame() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            enabled = !gameViewModel.gameRunning
        ){
            Text(if (gameViewModel.gameRunning) "遊戲進行中..." else "點擊開始遊戲")
        }
        }
    }