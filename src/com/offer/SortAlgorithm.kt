package com.offer

object SortAlgorithmTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputArr = intArrayOf(45, 23, 11, 89, 77, 98, 4, 28, 65, 43)
        val mms = MergeSortAlgorithm()
        mms.sort(inputArr)
        for (i in inputArr) {
            print(i)
            print(" ")
        }
    }
}

/**
 * 将待排序的数列分成若干个长度为1的子数列，然后将这些数列两两合并；
 * 得到若干个长度为2的有序数列，再将这些数列两两合并；
 * 得到若干个长度为4的有序数列，再将它们两两合并；直接合并成一个数列为止。
 */
class MergeSortAlgorithm {
    // 临时变量,用来存储临时数据
    private var array: IntArray? = null
    private var tempMergArr: IntArray? = null
    private var length: Int = 0

    fun sort(arrays: IntArray) {
        this.array = arrays;
        this.length = arrays.size
        this.tempMergArr = IntArray(length)
        doMergeSort(0, length - 1);
    }

    fun doMergeSort(lowerIndex: Int, higherIndex: Int) {
        if (lowerIndex < higherIndex) {
            val middle = (higherIndex + lowerIndex) / 2;
            // Below step sorts the left side of the array
//            println("a:"+middle)
            doMergeSort(lowerIndex, middle);
            println(array!![middle])
            // Below step sorts the right side of the array
//            println("b:"+middle)
            doMergeSort(middle + 1, higherIndex);
//            println("c:"+middle)
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    fun mergeParts(lowerIndex: Int, middle: Int, higherIndex: Int) {
        for (i in lowerIndex..higherIndex) {
            tempMergArr!![i] = array!![i]
        }
        var i = lowerIndex //左数组起始位置
        var j = middle + 1 //有数组起始位置
        var k = lowerIndex // tempArray起始位置
        while (i <= middle && j <= higherIndex) {
            array!![k++] = if (tempMergArr!![i] <= tempMergArr!![j]) tempMergArr!![i++] else tempMergArr!![j++]
        }
        while (i <= middle) {
            array!![k++] = tempMergArr!![i++]
        }
    }

}