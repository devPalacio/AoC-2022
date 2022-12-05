import java.io.File

fun main() {
    val newLine = System.lineSeparator()
    val inputFile = File("src/day1.txt")
    val caloriesPerElf = inputFile.readText().trim()
        .split("$newLine$newLine")
        .map { it.split(newLine).map(String::toInt).reduce{a,b -> a+b} }

    val mostCalories = caloriesPerElf.max()
    val top3Calories = caloriesPerElf.sorted().takeLast(3).sum()

    println(mostCalories)
    println(top3Calories)
}
