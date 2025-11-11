package com.example.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    var score by mutableStateOf(0)
        private set

    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(false)
    private val circleRadius = 100f

    var circleX by mutableStateOf(0f)
    var circleY by mutableStateOf(0f)

    // 設定螢幕寬度與高度
    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
    }

    fun startGame() {

        if (gameRunning) return
        gameRunning = true

        circleX = circleRadius
        circleY = screenHeightPx / 2f

        viewModelScope.launch {
            while (gameRunning) { // 每0.1秒循環
                delay(100)
                circleX += 10f

                if (circleX + circleRadius >= screenWidthPx){
                    score += 1 // 分數 +1
                    circleX = circleRadius // 回到左邊邊界重新開始
                }
            }
        }
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }
    fun getCircleRadius(): Float = circleRadius



}