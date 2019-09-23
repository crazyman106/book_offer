# 二叉树(Binary Tree)

> 每个节点最多有两个子树的树结构.通常子树被称作“左子树”（left subtree）和“右子树”（right subtree）。二叉树常被用于实现二叉查找树和二叉堆。
> 一棵深度为k，且有2^k-1个结点的二叉树，称为满二叉树。这种树的特点是每一层上的结点数都是最大结点数。而在一棵二叉树中，除最后一层外，若其余层都是满的，并且或者最后一层是满的，或者是在右边缺少连续若干结点，则此二叉树为完全二叉树。具有n个结点的完全二叉树的深度为floor(log2n)+1。深度为k的完全二叉树，至少有2k-1个叶子结点，至多有2k-1个结点。

## 定义
2^k - 1 二叉树是一个连通的无环图,并且每一个节点的度不大于3.有根二叉树还要满足根节点的度不大于2.有了根节点之后,每个顶点定义了唯一的父节点和最多的2个子节点.然后,没有足够的信息来区分左节点和右节点.如果不考虑连通性,允许图中有多个连通分量,这样的结构叫做森林.
## 基本概念
二叉树是递归定义的，其结点有左右子树之分，逻辑上二叉树有五种基本形态：

1. 空二叉树——如图(a)；
2. 只有一个根结点的二叉树——如图(b)；
3. 只有左子树——如图(c)
4. 只有右子树——如图(d)
5. 完全二叉树——如图(e)

![二叉树](https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D250/sign=d3868698e71190ef05fb95dafe1a9df7/314e251f95cad1c8f135aa527f3e6709c93d513c.jpg)

### 类型
1. 完全二叉树——若设二叉树的高度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第h层有叶子结点，并且叶子结点都是从左到右依次排布，这就是完全二叉树。
2. 满二叉树——除了叶结点外每一个结点都有左右子叶且叶子结点都处在最底层的二叉树。
3. 平衡二叉树——平衡二叉树又被称为AVL树（区别于AVL算法），它是一棵二叉排序树，且具有以下性质：它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。

## 相关术语

* 树的结点（node）：包含一个数据元素及若干指向子树的分支；
* 孩子结点（child node）：结点的子树的根称为该结点的孩子；
* 双亲结点：B 结点是A 结点的孩子，则A结点是B 结点的双亲；
* 兄弟结点：同一双亲的孩子结点； 堂兄结点：同一层上结点；
* 祖先结点: 从根到该结点的所经分支上的所有结点
* 子孙结点：以某结点为根的子树中任一结点都称为该结点的子孙
* 结点层：根结点的层定义为1；根的孩子为第二层结点，依此类推；
* 树的深度：树中最大的结点层
* 结点的度：结点子树的个数
* 树的度： 树中最大的结点度。
* 叶子结点：也叫终端结点，是度为 0 的结点；
* 分枝结点：度不为0的结点；
* 有序树：子树有序的树，如：家族树；
* 无序树：不考虑子树的顺序；

## 二叉树性质
1. 在非空二叉树中，第i层的结点总数不超过
, i>=1；
2. 深度为h的二叉树最多有
个结点(h>=1)，最少有h个结点；
3. 对于任意一棵二叉树，如果其叶结点数为N0，而度数为2的结点总数为N2，则N0=N2+1；
4. 具有n个结点的完全二叉树的深度为
（注：[ ]表示向下取整）
5. 有N个结点的完全二叉树各结点如果用顺序方式存储，则结点之间有如下关系：
若I为结点编号则 如果I>1，则其父结点的编号为I/2；
如果2*I<=N，则其左孩子（即左子树的根结点）的编号为2*I；若2*I>N，则无左孩子；
如果2*I+1<=N，则其右孩子的结点编号为2*I+1；若2*I+1>N，则无右孩子。
6. 给定N个结点，能构成h(N)种不同的二叉树。
h(N)为卡特兰数的第N项。h(n)=C(2*n，n)/(n+1)。
7. 设有i个枝点，I为所有枝点的道路长度总和，J为叶的道路长度总和J=I+2i [2]  

## 存储结构

### 顺序存储方式
```C++
typenode=record
data:datatype
l,r:integer;
end;
vartr:array[1..n]ofnode;
```

