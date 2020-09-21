package com.example.alphabetboard.ui.main

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MainViewModelTest {

    @Test
    fun `given provided width not bigger than zero when getItemSize() called than throw IllegalArgumentException`() {
        val vm = MainViewModel()
        assertFailsWith(IllegalArgumentException::class) {
            vm.getItemsMatrix(0, 100)
        }
    }

    @Test
    fun `given provided height not bigger than zero when getItemSize() called than throw IllegalArgumentException`() {
        val vm = MainViewModel()
        assertFailsWith(IllegalArgumentException::class) {
            vm.getItemsMatrix(1200, 0)
        }
    }

    @Test
    fun `given provided width = 2000 and height = 2400 when getItemSize() called than return array(500,400)`() {
        val vm = MainViewModel()
        val result = vm.getItemsMatrix(2000, 2400)
        assertEquals(500, result[0])
        assertEquals(400, result[1])
    }

    @Test
    fun `given provided width = 2400 and height = 2000 when getItemSize() called than return array(400,500)`() {
        val vm = MainViewModel()
        val result = vm.getItemsMatrix(2400, 2000)
        assertEquals(400, result[0])
        assertEquals(500, result[1])
    }

    @Test
    fun `given when generateLetterTiles() called than returned list's size is 24`() {
        val vm = MainViewModel()
        val list = vm.generateLetterTiles()
        assertEquals(24, list.size)
    }

    @Test
    fun `given when generateLetterTiles() called than list's first element's letter is 'A'`() {
        val vm = MainViewModel()
        val list = vm.generateLetterTiles()
        assertEquals('A', list.first().letter)
    }

    @Test
    fun `given when generateLetterTiles() called than list's first element's letter is 'Z'`() {
        val vm = MainViewModel()
        val list = vm.generateLetterTiles()
        assertEquals('Z', list.last().letter)
    }
}