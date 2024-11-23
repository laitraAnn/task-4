fun main() {
    print("Введите сообщение: ")
    var input = readln().lowercase().filter { it.isLetter() } // Убираем все не буквы
    if (input.length % 2 == 1) {
        input += 'я'
    }

    val chunks = input.chunked(2)
    val strCodes = ArrayList<String>()

    for (twoChars in chunks) {
        val firstCode = determineChar(twoChars[0])
        val secondCode = determineChar(twoChars[1])
        if (firstCode == 0 || secondCode == 0) {
            println("Ошибка: недопустимый символ")
            return
        }
        val result = secondCode + (firstCode - 1) * 31
        strCodes.add(codeToString(result))
    }

    // Выводим пары символов
    println(chunks.joinToString(" "))
    // Выводим закодированные символы
    println(strCodes.joinToString(" "))

    for (strCode in strCodes) {
        val code = stringToCode(strCode)
        val columnCount = 31
        var firstChar = 'a'
        var secondChar = 'a'

        for (currentRow in 0..30) {
            val nextRow = currentRow + 1
            if (currentRow * columnCount < code && nextRow * columnCount >= code) {
                for (column in 1..columnCount) {
                    if (currentRow * columnCount + column == code) {
                        firstChar = determineCode(currentRow + 1)
                        secondChar = determineCode(column)
                        print("$firstChar$secondChar ")
                        break
                    }
                }
                break
            }
        }
    }
}

fun determineCode(Response: Int): Char {
    val codes = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
    return codes[Response - 1]
}

fun determineChar(Response: Char): Int {
    val ch = if (Response == 'ё') 'е' else Response
    val codes = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
    return codes.indexOf(ch) + 1
}

fun codeToString(code: Int): String {
    return String.format("%03d", code)
}

fun stringToCode(strCode: String): Int {
    return strCode.toInt()
}