### 链表存储方式
```C++
typebtree=^node；
node=record
data:datatye;
lchild,rchild:btree;
end;
```

## 二叉树遍历
遍历是对树的一种最基本的运算，所谓遍历二叉树，就是按一定的规则和顺序走遍二叉树的所有结点，使每一个结点都被访问一次，而且只被访问一次。由于二叉树是非线性结构，因此，树的遍历实质上是将二叉树的各个结点转换成为一个线性序列来表示。
设L、D、R分别表示遍历左子树、访问根结点和遍历右子树， 则对一棵二叉树的遍历有三种情况：DLR（称为先根次序遍历），LDR（称为中根次序遍历），LRD （称为后根次序遍历）。

* 前序遍历（DLR，lchild,data,rchild），是二叉树遍历的一种，也叫做先根遍历、先序遍历、前序周游，可记做根左右。前序遍历首先访问根结点然后遍历左子树，最后遍历右子树
  若二叉树为空则结束返回，否则：
  
    * 访问根结点。
    * 前序遍历左子树。
    * 前序遍历右子树 。

![](https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=7e9edc97a9773912c0268263c8188675/3c6d55fbb2fb4316e5bfe05020a4462309f7d37c.jpg)

前序遍历结果：ABDECF

**已知后序遍历和中序遍历，就能确定前序遍历。**
```java
package test;
//前序遍历的递归实现与非递归实现
import java.util.Stack;
public class Test 
{
	public static void main(String[] args)
	{
		TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
		for(int i = 0; i < 10; i++)
		{
			node[i] = new TreeNode(i);
		}
		for(int i = 0; i < 10; i++)
		{
			if(i*2+1 < 10)
				node[i].left = node[i*2+1];
			if(i*2+2 < 10)
				node[i].right = node[i*2+2];
		}
		preOrderRe(node[0]);
	}
	
	public static void preOrderRe(TreeNode biTree)
	{//递归实现
		System.out.println(biTree.value);
		TreeNode leftTree = biTree.left;
		if(leftTree != null)
		{
			preOrderRe(leftTree);
		}
		TreeNode rightTree = biTree.right;
		if(rightTree != null)
		{
			preOrderRe(rightTree);
		}
	}
	
	public static void preOrder(TreeNode biTree)
	{//非递归实现
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(biTree != null || !stack.isEmpty())
		{
			while(biTree != null)
			{
				System.out.println(biTree.value);
				stack.push(biTree);
				biTree = biTree.left;
			}
			if(!stack.isEmpty())
			{
				biTree = stack.pop();
				biTree = biTree.right;
			}
		}
	}
}
 
class TreeNode//节点结构
{
	int value;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int value)
	{
		this.value = value;
	}
}
```

* 中序遍历
中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。在二叉树中，先左后根再右。巧记：左根右。
中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树.若二叉树为空则结束返回，
否则：
    * 中序遍历左子树
    * 访问根结点
    * 中序遍历右子树
    
![](https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=cf18e0e28735e5dd942ca2dd46c7a7f5/4034970a304e251f1510e448a586c9177e3e539e.jpg)

中序遍历结果：DBEAFC    
```java
import java.util.Stack;
public class Test 
{
	public static void main(String[] args)
	{
		TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
		for(int i = 0; i < 10; i++)
		{
			node[i] = new TreeNode(i);
		}
		for(int i = 0; i < 10; i++)
		{
			if(i*2+1 < 10)
				node[i].left = node[i*2+1];
			if(i*2+2 < 10)
				node[i].right = node[i*2+2];
		}
		
		midOrderRe(node[0]);
		System.out.println();
		midOrder(node[0]);
	}
	
	public static void midOrderRe(TreeNode biTree)
	{//中序遍历递归实现
		if(biTree == null)
			return;
		else
		{
			midOrderRe(biTree.left);
			System.out.println(biTree.value);
			midOrderRe(biTree.right);
		}
	}
	
	
	public static void midOrder(TreeNode biTree)
	{//中序遍历费递归实现
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(biTree != null || !stack.isEmpty())
		{
			while(biTree != null)
			{
				stack.push(biTree);
				biTree = biTree.left;
			}
			if(!stack.isEmpty())
			{
				biTree = stack.pop();
				System.out.println(biTree.value);
				biTree = biTree.right;
			}
		}
	}
}
 
class TreeNode//节点结构
{
	int value;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int value)
	{
		this.value = value;
	}
}
```

