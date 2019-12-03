package com.offer.algorithm

import sun.misc.LRUCache
import java.util.*
import java.util.Arrays


/**
 *
 * 排序算法共有八大类,即冒泡排序,选择排序,快速排序,插入排序,希尔排序,归并排序,基数排序以及堆排序;另外还有桶排序.
 */
object SortAlgorithmTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputArr = intArrayOf(45, 23, 11, 89, 77, 98, 4, 28, 65, 43, 8, 320, 234, -5, -15, -354, 0)
//        val mms = MergeSortAlgorithm().sort(inputArr)
//        BubbleSortAlgorithm().bubbleSort(inputArr)
//        SelectionSortAlgorithm().selectSort(inputArr)
//        InsertionSortAlgorithm().insertSort(inputArr)
//        ShellSortAlgorithm().sort(inputArr)
//        QuickSortAlgorithm().quickSort(inputArr, 0, 10)
//        HeapSortAlgorithm().heapSort(inputArr, 11)
//        CountSortAlgorithm().countSort(inputArr)
//        BucketSortAlgorithm().bucketSort(inputArr)
        RadixSortAlgorithm().radixSort(inputArr)
        for (i in inputArr) {
            print(i)
            print(" ")
        }
    }
}

class RadixSortAlgorithm {

    fun radixSort(arrays: IntArray) {
        if (arrays.size <= 1) return
        var mod = 10
        var maxDigit = getMaxDigit(arrays) // 获取最大数的位数(如果是只有个位,则只排序一次,如果是十位,则排列2次,如果是百位,则排列三次...依次类推)

        var i = 0
        while (i < maxDigit) { // 根据位数排列循环排列数据
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            /***
             *
             *
             *
             * 0    1   2   3   4   5   6   7   8   9  | 10   11   12    13   14    15    16    17    18    19
             */
            val counter = Array(mod * 2) { IntArray(0) } // 二维数组,一维表示存放(个位,十位,百位..相同数据的容器的容器列表),二维分别存放个位,十位相同数据的容器
            // 循环数据,将每个元素(根据个位数,十位数,百位数)放到对应的位置上,
            for (j in 0 until arrays.size) {
                val bucket = (arrays[j] / Math.pow(10.0, i.toDouble()) % 10 + 10).toInt()
                counter[bucket] = arrayAppend(counter[bucket], arrays[j])
            }

            // 将数据取出来,排成一个列表
            var pos = 0
            for (bucket in counter) {
                for (value in bucket) {
                    arrays[pos++] = value
                }
            }
            i++
        }
    }

    private fun arrayAppend(arr: IntArray, value: Int): IntArray {
        var arrs = arr
        arrs = Arrays.copyOf(arr, arr.size + 1)
        arrs[arrs.size - 1] = value
        return arrs
    }

    /**
     * 获取最大位数
     */
    private fun getMaxDigit(arrays: IntArray): Int {
        val maxValue = getMaxValue(arrays)
        return getNumLength(maxValue.toLong())
    }

    /**
     * 获取数组中最大值
     */
    private fun getMaxValue(arrays: IntArray): Int {
        var maxValue = arrays[0]
        for (value in arrays) {
            if (maxValue < value) {
                maxValue = value
            }
        }
        return maxValue
    }

    /**
     * 获取最大值的位数
     */
    private fun getNumLength(num: Long): Int {
        if (num == 0L) {
            return 1;
        }
        var lenght = 0
        var temp = num
        while (temp != 0L) {
            lenght++
            temp /= 10
        }
        return lenght
    }
}

/**
 * 1.可以先把元素存入桶中,在将每个桶内的数据进行排序
 * 2.在向桶中插入数据时,先比较,然后插入到对应的位置
 */
class BucketSortAlgorithm {
    fun bucketSort(arrays: IntArray) {
        if (arrays.size <= 1) return
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