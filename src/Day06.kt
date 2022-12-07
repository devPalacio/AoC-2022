fun main() {
    val input = readInput("day06")
    println("Part 1 ${findMarker(input[0], 4)}")
    println("Part 2 ${findMarker(input[0],14)}")
}

private fun findMarker(input: String, markerLength: Int): Int {
    input.forEachIndexed { index, _ ->
        val subString = input.subSequence(index, index + markerLength).toSet()
        if (subString.size == markerLength) {
            return@findMarker index + markerLength
        }
    }
    return -1
}

