package six

import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.*
import java.util.Queue

/**
 * 队列是一种特殊的线型表:一个先进先出(FIFO)的数据结构(和栈相反)
 *
 * 实现类图片地址:
 * https://images2017.cnblogs.com/blog/1182892/201711/1182892-20171122100317930-842768608.png
 *
 * 题目:用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 */
class Queue<T> {
    // 插入栈，只用于插入的数据
    private var stack1: Stack<T> = Stack();
    // 弹出栈，只用于弹出数据
    private var stack2: Stack<T> = Stack();

    fun appendTail(t: T) {
        if (t == null) throw IllegalArgumentException("invalid input")
        stack1.add(t)
    }


    /**
     * 还可以修改一下返回bool 判断是否删除成功
     */
    fun deleteHead(): T {
        // 先判断弹出栈是否为空，如果为空就将插入栈的所有数据弹出栈，
        // 并且将弹出的数据压入弹出栈中
        if (stack2.empty())
            while (!stack1.empty()) {
                stack2.add(stack1.pop())
            }
        // 如果弹出栈中还没有数据就抛出异常
        if (stack2.empty()) throw RuntimeException("No more element.")
        return stack2.pop()
    }
}