* 后序遍历
后序遍历（LRD）是二叉树遍历的一种，也叫做后根遍历、后序周游，可记做左右根。后序遍历有递归算法和非递归算法两种。在二叉树中，先左后右再根。巧记：左右根。
若二叉树为空则结束返回，
否则：
    - 后序遍历左子树
    - 后序遍历右子树
    - 访问根结点
![](https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=cf18e0e28735e5dd942ca2dd46c7a7f5/4034970a304e251f1510e448a586c9177e3e539e.jpg)

后序遍历结果：DEBFCA
已知前序遍历和中序遍历，就能确定后序遍历。

算法核心思想：<br/>
首先要搞清楚先序、中序、后序的非递归算法共同之处：用栈来保存先前走过的路径，以便可以在访问完子树后,可以利用栈中的信息,回退到当前节点的双亲节点,进行下一步操作。

后序遍历的非递归算法是三种顺序中最复杂的，原因在于，后序遍历是先访问左、右子树,再访问根节点，而在非递归算法中，利用栈回退到时，并不知道是从左子树回退到根节点，还是从右子树回退到根节点，如果从左子树回退到根节点，此时就应该去访问右子树，而如果从右子树回退到根节点，此时就应该访问根节点。所以相比前序和后序，必须得在压栈时添加信息，以便在退栈时可以知道是从左子树返回，还是从右子树返回进而决定下一步的操作。

```java
import java.util.Stack;
public class Test 
{
	public static void main(String[] args)
	{
		TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
		for(int i = 0; i < 10; i++)
		{
			node[i] = new TreeNode(i);
		}
		for(int i = 0; i < 10; i++)
		{
			if(i*2+1 < 10)
				node[i].left = node[i*2+1];
			if(i*2+2 < 10)
				node[i].right = node[i*2+2];
		}
		
		postOrderRe(node[0]);
		System.out.println("***");
		postOrder(node[0]);
	}
	
	
	
	public static void postOrderRe(TreeNode biTree)
	{//后序遍历递归实现
		if(biTree == null)
			return;
		else
		{
			postOrderRe(biTree.left);
			postOrderRe(biTree.right);
			System.out.println(biTree.value);
		}
	}
	
	public static void postOrder(TreeNode biTree)
	{//后序遍历非递归实现
		int left = 1;//在辅助栈里表示左节点
		int right = 2;//在辅助栈里表示右节点
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<Integer> stack2 = new Stack<Integer>();//辅助栈，用来判断子节点返回父节点时处于左节点还是右节点。
		
		while(biTree != null || !stack.empty())
		{
			while(biTree != null)
			{//将节点压入栈1，并在栈2将节点标记为左节点
				stack.push(biTree);
				stack2.push(left);
				biTree = biTree.left;
			}
			
			while(!stack.empty() && stack2.peek() == right)
			{//如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
				stack2.pop();
				System.out.println(stack.pop().value);
			}
			
			if(!stack.empty() && stack2.peek() == left)
			{//如果是从左子节点返回父节点，则将标记改为右子节点
				stack2.pop();
				stack2.push(right);
				biTree = stack.peek().right;
			}
				
		}
	}
}
 
class TreeNode//节点结构
{
	int value;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int value)
	{
		this.value = value;
	}
}
```
* 层次遍历:
与树的前中后序遍历的DFS思想不同，层次遍历用到的是BFS思想。一般DFS用递归去实现（也可以用栈实现），BFS需要用队列去实现。
层次遍历的步骤是：

1. 对于不为空的结点，先把该结点加入到队列中
2. 从队中拿出结点，如果该结点的左右结点不为空，就分别把左右结点加入到队列中
3. 重复以上操作直到队列为空

```java
	public static void levelOrder(TreeNode biTree)
	{//层次遍历
		if(biTree == null)
			return;
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		list.add(biTree);
		TreeNode currentNode;
		while(!list.isEmpty())
		{
			currentNode = list.poll();
			System.out.println(currentNode.value);
			if(currentNode.left != null)
				list.add(currentNode.left);
			if(currentNode.right != null)
				list.add(currentNode.right);
		}
	}
```

先序遍历特点：第一个值是根节点

中序遍历特点：根节点左边都是左子树，右边都是右子树
