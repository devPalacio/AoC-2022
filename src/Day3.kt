import java.io.File
import java.lang.Exception

fun main() {
    val input = File("src/day3.txt").readLines()

    val compartmented = input.map {
        it.chunked(it.length / 2).map(String::toSet)
    }

    val elvesGrouped = input.chunked(3).map{it.map(String::toSet)}

    fun findBadge(group: List<Set<Char>>) : Char =
        (group[0] intersect group[1] intersect group[2]).first()

    fun findDupeItem(bag: List<Set<Char>>): Char = (bag[0] intersect bag[1]).first()

    fun letterToPriority(letter: Char): Int {
        return when (letter.code) {
            in 97..122 -> letter.code - 96
            in 65..90 -> letter.code - 38
            else -> throw Exception("Not a valid char")
        }
    }

    val answer = compartmented
            .map(::findDupeItem)
            .map(::letterToPriority)
            .sum()

    val part2 = elvesGrouped
            .map(::findBadge)
            .map(::letterToPriority)
            .sum()

    println(answer)
    print(part2)
}

