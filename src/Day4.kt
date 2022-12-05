import java.io.File

fun main() {
    val input = File("src/day4.txt").readLines()
    // Input comes in as a pair of ranges ex: "4-10,6-11"
    val rangesParsed = input
        .map { Pair(it.substringBefore(",").toRange(), it.substringAfter(",").toRange()) }

    val part1 = rangesParsed.map(::isFullyContained).count { it }
    val part2 = rangesParsed.map(::isOverlapping).count { it }

    println(part1)
    println(part2)

}

//convert string "2-4" to 2..4
private fun String.toRange(): IntRange {
    val parsed = this.split("-").map(String::toInt)
    return parsed[0]..parsed[1]
}

private fun isFullyContained(input: Pair<IntRange, IntRange>): Boolean =
    (input.first.all { it in input.second } || input.second.all { it in input.first })

private fun isOverlapping(input: Pair<IntRange, IntRange>): Boolean =
    (input.first.any { it in input.second } || input.second.any { it in input.first })