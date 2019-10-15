package com.offer

/**
 *
 * 排序算法共有八大类,即冒泡排序,选择排序,快速排序,插入排序,希尔排序,归并排序,基数排序以及堆排序;另外还有桶排序.
 */
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
class BubbleSortAlgorithm{
    fun bubbleSort(arrays:IntArray):IntArray{
        for ( i in 0 until arrays.size){
            for (j in 0 until arrays.size-1-i){

            }
        }
        return arrays;
    }
}

/**
 * 将待排序的数列分成若干个长度为1的子数列，然后将这些数列两两合并；
 * 得到若干个长度为2的有序数列，再将这些数列两两合并；
 * 得到若干个长度为4的有序数列，再将它们两两合并；直接合并成一个数列为止。
 *
 * 归并排序中，用到了一个临时数组，故空间复杂度为O(N)
 * 由归并排序的递归公式：T(N) = 2T(N/2) + O(N) 可知时间复杂度为O(NlogN)
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