package com.offer.nine

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 思路:
 * 1. 递归,耗时,耗内存
 * 2.
 *      1级台阶有1种方法；
 *      2级台阶有2种方法；
 *      3级台阶有3种方法；
 *      4级台阶有5种方法；
 *      n级台阶有（（n-1)级台阶和(n-2)级台阶）的和
 *
 */
object Test1 {
    @JvmStatic
    fun main(args: Array<String>) {
        println(System.currentTimeMillis())
        println(jumpFloor1(4))
        println(System.currentTimeMillis())
        println(jumpFloor2(4))
        println(System.currentTimeMillis())
    }

    fun jumpFloor1(steps: Long): Long {
        if (steps <= 0) return 0
        if (steps == 1L || steps == 2L) return steps
        return jumpFloor1(steps - 1) + jumpFloor1(steps - 2);
    }

    fun jumpFloor2(steps: Long): Long {
        if (steps <= 0) return 0
        if (steps == 1L || steps == 2L) return steps
        var result: Long = 0L
        var preTwo = 1L
        var preOne = 2L
        for (index in 3..steps) {
            result = preOne + preTwo
            preTwo = preOne
            preOne = result
        }
        return result
    }
}