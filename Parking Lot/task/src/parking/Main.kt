package parking

import java.util.Scanner

fun main() {
    var size = 20
    val parking = Parking(size)
    parking.start()
}

class Parking {
    var size: Int
    val spots: MutableMap<Int, String> = mutableMapOf<Int, String>()
    var exit = false
    val scanner = Scanner(System.`in`)
    var input: List<String> = emptyList()
    var minEmpty: Int = 1

    constructor (_size: Int) {
        size = _size
        initializePark()
    }

    fun initializePark() {
        for (i in 1..size) {
            spots[i] = ""
        }
    }

    fun start() {
        while (!exit) {
            input = scanner.nextLine().split(" ")

            when (input[0]) {
                "park" -> park()
                "leave" -> leave(input[1].toInt())
                "exit" -> exit = true
                else -> println("Incorrect input")
            }
        }
    }

    fun park() {
        if (minEmpty == 0) {
            println("Sorry, the parking lot is full.")
            return
        }
        spots[minEmpty] = input[2]
        println("${spots[minEmpty]} car parked in spot ${minEmpty}.")
        findNextEmpty(minEmpty)
    }

    fun leave(spot: Int) {
        if (spots.get(spot) == "")
            println("There is no car in spot $spot.")
        else {
            spots[spot] = ""
            println("Spot $spot is free.")
        }
        minEmptySpot(spot)
    }

    fun findNextEmpty(_minEmpty: Int) {
        for (i in _minEmpty + 1..size) {
            if (spots[i] == "") {
                minEmpty = i
                return
            }
        }
        minEmpty = 0
    }

    private fun minEmptySpot(spot: Int): Int {
        if (minEmpty > spot || minEmpty == 0)
            minEmpty = spot
        return minEmpty
    }
}

