package com.offer

import java.util.ArrayList
import java.util.Collections

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val inputArr = intArrayOf(45, 23, 11, 89, 77, 98, 4, 28, 65, 43, 8)
        basket(inputArr)
    }

    fun basket(data: IntArray)//data为待排序数组
    {
        val n = data.size
        val bask = Array(10) { IntArray(n) }
        val index = IntArray(10)
        var max = Integer.MIN_VALUE
        for (i in 0 until n) {
            max = if (max > Integer.toString(data[i]).length) max else Integer.toString(data[i]).length
        }
        var str: String
        for (i in max - 1 downTo 0) {
            for (j in 0 until n) {
                str = ""
                if (Integer.toString(data[j]).length < max) {
                    for (k in 0 until max - Integer.toString(data[j]).length)
                        str += "0"
                }
                str += Integer.toString(data[j])
                bask[str[i] - '0'][index[str[i] - '0']++] = data[j]
            }
            var pos = 0
            for (j in 0..9) {
                for (k in 0 until index[j]) {
                    data[pos++] = bask[j][k]
                }
            }
            var x = 0
            while (x < 10) {
                index[x] = 0
                x++
            }
        }
    }
}
