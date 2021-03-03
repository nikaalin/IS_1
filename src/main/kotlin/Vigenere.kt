
private fun oneSymbolEncrypt(key: String, textSymbol: Char, abc: String, letterIndex: Int): Char {
    val textSymbolNumber = abc.indexOf(textSymbol) // номер буквы сообщания в алфавите
    val keySymbol = key[letterIndex % key.length] // сопостовляемая букве сообщения буква ключа
    val keySymbolNumber = abc.indexOf(keySymbol) // номер буквы ключа в алфавите
    val sum = textSymbolNumber + keySymbolNumber
    val resultSymbolNumber = sum % 33 // номер криптограммы буквы сообщения в алфавите
    return abc[resultSymbolNumber]
}

fun compositeEncrypt(keys: List<String>, text: String, abc: String): String {
    var cipherText = text
    for (key in keys) {
        cipherText = encrypt(key, cipherText, abc)
    }
    return cipherText
}

fun encrypt(key: String, text: String, abc: String): String {
    val upperAbc = abc.toUpperCase()
    val upperKey = key.toUpperCase()
    val lowerAbc = abc
    val lowerKey = key.toLowerCase()

    var result = ""
    var letterIndex = 0

    for (textSymbol in text) //проходим каждый символ сообщения
    {
        when { //определяем регистр буквы и ее наличие в заданном алфавите
            lowerAbc.contains(textSymbol) -> {  //вычисляем криптограмму буквы и прибавляем ее к результату
                result += oneSymbolEncrypt(
                    lowerKey,
                    textSymbol,
                    lowerAbc,
                    letterIndex
                )
                letterIndex++
            }
            upperAbc.contains(textSymbol) -> {
                result += oneSymbolEncrypt(upperKey, textSymbol, upperAbc, letterIndex)
                letterIndex++
            }
            else -> {
                result += textSymbol
            }
        }
    }
    return result
}


private fun oneSymbolDecrypt(key: String, textSymbol: Char, abc: String, letterIndex: Int): Char {
    val textSymbolNumber = abc.indexOf(textSymbol) // номер буквы криптограммы в алфавите
    val keySymbolNumber =
        abc.indexOf(key[letterIndex % key.length]) // сопостовляемая букве криптограммы буква ключа
    val dis = textSymbolNumber - keySymbolNumber
    val resultSymbolNumber = if (dis < 0) dis + 33 else dis // номер буквы искодного сообщения в алфавите
    return abc[resultSymbolNumber]
}

fun compositeDecrypt(keys: List<String>, text: String, abc: String): String {
    var decipherText = text
    for (key in keys) {
        decipherText = decrypt(key, decipherText, abc)
    }
    return decipherText
}

fun decrypt(key: String, text: String, abc: String): String {
    val upperAbc = abc.toUpperCase()
    val upperKey = key.toUpperCase()
    val lowerAbc = abc
    val lowerKey = key.toLowerCase()

    var result = ""
    var letterIndex = 0

    for (textSymbol in text) //проходим каждый символ сообщения
    {
        when { //определяем регистр буквы и ее наличие в заданном алфавите
            lowerAbc.contains(textSymbol) -> {
                result += oneSymbolDecrypt(  //вычисляем расшифровку буквы и прибавляем ее к сообщению
                    lowerKey,
                    textSymbol,
                    lowerAbc,
                    letterIndex
                )
                letterIndex++
            }
            upperAbc.contains(textSymbol) -> {
                result += oneSymbolDecrypt(upperKey, textSymbol, upperAbc, letterIndex)
                letterIndex++
            }
            else -> {
                result += textSymbol
            }
        }
    }
    return result;
}
