package com.example.alphabetboard.ui.main

import androidx.lifecycle.ViewModel
import kotlin.random.Random.Default.nextInt

class MainViewModel : ViewModel() {
    fun getItemsMatrix(screenWidth: Int, screenHeight: Int): Triple<Int, Int, Int> {
        if (screenWidth <= 0 || screenHeight <= 0) throw IllegalArgumentException()
        val sInRow = if (screenHeight > screenWidth) 4 else 6
        val sInColumn = if (screenHeight > screenWidth) 6 else 4

        val sWidth = screenWidth / sInRow
        val sHeight = screenHeight / sInColumn

        return Triple(sInRow, sWidth, sHeight)
    }

    fun generateLetterTiles(): List<LetterTile> {
        val list = mutableListOf<LetterTile>()
        var letter = 'A'
        for (i in 1..24) {
            if (letter == 'Q' || letter == 'V') {
                letter++
            }
            val item = LetterTile(letter, generateRandomHand())
            list.add(item)
            letter++

        }
        return list
    }

    private fun generateRandomHand(): Char {
        return when (nextInt(3)) {
            0 -> 'B'
            1 -> 'L'
            2 -> 'R'
            else -> 'E'
        }
    }


}