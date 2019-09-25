package com.offer.five

import java.lang.RuntimeException
import com.offer.five.TraversalTreeNode.BinaryTreeNode




object TraversalTreeNode {
    @JvmStatic
    fun main(args: Array<String>) {
        val preorderTraversal = intArrayOf(1, 2, 4, 5, 3, 6, 7) // 前序
        val inorderTraversal = intArrayOf(4, 2, 8, 1, 6, 3, 7) // 中序
        test1()
    }

    fun test1() {
        var preorder = intArrayOf(1, 2, 4, 7, 3, 5, 6, 8);
        var inorder = intArrayOf(4, 7, 2, 1, 5, 3, 8, 6);
        var root = buildBinaryTreeNode(preorder, inorder);
        printTree(root)
    }

    // 中序遍历二叉树
    fun printTree(root: BinaryTreeNode?) {
        if (root != null) {
            System.out.print(root.value);
            printTree(root.left);
            printTree(root.right)
        }

    }

    fun buildBinaryTreeNode(preorderTraversalNodes: IntArray, inorderTraversalNodes: IntArray): BinaryTreeNode? {
        if (preorderTraversalNodes.isEmpty() || inorderTraversalNodes.isEmpty() || preorderTraversalNodes.size != inorderTraversalNodes.size || inorderTraversalNodes.size < 1) {
            return null
        }
        return buildBinaryTreeNode(
            preorderTraversalNodes,
            0,
            preorderTraversalNodes.size - 1,
            inorderTraversalNodes,
            0,
            inorderTraversalNodes.size - 1
        )
    }

    /**
     * preStartPosition:前序遍历的开始位置
     * preEndPosition:前序遍历的结束位置
     */
    fun buildBinaryTreeNode(
        preorderTraversalNodes: IntArray, preStartPosition: Int, preEndPosition: Int,
        inorderTraversalNodes: IntArray, inStartPosition: Int, inEndPosition: Int
    ): BinaryTreeNode? {
        if (preStartPosition > preEndPosition) return null
        // 取前序遍历的第一个数字，就是当前的根结点
        var value = preorderTraversalNodes[preStartPosition]
        var index = inStartPosition

        // 在中序遍历的数组中找根结点的位置{前序第一个数据是当前的根节点}
        while (index <= inEndPosition && inorderTraversalNodes[index] != value) {
            index++
        }
        // 如果在整个中序遍历的数组中没有找到，说明输入的参数是不合法的，抛出异常
        if (index > inEndPosition) {
            throw RuntimeException("Invalid input,")
        }
        // 创建当前的根结点，并且为结点赋值
        val rootNode = BinaryTreeNode(value)
        // 递归构建当前根结点的左子树，左子树的元素个数：index-inStartPosition个
        // 左子树对应的前序遍历的位置在[preStartPosition+1, preStartPosition+index-inStartPosition]
        // 左子树对应的中序遍历的位置在[inStartPosition, index-1]
        rootNode.left = buildBinaryTreeNode(
            preorderTraversalNodes, preStartPosition + 1, preStartPosition + index - inStartPosition,
            inorderTraversalNodes, inStartPosition, index - 1
        )
        // 递归构建当前根结点的右子树，右子树的元素个数：inEndPosition-index个
        // 右子树对应的前序遍历的位置在[preStartPosition+index-inStartPosition+1, preEndPosition][index-inStartPosition+1:左子树个数+起始位置==右子树起始位置]
        // 右子树对应的中序遍历的位置在[index+1, inEndPosition]
        rootNode.right = buildBinaryTreeNode(
            preorderTraversalNodes, preStartPosition + index - inStartPosition + 1, preEndPosition,
            inorderTraversalNodes, index + 1, inEndPosition
        )
        return rootNode;
    }

    data class BinaryTreeNode(val _value: Int) {
        val value = _value
        var left: BinaryTreeNode? = null
        var right: BinaryTreeNode? = null
    }
}
