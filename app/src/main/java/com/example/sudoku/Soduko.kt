package com.example.sudoku

import android.graphics.Color

val red = "\u001b[31m"
val green = "\u001B[32m"
val reset = "\u001b[0m"
private const val GRID_SIZE = 9
fun main(args: Array<String>) {
    val board = arrayOf(
        intArrayOf(7, 0, 2, 0, 5, 0, 6, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 3, 0, 0, 0),
        intArrayOf(1, 0, 0, 0, 0, 9, 5, 0, 0),
        intArrayOf(8, 0, 0, 0, 0, 0, 0, 9, 0),
        intArrayOf(0, 4, 3, 0, 0, 0, 7, 5, 0),
        intArrayOf(0, 9, 0, 0, 0, 0, 0, 0, 8),
        intArrayOf(0, 0, 9, 7, 0, 0, 0, 0, 5),
        intArrayOf(0, 0, 0, 2, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 7, 0, 4, 0, 2, 0, 3))
    printBoard(board)

    if (solveBoard(board)) {
        println(green + "Solved successfully!" + reset)
    } else {
        println(red + "Unsolvable board :(" + reset)
    }
    printBoard(board)
}

private fun printBoard(board: Array<IntArray>) {
    for (row in 0 until GRID_SIZE) {
        if (row % 3 == 0 && row != 0) {
            println("-----------")
        }
        for (column in 0 until GRID_SIZE) {
            if (column % 3 == 0 && column != 0) {
                print("|")
            }
            print(board[row][column])
        }
        println()
    }
}

private fun isNumberInRow(board: Array<IntArray>, number: Int, row: Int): Boolean {
    for (i in 0 until GRID_SIZE) {
        if (board[row][i] == number) {
            return true
        }
    }
    return false
}

private fun isNumberInColumn(board: Array<IntArray>, number: Int, column: Int): Boolean {
    for (i in 0 until GRID_SIZE) {
        if (board[i][column] == number) {
            return true
        }
    }
    return false
}

private fun isNumberInBox(board: Array<IntArray>, number: Int, row: Int, column: Int): Boolean {
    val localBoxRow = row - row % 3
    val localBoxColumn = column - column % 3
    for (i in localBoxRow until localBoxRow + 3) {
        for (j in localBoxColumn until localBoxColumn + 3) {
            if (board[i][j] == number) {
                return true
            }
        }
    }
    return false
}

private fun isValidPlacement(
    board: Array<IntArray>,
    number: Int,
    row: Int,
    column: Int,
): Boolean {
    return !isNumberInRow(board, number, row) &&
            !isNumberInColumn(board, number, column) &&
            !isNumberInBox(board, number, row, column)
}

private fun solveBoard(board: Array<IntArray>): Boolean {
    for (row in 0 until GRID_SIZE) {
        for (column in 0 until GRID_SIZE) {
            if (board[row][column] == 0) {
                for (numberToTry in 1..GRID_SIZE) {
                    if (isValidPlacement(board, numberToTry, row, column)) {
                        board[row][column] = numberToTry
                        if (solveBoard(board)) {
                            return true
                        } else {
                            board[row][column] = 0
                        }
                    }
                }
                return false
            }
        }
    }
    return true
}



