fun main() {
    val input = readInput("day05")

    val crates = input.takeWhile { it.contains("[") }
    val totalRows = input.first { it.contains(" 1") }.trim().last().toString().toInt()
    val rawCommands = input.filter { it.startsWith('m') }
    // create holding structure for data
    val crateStructure9000 = List(totalRows) { ArrayDeque<Char>() }
    val crateStructure9001 = List(totalRows) { ArrayDeque<Char>() }

    for (crate in crates) {
        val chunked = (crate.chunked(4)).map(::removeBrackets).map(String::first)
        for ((index, chunk) in chunked.withIndex()) {
            if (chunk == ' ') continue
            crateStructure9000[index].add(chunk)
            crateStructure9001[index].add(chunk)
        }
    }

    rawCommands.forEach { command ->
        moveCrates(command, crateStructure9000, "9000")
    }

    var part1 = ""
    crateStructure9000.forEach { part1 += it.first() }
    println("Part one is $part1")

    rawCommands.forEach { command ->
        moveCrates(command, crateStructure9001, "9001")
    }
    var part2 = ""
    crateStructure9001.forEach { part2 += it.first() }
    println("Part two is $part2")
}

private fun moveCrates(command: String, crates: List<ArrayDeque<Char>>, model: String): Unit {
    val numCrates = command.substringAfter("move ").substringBefore(" from").trim().toInt()
    val fromStack = command.substringAfter("from ").substringBefore(" to").trim().toInt()
    val toStack = command.substringAfter("to ").trim().toInt()

    when (model) {
        "9000" -> {
            for (i in 1..numCrates) {
                val popped = crates[fromStack - 1].removeFirst()
                crates[toStack - 1].addFirst(popped)
            }
        }
        "9001" -> {
            val popped = ArrayDeque<Char>()
            for (i in 1..numCrates) {
                popped.add(crates[fromStack - 1].removeFirst())
            }
            popped.reverse()
            popped.forEach { crate ->
                crates[toStack - 1].addFirst(crate)
            }
        }
        else -> throw Exception("Not a valid crane model")
    }
}


private fun removeBrackets(input: String): String = input.replace("[", "").replace("]", "")