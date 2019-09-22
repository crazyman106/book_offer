package com.offer.four

import java.util.*

/**
 * 输入一个链表的头结点，从尾到头打印链表每个节点的值
 *
 * 什么是链表？
 * 1. 单向链表,它由节点组成,每个节点都包含下一个节点的指针
 * 2. 双向链表,它由节点组成,每个节点包含下一个节点和上一个节点的指针
 *
 * 解析:因为单向链表智能从上一个节点a1查询到下一个节点a2，所以不能从后向前查找。
 *
 * 思路:用一个堆栈存储链表，然后将堆栈中的数据读出；为什么是堆栈？堆栈存储数据是先进后出形式.
 */
object LinkedListTest {

    @JvmStatic
    fun main(args: Array<String>) {
        var head: LinkedNode = LinkedNode()
        head.value = 1
        head.next = LinkedNode()
        head.next?.value = 2
        head.next?.next = LinkedNode()
        head.next?.next?.value = 3
        head.next?.next?.next = LinkedNode()
        head.next?.next?.next?.value = 4
        head.next?.next?.next?.next = LinkedNode()
        head.next?.next?.next?.next?.value = 5;

        printListInversely(head)
    }

    class LinkedNode {
        var value: Int = 0
        var next: LinkedNode? = null

    }

    fun printListInversely(head: LinkedNode?) {
        var head = head;

        var stack: Stack<LinkedNode> = Stack()
        while (head != null) {
            stack.push(head)
            head = head.next;
        }

        while (stack.isNotEmpty()) {
            System.out.print(stack.pop().value.toString() + " ")
        }
    }
}