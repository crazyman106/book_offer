package com.offer.three

/**
 * 请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.”，
 * 则输出“We%20are%20happy.”请实现一个函数，把字符串中的每个空格替换成"%20"，
 * 例如“We are happy.”，则输出“We%20are%20happy.”
 */
object StringInsert {
    @JvmStatic
    fun main(args: Array<String>) {
        println(System.currentTimeMillis())
        println(replaceBlack("ha pp y"))
        println(System.currentTimeMillis())
        println(replaceSpace(StringBuffer("ha pp y")))
        println(System.currentTimeMillis())

    }
    fun replaceSpace(str: StringBuffer?): String? {
        if (str == null) {
            return null
        }
        //新建一个  StringBuilder（其实StringBuffer也行，只是StringBuilder是轻量级的，效率更高）
        val newStr = StringBuilder()
        for (i in 0 until str.length) {
            if (str[i] == ' ') {//判断是否为空
                newStr.append("%20")

            } else {
                newStr.append(str[i])
            }
        }
        return newStr.toString()
    }

    fun replaceBlack(str: String): String {
        if (str == null) {
            return str;
        }
        val charArray = str.toCharArray();
        // 统计字符数组中的空白字符数
        var whiteCount = charArray.filter { it == ' ' }.size
        // 如果空白字符为0
        if (whiteCount == 0) {
            return str;
        }
        var count = charArray.size
        // 替换后的长度
        var targetLength = whiteCount * 2 + str.length
        var temArray = CharArray(targetLength)

        while (--count >= 0 && whiteCount > 0) {
            if (charArray[count] == ' ') {
                temArray[--targetLength] = '0';
                temArray[--targetLength] = '2';
                temArray[--targetLength] = '%';
            } else {
                temArray[--targetLength] = charArray[count];
            }
        }
        return String(temArray);
    }
}