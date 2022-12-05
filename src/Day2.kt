import java.io.File

fun main() {
    val input = File("src/day2.txt").readText().trim()
    val games = input.split(System.lineSeparator())
            .map { game -> game.split(" ") }
            .map { it[0].first() to it[1].first() }

    fun getTotalPoints(games: List<Pair<Char,Char>>) = games.sumOf {
        val shapeScore = getShapeScore(it.second)
        val outcomeScore = getOutcomeScore(it)
        shapeScore + outcomeScore
    }

    val totalPointsWithStrat = getTotalPoints(games.map(::getRightMove))

    println(getTotalPoints(games))
    println(totalPointsWithStrat)
}

fun getShapeScore(letter: Char): Int =
        when (letter) {
            'X' -> 1 // rock
            'Y' -> 2 // paper
            'Z' -> 3 // scissors
            else -> throw Exception("Not a valid shape")
        }

fun getOutcomeScore(game: Pair<Char, Char>): Int {
    when (game.first) {
        'A' -> {
            return when (game.second) {
                'X' -> 3 // draw
                'Y' -> 6 // win
                'Z' -> 0 // loss
                else -> throw Exception("Not a valid outcome")
            }
        }

        'B' -> {
            return when (game.second) {
                'X' -> 0 // paper vs rock
                'Y' -> 3 // paper vs paper
                'Z' -> 6 // paper vs scissor
                else -> throw Exception("Not a valid outcome")
            }
        }

        'C' -> {
            return when (game.second) {
                'X' -> 6 // scissor vs rock
                'Y' -> 0 // scissor vs paper
                'Z' -> 3 // scissor vs scissor
                else -> throw Exception("Not a valid outcome")
            }
        }

        else -> throw java.lang.Exception("Not a valid outcome")
    }
}

fun getRightMove(game: Pair<Char, Char>): Pair<Char, Char> {
    return when (game.second) {
        // X means lose, we need to look at first and pick what loses
        'X' -> game.copy(second = loseStratMap[game.first]!!)
        'Y' -> game.copy(second = tieStratMap[game.first]!!)
        'Z' -> game.copy(second = winStratMap[game.first]!!)
        else -> throw java.lang.Exception("Invalid Game")
    }

}

val loseStratMap = mapOf('A' to 'Z', 'B' to 'X', 'C' to 'Y')
val tieStratMap = mapOf('A' to 'X', 'B' to 'Y', 'C' to 'Z')
val winStratMap = mapOf('A' to 'Y', 'B' to 'Z', 'C' to 'X')