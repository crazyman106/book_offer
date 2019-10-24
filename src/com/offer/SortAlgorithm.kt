package com.offer

import java.util.*


/**
 *
 * 排序算法共有八大类,即冒泡排序,选择排序,快速排序,插入排序,希尔排序,归并排序,基数排序以及堆排序;另外还有桶排序.
 */
object SortAlgorithmTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputArr = intArrayOf(45, 23, 11, 89, 77, 98, 4, 28, 65, 43, 8)
//        val mms = MergeSortAlgorithm().sort(inputArr)
//        BubbleSortAlgorithm().bubbleSort(inputArr)
//        SelectionSortAlgorithm().selectSort(inputArr)
//        InsertionSortAlgorithm().insertSort(inputArr)
//        ShellSortAlgorithm().sort(inputArr)
//        QuickSortAlgorithm().quickSort(inputArr, 0, 10)
//        HeapSortAlgorithm().heapSort(inputArr, 11)
//        CountSortAlgorithm().countSort(inputArr)
        BucketSortAlgorithm().bucketSort(inputArr)
        for (i in inputArr) {
            print(i)
            print(" ")
        }
    }
}

/**
 * 1.可以先把元素存入桶中,在将每个桶内的数据进行排序
 * 2.在想桶中插入数据时,先比较,然后插入到对应的位置
 */
class BucketSortAlgorithm {
    fun bucketSort(arrays: IntArray) {
        // 计算最大值与最小值
        var max = arrays[0]
        var min = arrays[0]
        for (i in arrays.indices) {
            max = Math.max(max, arrays[i])
            min = Math.min(min, arrays[i])
        }
        // 计算桶的数量
        var bucketNum = (max - min) / arrays.size + 1
        val bucketArr = ArrayList<ArrayList<Int>>(bucketNum) // 桶列表
        for (i in 0 until bucketNum) {
            bucketArr.add(ArrayList())
        }
        // 将每个元素放入桶
        for (i in arrays.indices) {
            val num = (arrays[i] - min) / arrays.size
            bucketArr[num].add(arrays[i])
        }
        // 对每个桶进行排序
        for (i in bucketArr.indices) {
            bucketArr[i].sort()
        }
        // 将桶中的元素赋值到原序列
        var index = 0
        for (i in 0 until bucketArr.size) {
            for (k in 0 until bucketArr[i].size) {
                arrays[index++] = bucketArr[i][k]
            }
        }
    }
}

class CountSortAlgorithm {
    fun countSort(arrays: IntArray) {
        if (arrays.size <= 1) return
        var bias: Int = 0 // bias 偏差
        var min = arrays[0]
        var max = arrays[0]
        for (i in 0 until arrays.size) {
            if (arrays[i] > max) max = arrays[i]
            if (arrays[i] < min) min = arrays[i]
        }

        bias = 0 - min
        var bucket = IntArray(max - min + 1, { value -> 0 })  // bucket 桶
        for (i in 0 until arrays.size) {
            bucket[arrays[i] + bias]++
        }
        var index = 0
        var i = 0
        while (index < arrays.size) {
            if (bucket[i] != 0) {
                arrays[index] = i - bias
                bucket[i]--
                index++
            } else {
                i++
            }
        }
    }
}


class HeapSortAlgorithm {

    /**
     * lastLeftIndex:最后节点左子树,count全部节点数
     */
    fun heapAdjust(arrays: IntArray, s: Int, n: Int) {
        var parentIndexTemp = s
        var temp: Int;
        temp = arrays[s]
        var i = 2 * s  // i为左子树节点
        while (i <= n) {
            if (i < n && arrays[i] < arrays[i + 1]) {
                i++
            }
            if (temp >= arrays[i]) {
                break
            }
            arrays[parentIndexTemp] = arrays[i]
            parentIndexTemp = i;
            i *= 2
        }
        arrays[parentIndexTemp] = temp;
    }

    fun heapSort(arrays: IntArray, count: Int) {
        var i: Int = count / 2
        while (i > 0) {
            heapAdjust(arrays, i, count)
            i--
        }
        i = count
        while (i > 1) {
            swap(arrays, 1, i) // 将根节点最大元素和最后一个节点互换,
            heapAdjust(arrays, 1, i - 1) // 互换后,在把除最后一个最大元素外的所有节点重新构建成大顶堆,
            i--
            // 最后不断循环,直到把所有元素重新排序
        }

    }

    fun swap(arrays: IntArray, s: Int, c: Int) {
        var temp = arrays[s]
        arrays[s] = arrays[c]
        arrays[c] = temp
    }
}

/**
 * 快速排序
 */
class QuickSortAlgorithm {
    fun quickSort(arrays: IntArray, startIndex: Int, endIndex: Int) {
        var pivot = arrays[startIndex]
        var start = startIndex
        var end = endIndex

        while (start < end) {
            while ((start < end) && (arrays[end] > pivot)) {
                end--
            }
            while ((start < end) && (arrays[start] < pivot)) {
                start++
            }
            if ((arrays[start] == arrays[end]) && (start < end)) {
                start++
            } else {
                var temp = arrays[end]
                arrays[end] = arrays[start]
                arrays[start] = temp
            }
        }
        if (start - 1 > startIndex) quickSort(arrays, startIndex, start - 1)
        if (end + 1 < endIndex) quickSort(arrays, end + 1, endIndex)
    }
}

/**
 * 希尔排序
 */
class ShellSortAlgorithm {
    fun sort(arrays: IntArray) {
        if (arrays.size == 0) return
        var gap = arrays.size / 2
        while (gap >= 1) {
            for (i in gap..arrays.size - 1) {
                var j = i - gap
                val temp = arrays[i]
                while (j >= 0 && temp < arrays[j]) {
                    arrays[j + gap] = arrays[j]
                    j = j - gap;
                }
                arrays[j + gap] = temp
            }
            gap /= 2
        }
    }
}

/**
 * 插入排序
 */
class InsertionSortAlgorithm {
    fun insertSort(arrays: IntArray) {
        if (arrays.size == 0) return
        var currentValue = 0
        for (i in 1 until arrays.size) {
            currentValue = arrays[i]
            var preIndex = i - 1
            while (preIndex >= 0 && currentValue < arrays[preIndex]) {
                arrays[preIndex + 1] = arrays[preIndex]
                preIndex--
            }
            arrays[preIndex + 1] = currentValue
        }
    }
}

/**
 * 选择排序
 */
class SelectionSortAlgorithm {
    fun selectSort(arrays: IntArray) {
        if (arrays.size == 0) return
        for (i in 0 until arrays.size) {
            var minIndex = i;
            for (j in i until arrays.size) {
                if (arrays[j] < arrays[minIndex]) {//找到最小的数
                    minIndex = j;  //将最小数的索引保存
                }
            }
            var temp = arrays[minIndex]
            arrays[minIndex] = arrays[i]
            arrays[i] = temp
        }
    }
}

/**
 * 冒泡排序
 */
class BubbleSortAlgorithm {
    fun bubbleSort(arrays: IntArray) {
        if (arrays.size == 0) return
        for (i in 0 until arrays.size) {
            for (j in 0 until arrays.size - 1 - i) {
                if (arrays[j] > arrays[j + 1]) {
                    var temp = arrays[j + 1]
                    arrays[j + 1] = arrays[j]
                    arrays[j] = temp
                }
            }
        }
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
            doMergeSort(lowerIndex, middle);
            println(array!![middle])
            doMergeSort(middle + 1, higherIndex);
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