package seven

/**
 * 题目： 把一个数组最开始的若干个元素搬到数组的末尾， 我们称之数组的旋转。
 * 输入一个递增排序的数组的一个旋转， 输出旋转数组的最小元素。
 * 例如数组{3，4, 5, 1, 2 ｝为｛ l1,2,3, 4，5}的一个旋转，该数组的最小值为1
 *
 * 思路:因为是顺序数组,所以左边的数据大于右边的
 * 什么时候坐标小于右边就找到了最小数
 *
 * 1. 找一个中间位置的数据
 * 2. 左边的数据和右边的比较,如果左边小于右边的,则该数为最小,
 * 3. 如果左边小于中间,则左边的数的角标移动到中间数
 * 4. 如果中间小于右边,则右边数的下角标移动到中间,
 * 5. 否则左边数下角标自增
 */
object ArrayTraversal {

    @JvmStatic
    fun main(args: Array<String>) {
        // 典型输入，单调升序的数组的一个旋转
        var array1 = intArrayOf(3, 4, 5, 1, 2)
        System.out.println(minNumberInRotateArray(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        var array2 = intArrayOf(3, 4, 5, 1, 1, 2)
        System.out.println(minNumberInRotateArray(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        var array3 = intArrayOf(3, 4, 5, 1, 2, 2)
        System.out.println(minNumberInRotateArray(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        var array4 = intArrayOf(1, 0, 1, 1, 1)
        System.out.println(minNumberInRotateArray(array4));

        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        var array5 = intArrayOf(1, 2, 3, 4, 5)
        System.out.println(minNumberInRotateArray(array5));

        // 数组中只有一个数字
        var array6 = intArrayOf(2)
        System.out.println(minNumberInRotateArray(array6));

        // 数组中数字都相同
        var array7 = intArrayOf(1, 1, 1, 1, 1, 1, 1)
        System.out.println(minNumberInRotateArray(array7));
    }

    /**
     * 二分法查找,不对缩小数组的长度
     */
    fun minNumberInRotateArray(rotateArray: IntArray): Int {
        if (rotateArray.isEmpty()) {
            return -1
        }
        var left = 0
        var right = rotateArray.size - 1
        while (left < right) {
            if (rotateArray[left] < rotateArray[right])
                return rotateArray[left]
            val mid = left + (right - left) / 2
            if (rotateArray[left] < rotateArray[mid]) {
                left = mid + 1
            } else if (rotateArray[mid] < rotateArray[right]) {
                right = mid
            } else {
                left++
            }
        }
        return rotateArray[left];
    }
}