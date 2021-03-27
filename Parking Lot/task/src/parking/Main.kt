package parking

import java.util.*

val scanner = Scanner(System.`in`)

data class Car(val registration: String, val color: String)

fun main() {
    var spots: MutableList<Car?> = mutableListOf()

    while (true) {
        val action = scanner.next()
        when (action) {
            "exit" -> break
            "status" -> status(spots)
            "create" -> spots = create()
            "leave" -> leave(spots)
            "park" -> park(spots)
            "reg_by_color" -> reg_by_color(spots)
            "spot_by_color" -> spot_by_color(spots)
            "spot_by_reg" -> spot_by_reg(spots)
        }
    }
}

fun spot_by_reg(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val reg = scanner.next()
    var count = 0

    for (i in spots.indices) {
        val car = spots[i]

        if (car != null) {
            if (car.registration.toUpperCase() == reg.toUpperCase()) {
                println("${i + 1}")
                count++
            }
        }
    }
    if (count == 0)
        println("No cars with registration number $reg were found.")
}

fun spot_by_color(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val color = scanner.next()
    var sp = ""

    for (i in spots.indices) {
        val car = spots[i]
        if (car != null) {
            if (car.color.toLowerCase() == color.toLowerCase()) {
                sp += " " + i
            }
        }
    }

    var nnn = 0
    val p = sp.split(" ")

    if (p.size == 0 || (p[0] == "" && p.size == 1))
        println("No cars with color $color were found.")

    else {
        nnn = p[1].toInt() + 1
        print(nnn)
        if (p.size > 2)
            for (i in 2 until p.size) {
                nnn = p[i].toInt() + 1
                print(", " + nnn)
            }
        println()
    }
}

fun reg_by_color(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val color = scanner.next()
    var sp = ""

    for (i in spots.indices) {
        val car = spots[i]
        if (car != null) {
            if (car.color.toLowerCase() == color.toLowerCase()) {
                sp += "${car.registration} "
            }
        }
    }
    if (sp.length == 0)
        println("No cars with color $color were found.")
    else {
        val a= sp.split(" ")
        print(a[0])
        if (a.size > 1)
            for (i in 1 until a.size - 1) {
                    print(", " + a[i])
            }
        println()
    }
}

fun status(spots: List<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val noOfOccupiedSpots = spots.count { car -> car != null }
    if (noOfOccupiedSpots == 0) {
        println("Parking lot is empty.")
        return
    }

    for (i in spots.indices) {
        val car = spots[i]
        if (car != null) {
            println("${i + 1} ${car.registration} ${car.color}")
        }
    }
}

fun create(): MutableList<Car?> {
    val size = scanner.nextInt()
    println("Created a parking lot with $size spots.")
    return MutableList(size) { null }
}

fun leave(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val spot = scanner.nextInt()
    if (spots[spot - 1] == null) {
        println("There is no car in spot $spot.")
    } else {
        spots[spot - 1] = null
        println("Spot $spot is free.")
    }
}

fun park(spots: MutableList<Car?>) {
    if (spots.isEmpty()) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    val firstFreeSpot = spots.indexOfFirst { car -> car == null }
    if (firstFreeSpot < 0) {
        println("Sorry, the parking lot is full.")
        return
    }

    val registration = scanner.next()
    val color = scanner.next()

    spots[firstFreeSpot] = Car(registration, color)

    println("$color car parked in spot ${firstFreeSpot + 1}.")
}