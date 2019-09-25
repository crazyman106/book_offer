package com.offer.nine

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * 通过计算的出 f(n) = 2的n次方
 *
 * 小心使用2的n次方超出位数
 */
object Test2 {

    @JvmStatic
    fun main(args: Array<String>) {
        println(System.currentTimeMillis())
        println((1 shl 35)) //按照32位来的，大于等于32的数是取对32的余数计算
        println(System.currentTimeMillis())
        println(jumpFloor1(10))
        println(System.currentTimeMillis())
        println(Math.pow(2.0,100.0))
    }

    fun jumpFloor1(steps: Long): Long {
        if(steps < 1){
            return -1;
        }else if(steps == 1L){
            return 1;
        }else{
            return 2*jumpFloor1(steps-1);
        }
    }

    fun jumpFloor2(steps: Long): Long {
        return Math.pow(2.0,30.0).toLong()
    }
}