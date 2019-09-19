package main.two

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val matrix =
                arrayOf(intArrayOf(1, 2, 8, 9), intArrayOf(2, 4, 9, 12), intArrayOf(4, 7, 10, 13), intArrayOf(6, 8, 11, 15))
        println(judgeNumIsExit(matrix, 7)) // 要查找的数在数组中
        println(judgeNumIsExit(matrix, 5)) // 要查找的数不在数组中
        println(judgeNumIsExit(matrix, 1)) // 要查找的数是数组中最小的数字
        println(judgeNumIsExit(matrix, 15)) // 要查找的数是数组中最大的数字
        println(judgeNumIsExit(matrix, 0)) // 要查找的数比数组中最小的数字还小
        println(judgeNumIsExit(matrix, 16)) // 要查找的数比数组中最大的数字还大
        println(judgeNumIsExit(null, 16))
    }

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     *
     * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
     * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
     * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
     * 查找的范围，直到找到要查找的数字，或者查找范围为空。
     *
     * 这样查找的话,会省时间;
     * @param matrix
     * 待查找的数组
     * @param number
     * 要查找的数
     * @return 查找结果，true找到，false没有找到
     */
    private fun judgeNumIsExit(matrix: Array<IntArray>?, number: Int): Boolean {
        if (matrix == null || matrix.size < 1 || matrix[0].size < 1) {
            return false
        }
        val rows = matrix.size
        val columns = matrix[0].size
        var row = 0 // 起始开始的行号
        var column = columns - 1

        // 如果该数字等于要查找的数字，查找过程结束
        // 如果该数字大于要查找的数字，剔除这个数字所在的列
        // 如果该数字小于要查找的数字，剔除这个数字所在的行。
        while (row >= 0 && row < rows && column >= 0 && column < columns) {
            if (number == matrix[row][column]) {
                return true
            } else if (matrix[row][column] > number) {
                column--
            } else {
                row++
            }
        }

        return false
    }

}
