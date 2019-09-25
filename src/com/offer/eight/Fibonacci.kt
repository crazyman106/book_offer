package eight

/**
 * 题目一：写一个函数，输入n，求斐波那契数列的第n项。斐波那契数列的定义如下：
 * 图片地址:https://img-blog.csdn.net/20150729082937925?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center
 *
 */
class Fibonacci {

    fun fibonacci1(value: Long): Long {
        if (value == 0L || value == 1L) return value
        if (value < 0) return 0
        return fibonacci1(value - 1) + fibonacci1(value - 2)
    }

    fun fibonacci2(value: Long): Long {
        var result = 0L
        var preOne = 1L
        var preTwo = 0L

        if (value == 0L) return preTwo
        if (value == 1L) return preOne
        for (i in 2  ..  value) {
            result = preOne+preTwo
            preTwo = preOne
            preOne = result
        }
        return result
    }

}