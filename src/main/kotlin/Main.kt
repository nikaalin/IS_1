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

private const val titleText = "����������/���������� ������ ������� �������� � ��������� ������\n "
private const val enterModeText = "�������� �����:\n 1. ����������\n 2. ����������\n 3. ������� ���� �����\n 4. �����\n"
private const val enterKeyCountText = "������� ���������� ������������ ������:\n"
private const val enterKeyText = "������� ����� (���� ���� �� ������ ��� ��������):\n"
private const val exceptionText = "������������ ����. ���������� ��� ���:\n"
private const val exceptionFileText = "���������� ������������ ���� ����. ���������� ��� ���:\n"
private const val enterTextText = "������� ���� �����:\n"
private const val enterLanguageText = "�������� ���� �����:\n 1. �������\n 2. English\n"
private const val saveText = "��������� ���������?\n 1. ��\n 2. ���\n"
private const val resultText = "��������� ������� � ����\n"
private const val inputText = "���������� �����:\n"
private const val outputText = "���������:\n"