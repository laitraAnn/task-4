import kotlin.random.Random

fun main() {
    val options = arrayOf("Камень", "Ножницы", "Бумага")

    while (true) {
        for (i in options.indices) {
            println("${i + 1}. ${options[i]}")
        }
        print("Ваш ход: ")
        val playerChoice = readLine()?.toIntOrNull()?.minus(1) ?: -1

        if (playerChoice !in 0..2) {
            println("Неверный ввод! Повторите и выберите 1, 2 или 3.")
            continue
        }

        println("Вы выбрали: ${options[playerChoice]}")

        val computerChoice = Random.nextInt(0, 3)
        println("Компьютер выбрал: ${options[computerChoice]}")

        val result = process(playerChoice, computerChoice)
        if (result != null) {
            println(result)
            break
        }
        println()
    }
}

fun process(playerChoice: Int, computerChoice: Int): String? {
    return when {
        playerChoice == computerChoice -> {
            println("Ничья! Попробуйте снова.")
            null
        }
        (playerChoice == 0 && computerChoice == 1) ||
                (playerChoice == 1 && computerChoice == 2) ||
                (playerChoice == 2 && computerChoice == 0) -> {
            "Вы выиграли!"
        }
        else -> {
            "Компьютер выиграл!"
        }
    }
}