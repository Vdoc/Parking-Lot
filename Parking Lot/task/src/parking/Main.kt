package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()
    val str = input.split(" ")

    val spots: MutableMap<Int, String> = mutableMapOf<Int, String>()
    spots[1] = " "
    spots[2] = ""

    when (str[0]) {
        "park" -> println("${str[str.size - 1]} car parked in spot 2.")
        "leave" -> {
            if(spots.get(str[1].toInt()) == "") println("There is no car in spot ${str[1]}.")
            else {
                spots[str[1].toInt()] = ""
                println("Spot 1 is free.")
            }
        }
    }
}
