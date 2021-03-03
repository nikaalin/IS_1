import java.io.File
import java.lang.Exception
import java.util.*

var abc = rus

fun main() {
    while (true) {
        print(titleText)
        print(enterModeText)
        val cipherMode = readLine()?.toIntOrNull()
        when (cipherMode) {
            1, 2 -> program(cipherMode)
            3 -> changeLanguage()
            4 -> return
            else -> print(exceptionText)
        }

    }
}

private fun program(cipherMode: Int) {
    val isDecipherMode = cipherMode == 2

    print(enterKeyCountText)
    var keyCount = readLine()?.toIntOrNull()
    while (keyCount == null) {
        print(exceptionText)
        keyCount = readLine()?.toIntOrNull()
    }

    print(enterKeyText)
    val keyList = mutableListOf<String>()
    var i = 0
    keyWord@ while (i < keyCount) {
        val keyWord = readLine() ?: "@"
        keyLetter@ for (letter in keyWord) {
            if (!abc!!.contains(letter.toLowerCase())) {
                print(exceptionText)
                continue@keyWord
            }
        }
        keyList.add(keyWord)
        i++
    }

    val text = readText()
    print(inputText)
    print(text + "\n")

    val result = if (isDecipherMode) compositeDecrypt(keyList, text, abc!!)
    else compositeEncrypt(keyList, text, abc!!)

    print(outputText)
    print(result + "\n")

    print(saveText)
    while (true) {
        when (readLine()?.toIntOrNull()) {
            1 -> {
                saveText(result)
                break
            }
            2 -> break
            else -> {
                print(exceptionText)
                continue
            }
        }
    }

}

private fun readText(): String{
    print(enterTextText)
    val filePath = readLine()
    return try {
        File(filePath!!).readText()
    } catch (e: Exception){
        print(exceptionFileText)
        readText()
    }
}

private fun saveText(text: String){
    print(enterTextText)
    val filePath = readLine()
    try {
        File(filePath!!).writeText(text)
        print(resultText)
    } catch (e: Exception){
        print(exceptionFileText)
        saveText(resultText)
    }
}

private fun changeLanguage() {
    print(enterLanguageText)
        abc =
            when (readLine()?.toIntOrNull()) {
                1 -> rus
                2 -> en
                else -> {
                    print(exceptionText)
                    changeLanguage()
                    abc
                }
            }
}

private const val titleText = "Шифрование/Дешифрация текста методом Виженера с составным ключом\n "
private const val enterModeText = "Выберите режим:\n 1. Шифрование\n 2. Дешифрация\n 3. Сменить язык файла\n 4. Выход\n"
private const val enterKeyCountText = "Введите количество используемых ключей:\n"
private const val enterKeyText = "Введите ключи (один ключ на строку без пробелов):\n"
private const val exceptionText = "Некорректный ввод. Попробуйте еще раз:\n"
private const val exceptionFileText = "Невозможно использовать этот путь. Попробуйте еще раз:\n"
private const val enterTextText = "Введите путь файла:\n"
private const val enterLanguageText = "Выберите язык файла:\n 1. Русский\n 2. English\n"
private const val saveText = "Сохранить результат?\n 1. Да\n 2. Нет\n"
private const val resultText = "Результат записан в файл\n"
private const val inputText = "Содержимое файла:\n"
private const val outputText = "Результат:\n"