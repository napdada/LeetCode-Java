# 剑指Offer(第2版)
[TOC]
 **注O(N) O(N)：先时间复杂度，再空间复杂度，全文如此。**

## A1. 动态规划DP

1. 状态定义
2. 转移方程
3. 初始化
4. 返回值

## A2. 递归

1. 递推参数
2. 终止条件
3. 递推工作
4. 返回值

## Q3. 数组中重复的数字：遍历计数、排序找重、**原地交换**

​	在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

解：

1. 遍历计数 - 遍历 nums[] ，利用 countArray[] 记录数出现的次数。

   - ```java
     /**
      * 遍历 nums[] ，利用 countArray[] 记录数出现的次数
      * @author PAN
      * @param nums int[]
      * @return repeatNumber：第一个重复出现的数字
      */
     public static int findReapeatNumber(int[] nums) {
         int[] countArray = new int[nums.length];
         int repeatNumber = -1;
         for(int num : nums) {
             countArray[num]++;
             if(countArray[num] > 1) {
                 repeatNumber = num;
                 break;
             }
         }
         return repeatNumber;
     }
     ```

2. 排序后再找重 - 排序 nums[]，再遍历找到第一个重复数字。

   - ```java
     /**
      * 排序 nums[]，再遍历找到第一个重复数字
      * @author PAN
      * @param nums int[]
      * @return repeatNumber：第一个重复出现的数字
      */
     public static int findReapeatNumber2(int[] nums) {
         int repeatNumber = -1;
         Arrays.sort(nums);
         int record = nums[0];
         for(int i = 1; i < nums.length; i++) {
             if(nums[i] == record) {
                 repeatNumber = record;
                 break;
             } else {
                 record = nums[i];
             }
         }
         return repeatNumber;
     }
     ```

3. **原地交换** - 遍历中，第一次遇到数字 xx 时，将其交换至索引 xx 处，而当第二次遇到数字 xx 时，一定有 nums[x] = x，此时即可得到一组重复数字。

   ![原地交换](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/原地交换.png)

   - ```java
     /**
      * 原地交换
      * 遍历中，第一次遇到数字 xx 时，将其交换至索引 xx 处；
      * 而当第二次遇到数字 xx 时，一定有 nums[x] = x ，此时即可得到一组重复数字。
      * @author 网友
      */
     public static int findReapeatNumber0(int[] nums) {
         int i = 0;
         while(i < nums.length) {
             if(nums[i] == i) {
                 i++;
                 continue;
             }
             if(nums[nums[i]] == nums[i]) return nums[i];
             int tmp = nums[i];
             nums[i] = nums[tmp];
             nums[tmp] = tmp;
         }
         return -1;
     }
     ```

## Q4. 二维数组中的查找：暴力求解、**类二叉查找树**

​	在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 ```
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

给定 target = 5，返回 true。
给定 target = 20，返回 false。
 ```

解：

1. 暴力求解 O(N + M) - 双重循环遍历整个数组，与所有元素一一比较。

   - ```java
     /**
      * 暴力求解
      * @author PAN
      * @param matrix int[][]
      * @param target int
      * @return true/false：查找结果
      * @time O(N * M)
      */
     public static boolean findNumberIn2DArray(int[][] matrix, int target) {
         for (int i = 0; i < matrix.length; i++) {
             for (int j = 0; j < matrix[i].length; j++) {
                 if (target == matrix[i][j]) {
                     return true;
                 }
             }
         }
         return false;
     }
     ```

2. **类二叉查找树 O(N + M)** - 将矩阵逆时针旋转 45° ，并将其转化为图形式，发现其类似于二叉搜索树，即对于每个元素，其左分支元素更小、右分支元素更大。从二维数组右上角元素开始与 target 比较，target 大则向下比较， target 小则向左比较。

   ![类二叉搜索树](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/类二叉搜索树.png)

   - ```java
     /**
      * 线性查找：从二维数组右上角元素开始与 target 比较，target 大则向下比较， target 小则向左比较
      * @author 网友
      * @param matrix 二维数组
      * @param target 查找目标
      * @return true/false
      * @time O(N + M)
      */
     public static boolean findNumberIn2DArray2(int[][] matrix, int target) {
         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
             return false;
         } else {
             int i = 0, j = matrix[0].length - 1;
             do {
                 if (target < matrix[i][j]) {
                     j--;
                 } else if (target == matrix[i][j]) {
                     return true;
                 } else {
                     i++;
                 }
             } while (i < matrix.length && j > -1);
     
             return false;
         }
     }
     ```

## Q5. 替换空格：遍历替换、**原地修改**

​	请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

解：

1. 遍历查找替换 O(N) O(N) - 逐个字符遍历字符串查找空格，字符串不为空格追加到新字符串，为空格追加"%20"。

   - ```java
     /**
      * 遍历查找替换：逐个字符遍历字符串查找空格，字符串不为空格追加到新字符串，为空格追加"%20"
      * @author PAN
      * @param s 原字符串
      * @return 替换后字符串
      * @time O(N)
      * @space O(N)
      */
     public static String replaceSpace(String s) {
         StringBuilder newS = new StringBuilder();
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             if(c != ' ') {
                 newS.append(c);
             } else {
                 newS.append("%20");
             }
         }
     
         return newS.toString();
     }
     ```

2. 调用`replace()`

   - ```java
     public static String replaceSpace2(String s) {
         return s.replace(" ","%20");
     }
     ```

3. **原地修改 O(N) O(1)** - 不使用新字符串来存储，但在 Java Python 中不行，因它们字符串建立后不可改变，在 C++ 中可以通过两个指针来原地修改

## Q6. 从尾到头打印链表：栈、递归

​	输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

```
输入：head = [1,3,2]
输出：[2,3,1]
```

解：

1. 栈 O(N) O(N)  - 先入后出实现从尾到头打印。

   - ```java
     /**
      * 栈：先入后出实现从尾到头打印
      * @author PAN
      * @param head 单链表头
      * @return 反转后单链表（以数组形式）
      * @time O(N)
      * @space O(N)
      */
     public static int[] reversePrint(ListNode head) {
         Stack<ListNode> s = new Stack<ListNode>();
         while (head != null) {
             s.push(head);
             head = head.next;
         }
         int len = s.size();
         int[] array = new int[len];
         for (int i = 0; i < len; i++) {
             array[i] = s.pop().val;
         }
         return array;
     }
     ```

2. 递归  O(N) O(N)。

## Q7. 重建二叉树 - 递归、迭代

​	输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

```
例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]

返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
```

解：

1. **递归 O(N) O(N)**

   **分治算法解析：**

   - **递推参数**： 根节点在前序遍历的索引 root 、子树在中序遍历的左边界 left 、子树在中序遍历的右边界 right ；

   - **终止条件**： 当 left > right ，代表已经越过叶节点，此时返回 nullnull ；

   - **递推工作**：

     1. 建立根节点 node ： 节点值为 preorder[root] ；

     2. 划分左右子树： 查找根节点在中序遍历 inorder 中的索引 i ；
        为了提升效率，本文使用哈希表 dic 存储中序遍历的值与索引的映射，查找操作的时间复杂度为 O(1)O(1)

     3. 构建左右子树： 开启左右子树递归；**几个索引值的确定容易出错！！！**

        |        | 根节点索引          | 中序遍历左边界 | **中序遍历右边界** |
        | ------ | ------------------- | -------------- | ------------------ |
        | 左子树 | root + 1            | left           | i - 1              |
        | 右子树 | i - left + root + 1 | i + 1          | right              |

        - i - left + root + 1含义为 根节点索引 + 左子树长度 + 1

   - **返回值**： 回溯返回 node ，作为上一层递归中根节点的左 / 右子节点；

   - ```java
     /**
      * 递归重建二叉树
      * @author 网友
      * @param root 前序中根索引
      * @param left 前序中左子树索引
      * @param right 前序中右子树索引
      * @return 树结点
      * @time O(N)
      * @space O(N)
      */
     TreeNode recur(int root, int left, int right) {
         if (left > right) return null;
         TreeNode node = new TreeNode(preorder[root]);
         int i = dic.get(preorder[root]);
         node.left = recur(root + 1, left, i -1);
         node.right = recur(i - left + root + 1, i + 1, right);
         return node;
     }
     ```

   

2. 迭代 O(N) O(N)，不好理解

   - 对于前序遍历中的任意两个连续节点 uu 和 vv，根据前序遍历的流程，我们可以知道 uu 和 vv 只有两种可能的关系：
     - vv 是 uu 的左儿子。这是因为在遍历到 uu 之后，下一个遍历的节点就是 uu 的左儿子，即 vv；

     - uu 没有左儿子，并且 vv 是 uu 的某个祖先节点（或者 uu 本身）的右儿子。

   - **算法：**
     - 用一个栈和一个指针辅助进行二叉树的构造。初始时栈中存放了根节点（前序遍历的第一个节点），指针指向中序遍历的第一个节点；

     - 我们依次枚举前序遍历中除了第一个节点以外的每个节点。如果 index 恰好指向栈顶节点，那么我们不断地弹出栈顶节点并向右移动 index，并将当前节点作为最后一个弹出的节点的右儿子；如果 index 和栈顶节点不同，我们将当前节点作为栈顶节点的左儿子；

     - 无论是哪一种情况，我们最后都将当前的节点入栈。

## Q9. 用两个栈实现队列：栈

​	用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

```
示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]

示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

解：

1. 一栈负责进，一栈复杂出 O(N) O(N)

   - ```java
     /**
      * 一栈负责进，一栈复杂出
      * @author PAN
      * @return 队列头
      * @time O(N)
      * @space O(N)
      */
     public int deleteHead() {
         if (s2.size() == 0) {
             if (s1.size() == 0) return -1;
             else {
                 while (!s1.isEmpty()) {
                     s2.push(s1.pop());
                 }
             }
         }
         return s2.pop();
     }
     ```

## Q10 - I. 斐波那契数列：递归、**动态规划DP**

​	写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

> F(0) = 0,   F(1) = 1
> F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
> 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

​	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```
示例 1：

输入：n = 2
输出：1

示例 2：

输入：n = 5
输出：5
```

解：

1. 递归 - 效率低，存在大量重复计算；

   - ```java
     /**
      * 递归求解 - 将斐波那契公式转换为递归形式
      * @author PAN
      * @param n 数列第 n 项
      * @return 斐波那契值
      */
     public static long fib(int n) {
         if (n == 0) return 0;
         else if (n == 1) return 1;
         else {
             if (fib(n - 1) + fib(n - 2) > 1000000007) return (fib(n - 1) + fib(n - 2)) % 1000000007;
             else return fib(n - 1) + fib(n - 2);
         }
     }
     ```

2. **动态规划 O(N) O(N)** - 某一项等于前两项之和，用循环依次计算。**【注意！！！】先求余与最后求余返回结果一致，但先求可以防止 int 溢出**

   - ```java
     /**
      * 动态规划 - 某一项等于前两项之和，用循环依次计算。【注意！！！】先求余与最后求余返回结果一致，但先求可以防止 int 溢出
      * @author PAN
      * @param n
      * @return
      * @time O(N)
      * @space O(1)
      */
     public static int fib2(int n) {
         int a = 0, b = 1;
         switch (n) {
             case 0: return 0;
             case 1: return 1;
             default: {
                 int tmp;
                 for (int i = 1; i < n; i++) {
                     tmp = (a + b) % 1000000007;
                     a = b;
                     b = tmp;
                 }
                 return b;
             }
         }
     }
     
     /**
      * 动态规划 - 精简代码
      * @author 网友
      * @param n
      * @return
      */
     public static int fib3(int n) {
         int a = 0, b = 1, sum;
         for(int i = 0; i < n; i++){
             sum = (a + b) % 1000000007;
             a = b;
             b = sum;
         }
         return a;
     }
     ```

## Q10 - II. 青蛙跳台阶问题：递归、**动态规划DP**

​	一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

​	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```
示例 1：

输入：n = 2
输出：2

示例 2：

输入：n = 7
输出：21

示例 3：

输入：n = 0
输出：1
```

解：

1. 递归

2. 动态规划 

   - 此题就是斐波那契数列的变形（区别在于初始值不一样），设跳上 n 级台阶有 f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1 级或 2 级台阶。
     - 当为 1 级台阶： 剩 n-1 个台阶，此情况共有 f(n-1) 种跳法；
     - 当为 2 级台阶： 剩 n-2 个台阶，此情况共有 f(n-2) 种跳法。
     - f(n) 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2)。

   - ```java
     public static int numWays(int n) {
         int a = 1, b = 1, sum;
         for (int i = 0; i < n; i++) {
             sum = (a + b) % 1000000007;
             a = b;
             b = sum;
         }
         return a;
     }
     ```


## Q11. 旋转数组的最小数字：遍历寻找、**二分法**

​	把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

```
示例 1：

输入：[3,4,5,1,2]
输出：1

示例 2：

输入：[2,2,2,0,1]
输出：0
```

解：

1. 遍历直到第一个变小的数 O(N) O(1) - 首先用min记录数组中第一个元素的值，之后便利数组，一一与min比较，第一个比min小的即是结果；

   - ```java
     /**
      * 遍历直到第一个变小的数
      * @author PAN
      * @param numbers 两个非递减数列构成的数组
      * @return min
      * @time O(N)
      * @space O(1)
      */
     public static int minArray(int[] numbers) {
         int min = numbers[0];
         for (int i = 1; i < numbers.length; i++) {
             if (numbers[i] < min) {
                 min = numbers[i];
                 break;
             }
         }
         return min;
     }
     ```

2. **二分法 O(log N) O(1)** - 因为整个数组是由两个非递减数列构成的，所以可以用二分来缩小范围

   1）i = 0, j = length - 1, m = (i + j) / 2；

   2）比较索引为m和j数组元素大小，大于则 i = m + 1，等于则 j--，小于则 j = m（或者调用上述遍历找min）

   - ```java
     /**
      * 二分法：因为整个数组是由两个非递减数列构成的，所以可以用二分来缩小范围
      * @author 网友
      * @param numbers 两个非递减数列构成的数组
      * @return min
      * @time O(logN)
      * @space O(1)
      */
     public static int minArray2(int[] numbers){
         int i = 0, j = numbers.length - 1;
         int m = 0;
         while (i < j) {
             m = (i + j) / 2;
             if (numbers[m] > numbers[j]) i = m + 1;
             else if (numbers[m] == numbers[j]) j--;
             else {
                 int min = numbers[i];
                 for (int k = i + 1; k < m + 1; k++) {
                     if (numbers[k] < min){
                         min = numbers[k];
                         break;
                     }
                 }
                 return min;
             }
         }
         return numbers[i];
     }
     ```

## Q12. 矩阵中的路径：**DFS+剪枝**

​	请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]

​	但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

```
示例 1：

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

示例 2：

输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```

解：

1. **DFS + 剪枝 O(3^K*MN) O(K)**：

   ![DFS+剪枝](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/DFS+剪枝.png)

   1）DFS暴力遍历矩阵中所有字符串可能性，通过递归先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推；

   2）在搜索中，遇到这条路不可能和目标字符串匹配成功的情况，则立即返回，称可行性剪枝；

   - DFS解析：

     - 递归参数：当前元素在矩阵 board 中的行索引 i 和 j ，当前目标字符在 word 中的索引 k。
     - 终止条件：
       1. 返回 false （满足任一一个）：（1）行或列索引越界 （2）当前矩阵元素与目标字符不同（3）当前矩阵元素已访问
       2. 返回 true：`k = len(word) - 1`，即字符串 word 已全部匹配
     - 递推工作：
       1. 标记当前矩阵元素：将 `board[i][j]`修改为空`'\0'`，代表已访问；
       2. 搜索下一单元格：上右下左；
       3. 还原当前矩阵元素：将 `board[i][j]`还原为初始值；
     - 返回值：是否搜索到目标字符串
     - 时间复杂度 O(3^K*MN)：字符串长度为K，搜索中每个字符有四个方向选
     - 空间复杂度 O(K)：搜索过程中的递归深度不超过K，系统因函数调用累计使用栈空间O(K)

   - ```java
     boolean existPath(char[][] board, String word) {
         char[] wordArray = word.toCharArray();
         for (int i = 0; i < board.length; i++) {
             for (int j = 0; j < board[i].length; j++) {
                 if (dfs(board, wordArray, i, j, 0)) return true;
             }
         }
         return false;
     }
     boolean dfs(char[][] board, char[] word, int i, int j, int k) {
         if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word[k]) return false;
         if (k == word.length - 1) return true;
         board[i][j] = '\0';
         boolean result = dfs(board, word, i, j - 1, k + 1) || dfs(board, word, i + 1, j, k + 1) ||
                 dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i - 1, j, k + 1);
         board[i][j] = word[k];
         return result;
     }
     ```

## Q13. 机器人的运动范围：DFS（**数位和增量公式、可达性分析）**、BFS

​	地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

```
示例 1：

输入：m = 2, n = 3, k = 1
输出：3

示例 2：

输入：m = 3, n = 1, k = 0
输出：1
```

解：

1. 深度优先遍历DFS O(MN) O(MN)

   - **数位和增量公式：不需要每次都用整除和求余去计算位数和**

     ![数位和增量公式](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/数位和增量公式.png)

   - 可达解分析：**仅需向右、向下移动**

   - 与Q12类似，采用递归求解即可，代码十分类似

   - ```java
     /**
      * 递归求解，与Q12类似，仅需向右、向下移动
      * @author PAN
      * @param m 行数
      * @param n 列数
      * @param k 位数和上限
      * @return 可达数
      * @time O(MN)
      * @space O(MN)
      */
     int movingCount(int m, int n, int k) {
         DFS(m, n, 0, 0, k);
         return xy.size();
     }
     
     /**
      * DFS：递归函数
      * @author PAN
      * @param m 行数
      * @param n 列数
      * @param i 初始位置
      * @param j 初始位置
      * @param k 位数和上限
      */
     void DFS(int m, int n, int i, int j, int k) {
         int sum = i / 100 + i / 10 + i % 10 + j / 100 + j / 10 + j % 10;
         boolean flag = sum > k ? false : true;
         String tmp = i + "," + j;
         if (i < 0 || i >= m || j < 0 || j >= n || !flag || xy.contains(tmp)) return;
         xy.add(tmp);
         DFS(m, n, i, j + 1, k);
         DFS(m, n, i + 1, j, k);
     
         // 优化，不必向上、向左，也意味不需要判断 i < 0 || j < 0
         // DFS(m, n, i, j - 1, k);
         // DFS(m, n, i - 1, j, k);
     }
     
     /**
      * 递归求解优化：用 visited 数组记录是否访问，并精简代码
      * @author PAN
      * @param m 行数
      * @param n 列数
      * @param k 位数和上限
      * @return 可达数
      * @time O(MN)
      * @space O(MN)
      */
     int movingCount2(int m, int n, int k) {
         boolean[][] visited = new boolean[m][n];
         return DFS2(m, n, 0, 0, k, visited);
     }
     int DFS2(int m, int n, int i, int j, int k, boolean[][] visited){
         int sum = i / 100 + i / 10 + i % 10 + j / 100 + j / 10 + j % 10;
         if (i >= m || j >= n || sum > k || visited[i][j]) return 0;
         visited[i][j] = true;
         return DFS2(m, n, i, j + 1, k, visited) + DFS2(m, n, i + 1, j, k, visited) + 1;
     }
     ```

2. 广度优先遍历BFS O(MN) O(MN)

   - 用队列实现：

     1）将机器人初始点加入队列；

     2）将队首单元格的索引、数位弹出；

     3）判断是否越界或超出k或已访问；

     4）对未访问的单元格进行标记，(i, j )存入visited中；

     5）将当前元素的下方、右方单元格数位入队；

     6）队列为空时，停止迭代
     
   - ```java
     /**
      * BFS：用队列实现，重点在于数位和增量公式(不需要每次都用整除和求余去计算位数和)
      * @author 网友
      * @param m 行数
      * @param n 列数
      * @param k 位数和上限
      * @return 可达数
      * @time O(MN)
      * @space O(MN)
      */
     public int movingCount3(int m, int n, int k) {
         boolean[][] visited = new boolean[m][n];
         int res = 0;
         Queue<int[]> queue= new LinkedList<int[]>();
         queue.add(new int[] { 0, 0, 0, 0 });
         while(queue.size() > 0) {
             int[] x = queue.poll();
             int i = x[0], j = x[1], si = x[2], sj = x[3];
             if(i >= m || j >= n || k < si + sj || visited[i][j]) continue;
             visited[i][j] = true;
             res ++;
             queue.add(new int[] { i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj }); // 数位和增量公式
             queue.add(new int[] { i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8 });
         }
         return res;
     }
     ```

## Q14 - I. 剪绳子：**数学推导、贪心、动态规划**

​	给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

```
示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1

示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

解：

1. **数学推导 O(1) O(1)**

   - 由重要不等式的推论可以证明，绳子越等分乘积越大；

   - 求导也可以证明，同时可以求得驻点为e，即2.7左右，通过带入2、3可以得到绳子越多切分成长度3，乘积越大；

     ![绳子切分](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/绳子切分.png)

   - 那么转换成以下算法：

     1）3a + b = n，当 n <= 3 时，由于 m > 1，那么必须有一段绳子长度为1，乘积即为 n - 1；

     2）当 n > 3 时，对 b 讨论，即 n % 3 讨论：b = 0 时，乘积为 3 ^ a；b = 1 时，乘积为 3 ^ （a - 1） * 4，即有一段长度为3的绳子要拿出来和长度为1的绳子形成2 + 2；b = 2 时，乘积为 3 ^ a * 2；
     
   - ```java
     /**
      * 数学推导/贪心：
      *  1. 绳子越等分乘积越大；
      *  2. 越多切分成长度 3 ，乘积越大；
      *  3. 以长度 3 为基础去切分，对最后一段长度（可能：0， 1， 2）进行讨论计算；
      * @author PAN & 网友：自己推断了1，但2不太确定，可以用求导得驻点是e（2.7）
      * @param n 绳子长度
      * @return 最大乘积
      * @time O(1)
      * @space O(1)
      */
     public static int cuttingRope(int n) {
         if (n <= 3) return n - 1;
         int a = n / 3, b = n % 3;
         if(b == 0) return (int) Math.pow(3, a);
         else if(b == 1) return 4 * (int) Math.pow(3, a - 1);
         else return 2 * (int) Math.pow(3, a);
     }
     ```

2. **贪心**

   - ![绳子切分贪心](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/绳子切分贪心.png)

3. 动态规划 O(N^2) O(N)

   - ![绳子切分动态规划](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/绳子切分动态规划.png)

   - ```java
     class Solution {
         public int cuttingRope(int n) {
             int[] dp = new int[n + 1];
             dp[2] = 1;
             for(int i = 3; i < n + 1; i++){
                 for(int j = 2; j < i; j++){
                     dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                 }
             }
             return dp[n];
         }
     }
     ```

## Q14 - II. 剪绳子 II：循环求余、**快速求余**

​	给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

​	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```
示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1

示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

解（此题跟上一题差别在于n的范围）：

1. 循环求余 O(N) O(1) - 每一次幂运算都求一次余数；

   - ```java
     /**
      * 循环求余
      * @author PAN
      * @param n 绳子长度
      * @return 最大乘积
      * @time O(N)
      * @space O(1)
      */
     public static int cuttingRope(int n) {
         if (n <= 3) return n - 1;
         int a = n / 3, b = n % 3;
         long mul = 1;
         int r = 1000000007;
         for (int i = 0; i < a - 1; i++) {
             mul = (mul * 3) % r;
         }
         if(b == 0) return (int)(mul * 3 % r);
         else if(b == 1) return (int)((4 * mul) % r);
         else return (int)(mul * 6 % r);
     }
     ```

2. **快速求余 O(log2 N) O(1)**

   - ![快速幂求余](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/快速幂求余.png)

   - ```java
     class Solution {
         public int cuttingRope(int n) {
             if(n <= 3) return n - 1;
             int b = n % 3, p = 1000000007;
             long rem = 1, x = 3;
             for(int a = n / 3 - 1; a > 0; a /= 2) {
                 if(a % 2 == 1) rem = (rem * x) % p;
                 x = (x * x) % p;
             }
             if(b == 0) return (int)(rem * 3 % p);
             if(b == 1) return (int)(rem * 4 % p);
             return (int)(rem * 6 % p);
         }
     }
     ```

## Q15. 二进制中1的个数：**巧用n & (n - 1)**、逐位判断

​	请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

```
示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

示例 2：

输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。

示例 3：

输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

解：

1. **巧用 n & (n - 1) O(M) O(1)**，M 为 1 的个数。
   - (n−1) 解析： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
   
   - n \& (n - 1)解析： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
   
   - ```java
     /**
      * 巧用 n & (n - 1)
      * (n−1)： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
      * n & (n - 1)： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
      * @author 网友 & PAN，借鉴 n & (n - 1) 的思想
      * @param n 无符号二进制数
      * @return 1 的个数
      * @time O(M) M 为 1 的个数
      * @space O(1)
      */
     public static int hammingWeight(int n) {
         int count = 0;
         while (n != 0) {
             n = n & (n - 1);
             count++;
         }
         return count;
     }
     ```
   
2. 逐位判断 O(log2 N) O(1) - 将 n 和 1 做与操作，结果为 1 时计数，再将 n 向右移位重复操作。

   - ```java
     /**
      * 逐位判断：将 n 和 1 做与操作，结果为 1 时计数，再将 n 向右移位重复操作。
      * @author 网友
      * @param n 无符号二进制数
      * @return 1 的个数
      * @time O(log2 N) M 为 1 的个数
      * @space O(1)
      */
     public static int hammingWeight2(int n) {
         int count = 0;
         while (n != 0) {
             count += n & 1;
             n >>>= 1; // 这里需要写成>>>，Java中的无符号右移
             // n = n >> 1; // 自己的写法
         }
         return count;
     }
     ```

## Q16. 数值的整数次方：循环求幂、**二进制快速幂**、**二分法快速幂**

​	实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。

```
示例 1：

输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：

输入：x = 2.10000, n = 3
输出：9.26100
示例 3：

输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25
```

解：

1. 循环求解 O(N) O(1) - 用循环一次次累乘，以求幂运算；

   - ```java
     /**
      * 循环求解：用循环一次次累乘，以求幂运算。
      * 该法有几个坑：
      * 1. 需要对 x = -1, 0, 1 的特殊情况进行判断；
      * 2. 测试用例中有一项 n 为 2147483648，超过 int 范围（2147483647），
      *    需要用long 或者判断 double 精度不够直接置 0；
      * @author PAN
      * @param x 底数
      * @param n 幂次
      * @return x ^ n
      * @time O(N)
      * @space O(1)
      */
     public static double myPow(double x, int n) {
         if (x == 0 || x == 1) return x;
         double pow = 1;
         if (n < 0) {
             n = n * (-1);
             x = 1.0 / x;
         }
         if (x == -1 && n % 2 == 0) return -x;
         else if (x == -1 && n % 2 != 0) return x;
         if (n < 0) return 0;
         for (int i = 0; i < n; i++) {
             pow *= x;
             if (pow == 0) break;
         }
         return pow;
     }
     ```

2. **二进制快速幂 O(log2 N) O(1)** - 利用十进制数字 n 的二进制表示，可对快速幂进行数学化解释。

   ![二进制快速幂](/Users/panpan/Documents/Code/DevelopTips/图/算法/二进制快速幂.png)

   - ```java
     /**
      * 二进制快速幂：利用十进制数字 n 的二进制表示，可对快速幂进行数学化解释
      * 或二分法快速幂：对 n 不断除以 2 来快速计算，只需要判断 n 的奇偶
      * @author 网友
      * @param x 底数
      * @param n 幂次
      * @return x ^ n
      * @time O(log2 N)
      * @space O(1)
      */
     public static double myPow2(double x, int n) {
         if (x == 0) return x;
         double pow = 1.0;
         long newN = n;
         if (newN < 0) {
             newN = -newN;
             x = 1.0 / x;
         }
         while (newN != 0) {
             if ((newN & 1) == 1) pow *= x;
             x *= x;
             newN >>= 1;
         }
         return pow;
     }
     ```

3. **二分法快速幂 O(log2 N) O(1)** - 与上个方法类似

   ![二分法快速幂](/Users/panpan/Documents/Code/DevelopTips/图/算法/二分法快速幂.png)

## Q17. 打印从1到最大的n位数：循环打印、**大数打印（递归）**

​	输入数字 `n`，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

```
示例 1:

输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]
```

解：

1. 循环打印 O(10^N) O(1)（ 列表作为返回结果，不计入额外空间 ） - 直接构建数组循环输出。

   - ```java
     /**
      * 循环打印：直接构建数组循环输出
      * @author PAN
      * @param n 最大位数
      * @return 从 1 到最大的 n 位十进制数
      * @time O(10^N)
      * @space O(1)
      */
     public static int[] printNumbers(int n) {
         int max = (int) Math.pow(10, n) - 1;
         int[] printArray = new int[max];
         for (int i = 0; i < max; i++) {
             printArray[i] = i + 1;
         }
         return printArray;
     }
     ```

2. 大数打印 O(10^N) O(10^N) - 题目给定了 int 范围，但实际情况可能会考大数，这时候用 int 无法解，需要用 String。

   - ![大数递归打印](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/大数递归打印.png)

   - 主要处理两个问题：

     1. 删除高位多余的 0：

        ![删除高位0](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/删除高位0.png)

     2. 列表从 1 开始：添加字符串前判断是否为“0”，是则跳过。
     
   - ```java
     /**
      * 大数打印： 题目给定了 int 范围，但实际情况可能会考大数，这时候用 int 无法解，需要用 String。
      * @author 网友 & PAN，根据思路改了一些写法，
      * 但还有问题：这样默认还是 int 范围，应该将 printNumber 返回结果也改为 String
      * @param n 最大位数
      * @return 从 1 到最大的 n 位十进制数
      * @time O(10^N)
      * @space O(10^N)
      */
     public int[] printNumbers2(int n) {
         this.n = n;
         this.num = new char[n];
         this.printArray = new int[(int) Math.pow(10, n) - 1];
         dfs(0);
         return this.printArray;
     }
     
     /**
      * 递归主体：先固定高位，向低位递归，当个位已被固定时，添加数字的字符串
      * @param x 递归位数（0 - n）
      */
     public void dfs(int x) {
         if (x == this.n) {
             if (Integer.parseInt(String.valueOf(num)) != 0) {
                 this.printArray[count] = Integer.parseInt(String.valueOf(num));
                 count++;
             }
             return;
         }
         for (char c : this.loop) {
             this.num[x] = c;
             dfs(x + 1);
         }
     }
     ```

## Q18. 删除链表的节点：遍历对比删除

​	给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。

```
示例 1:

输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

示例 2:

输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

解：

1. 遍历对比删除 O(N) O(1) - 从头节点开始依次比较，找到要删除的节点进行删除，即将上一个节点指向下一个节点。

   - ```java
     /**
      * 遍历对比删除：从头节点开始依次比较，找到要删除的节点进行删除，即将上一个节点指向下一个节点。
      * @author PAN
      * @param head 头节点
      * @param val 待删除节点的值
      * @return 头节点
      * @time O(N)
      * @space O(1)
      */
     public ListNode deleteNode(ListNode head, int val) {
         if (head.val == val) {
             head = head.next;
             return head;
         }
         ListNode point = head;
         while (point.next != null) {
             if (point.next.val == val) point.next = point.next.next;
             else point = point.next;
         }
         return head;
     }
     ```

## Q19. 正则表达式匹配：**动态规划**

​	请实现一个函数用来匹配包含 . 和 * 的正则表达式。模式中的字符 . 表示任意一个字符，而 * 表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab * ac * a"匹配，但与"aa.a"和"ab*a"均不匹配。

```
示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

示例 4:

输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

示例 5:

输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

解：

1. **动态规划 O(NM) O(NM)**
   - 总体思路：每轮添加一个字符并判断是否能匹配，直至添加完整个字符串 s 和 p。
   
   - ![正则表达式匹配](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/正则表达式匹配.png)
   
   - ![正则表达式匹配DP](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/正则表达式匹配DP.png)
   
   - ```java
     /**
      * 动态规划：每轮添加一个字符并判断是否能匹配，直至添加完整个字符串 s 和 p
      * @author 网友
      * @param s 字符串
      * @param p 正则表达式
      * @return 匹配成功与否
      * @time O(NM)
      * @space O(NM)
      */
     public static boolean isMatch2(String s, String p) {
         boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
         dp[0][0] = true;
         for (int j = 2; j < p.length() + 1; j += 2) {
             dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
         }
         for (int i = 1; i < s.length() + 1; i++) {
             for (int j = 1; j < p.length() + 1; j++) {
                 if (p.charAt(j - 1) == '*') {
                     if (dp[i][j - 2]) dp[i][j] = true;
                     else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
                     else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
                 } else {
                     if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
                     else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
                 }
             }
         }
         return dp[s.length()][p.length()];
     }
     ```

## Q20. 表示数值的字符串：逐位判断、**有限状态自动机**

​	请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

解：

1. 遍历判断 O(N) O(1) - 逐位遍历判断是否符合数字要求

   - '.'出现正确的情况：只出现一次，且在e/E前；

   - 'e/E'出现正确的情况：只出现一次，且出现前有数字；

   - '+/-'出现正确的情况：只能在开头或者e/E后一位；

   - ```java
     public static boolean isNumber(String s) {
         boolean hasNum = false, hasDecimal = false, hasE = false; // 是否有数字、小数、e/E
         s = s.trim(); // 删除前后多余空格
         for (int i = 0; i < s.length(); i++) {
             if (s.charAt(i) >= '0' && s.charAt(i) <= '9') hasNum = true;
             else if (s.charAt(i) == '.' && !hasDecimal && !hasE) {
                 hasDecimal = true;
             } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !hasE && hasNum){
                 hasE = true;
                 hasNum = false;
             } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {
     
             } else {
                 return false;
             }
         }
         return hasNum;
     }
     ```

2. **有限状态自动机 O(N) O(1)** 

   - ![判断数字有限状态自动机](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/判断数字有限状态自动机.png)

   - ```java
     class Solution {
         public boolean isNumber(String s) {
             Map[] states = {
                 new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                 new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                 new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                 new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                 new HashMap<>() {{ put('d', 3); }},                                        // 4.
                 new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                 new HashMap<>() {{ put('d', 7); }},                                        // 6.
                 new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                 new HashMap<>() {{ put(' ', 8); }}                                         // 8.
             };
             int p = 0;
             char t;
             for(char c : s.toCharArray()) {
                 if(c >= '0' && c <= '9') t = 'd';
                 else if(c == '+' || c == '-') t = 's';
                 else if(c == 'e' || c == 'E') t = 'e';
                 else if(c == '.' || c == ' ') t = c;
                 else t = '?';
                 if(!states[p].containsKey(t)) return false;
                 p = (int)states[p].get(t);
             }
             return p == 2 || p == 3 || p == 7 || p == 8;
         }
     }
     ```

## Q21. 调整数组顺序使奇数位于偶数前面：遍历找奇偶并用数组存储、**双指针交换**

​	输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

```
示例：

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

解：

1. 遍历找奇偶并用数组存储 O(N) O(N) - 遍历数组，用两个指针指向新数组头尾，遇到奇数往头部放，遇到偶数往尾部放。

   - ```java
     /**
      * 遍历找奇偶并用数组存储：遍历数组，用两个指针指向新数组头尾，遇到奇数往头部放，遇到偶数往尾部放。
      * @author PAN
      * @param nums 待调整的数组
      * @return 按奇偶调整后的数组
      * @time O(N)
      * @space O(N)
      */
     public static int[] exchange(int[] nums) {
         int i = 0, j = nums.length - 1;
         int[] exchangeNums = new int[nums.length];
         for (int k = 0; k < nums.length; k++) {
             if (nums[k] % 2 == 0) {
                 exchangeNums[j] = nums[k];
                 j--;
             }
             else {
                 exchangeNums[i] = nums[k];
                 i++;
             }
         }
         return exchangeNums;
     }
     ```

2. **双指针交换 O(N) O(1)** - 一头一尾指针指向原数组，头指针为奇数时右移，尾指针为偶数时左移，找到第一个不满足条件的两个值交换，然后继续循环。

   - ```java
     /**
      * 双指针交换：一头一尾指针指向原数组，头指针为奇数时右移，尾指针为偶数时左移，找到第一个不满足条件的两个值交换，然后继续循环。
      * @author PAN
      * @param nums 待调整的数组
      * @return 按奇偶调整后的数组
      * @time O(N)
      * @space O(1)
      */
     public static int[] exchange2(int[] nums) {
         int i = 0, j = nums.length - 1;
         int tmp;
         while (i < j) {
             boolean flagI = (nums[i] % 2 == 0);
             boolean flagJ = (nums[j] % 2 == 1);
             if (flagI && flagJ) {
                 tmp = nums[i];
                 nums[i] = nums[j];
                 nums[j] = tmp;
                 i++;
                 j--;
             } else {
                 if (!flagI) i++;
                 if (!flagJ) j--;
             }
         }
         return nums;
     }
     
     /**
      * 双指针交换2
      * @author 网友
      * @param nums 待调整的数组
      * @return 按奇偶调整后的数组
      * @time O(N)
      * @space O(1)
      */
     public int[] exchange3(int[] nums) {
         int left = 0;
         int right = nums.length - 1;
         while (left < right) {
             while (left < right && nums[left] % 2 != 0) {
                 left++;
             }
             while (left < right && nums[right] % 2 == 0) {
                 right--;
             }
             if (left < right) {
                 int temp = nums[left];
                 nums[left] = nums[right];
                 nums[right] = temp;
             }
         }
         return nums;
     }
     ```

## Q22. 链表中倒数第k个节点：先求长度再顺序找、**双指针寻找**

​	输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

​	例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

```
示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

1. 先求链表长度再找 O(N) O(1) - 先遍历一边链表求出长度 len，在利用 len - k 去找该倒数 节点；

   - ```java
     /**
      * 先求链表长度再找：先遍历一边链表求出长度 len，在利用 len - k 去找该倒数 节点
      * @author PAN
      * @param head 链表头节点
      * @param k 倒数第 k
      * @return 链表倒数第 k 个节点
      * @time O(N)
      * @space O(1)
      */
     public ListNode getKthFromEnd(ListNode head, int k) {
         int len = 0;
         ListNode point = head;
         while (point != null) {
             len++;
             point = point.next;
         }
         if (k > len) return null;
         int i = 0;
         point = head;
         while (i != len - k) {
             point = point.next;
             i++;
         }
         return point;
     }
     ```

2. **双指针 O(N) O(1)** - 利用快慢指针，先使得 latter = former + k，再两个指针同步后移，当 latter 为空时 former 即为要找的节点；

   - ```java
     /**
      * 双指针：利用快慢指针，先使得 latter = former + k，再两个指针同步后移，当 latter 为空时 former 即为要找的节点
      * @author 网友 & PAN，参考思路自己实现
      * @param head 链表头节点
      * @param k 倒数第 k
      * @return 链表倒数第 k 个节点
      * @time O(N)
      * @space O(1)
      */
     public ListNode getKthFromEnd2(ListNode head, int k) {
         ListNode former = head, latter = head;
         for (int i = 0; i < k; i++) {
             if (latter == null) return null;
             latter = latter.next;
         }
         while (latter != null) {
             former = former.next;
             latter = latter.next;
         }
         return former;
     }
     ```

3. 栈 O(N) O(N) - 先遍历节点逐个压栈，再弹出第 k 个即为所需；

## Q24. 反转链表：栈、数组、**双指针**、递归

​	定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

```
示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

1. 栈/数组顺序存储再反转 O(N) O(N) - 用栈或者数组先按照原先顺序存储，再按照倒序弹出栈或者数组反向操作；

   - ```java
     /**
      * 栈：用栈先按照原先顺序存储，再按照倒序弹出栈
      * @author PAN
      * @param head 链表头节点
      * @return 反转后链表头
      * @time O(N)
      * @space O(N)
      */
     public ListNode reverseList(ListNode head) {
         if (head == null) return null;
         Stack s = new Stack();
         while (head != null) {
             s.push(head);
             head = head.next;
         }
         ListNode newHead = (ListNode) s.pop();
         ListNode point = newHead;
         while (!s.isEmpty()) {
             point.next = (ListNode) s.pop();
             point = point.next;
         }
         point.next = null;
         return newHead;
     }
     
     /**
      * 数组：用数组按原先顺序存储，再按倒序反向操作
      * @author PAN
      * @param head 链表头节点
      * @return 反转后链表头
      * @time O(N)
      * @space O(N)
      */
     public ListNode reverseList2(ListNode head){
         if (head == null) return null;
         ListNode[] array = new ListNode[5000];
         int i = 0, len = 0;
         while (head != null) {
             array[i] = head;
             i++;
             len++;
             head = head.next;
         }
         for (i = len - 1; i > 0; i--) {
             array[i].next = array[i - 1];
         }
         array[0].next = null;
         return array[len - 1];
     }
     ```

2. **双指针 O(N) O(1)** - 用前后相差一位的指针反复移动来实现反转；

   - ```java
     /**
      * 双指针：用前后相差一位的指针反复移动来实现反转
      * @author PAN
      * @param head 链表头节点
      * @return 反转后链表头
      * @time O(N)
      * @space O(1)
      */
     public ListNode reverseList3(ListNode head) {
         if (head == null) return null;
         ListNode former = head, latter = head.next, tmp;
         if (latter == null) return head;
         former.next = null;
         while (latter != null) {
             tmp = latter.next;
             latter.next = former;
             former = latter;
             latter = tmp;
         }
         return former;
     }
     
     /**
      * 双指针精简版：将两个指针的初始值设为 null, head 代替自己的 head, head.next 以省略一些多余的判断
      * @author 网友
      * @param head 链表头节点
      * @return 反转后链表头
      * @time O(N)
      * @space O(1)
      */
     public ListNode reverseList4(ListNode head) {
         ListNode cur = head, pre = null;
         while(cur != null) {
             ListNode tmp = cur.next; // 暂存后继节点 cur.next
             cur.next = pre;          // 修改 next 引用指向
             pre = cur;               // pre 暂存 cur
             cur = tmp;               // cur 访问下一节点
         }
         return pre;
     }
     ```

3. 递归 O(N) O(N) - 考虑使用递归法遍历链表，当越过尾节点后终止递归，在回溯时修改各节点的 `next` 引用指向。

   - ```java
     /**
      * 递归：考虑使用递归法遍历链表，当越过尾节点后终止递归，在回溯时修改各节点的 next 引用指向。
      * @author 网友
      * @param head 链表头节点
      * @return 反转后链表头
      * @time O(N)
      * @space O(N)
      */
     public ListNode reverseList5(ListNode head) {
         return recur(head, null);    // 调用递归并返回
     }
     private ListNode recur(ListNode cur, ListNode pre) {
         if (cur == null) return pre; // 终止条件
         ListNode res = recur(cur.next, cur);  // 递归后继节点
         cur.next = pre;              // 修改节点引用指向
         return res;                  // 返回反转链表的头节点
     }
     ```

## Q25. 合并两个排序的链表：遍历比较合并、递归

​	输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

```
示例1：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

解：

1. 遍历比较合并 O(N + M) O(1) - 遍历逐个比较大小，将小的加入到新建的头节点后面，大的继续比较；

   - ```java
     /**
      * 遍历比较合并：遍历逐个比较大小，将小的加入到新建的头节点后面，大的继续比较
      * @author PAN
      * @param l1 非递减链表 1
      * @param l2 非递减链表 2
      * @return 非递减合并链表
      * @time O(M + N)
      * @space O(1)
      */
     public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
         if (l1 == null) return l2;
         if (l2 == null) return l1;
         ListNode newHead = l1.val < l2.val ? l1 : l2, point = newHead;
         if (l1.val < l2.val) l1 = l1.next;
         else l2 = l2.next;
     
         while (l1 != null && l2 != null) {
             if (l1.val < l2.val) {
                 point.next = l1;
                 point = point.next;
                 l1 = l1.next;
             } else {
                 point.next = l2;
                 point = point.next;
                 l2 = l2.next;
             }
         }
         if (l1 == null) point.next = l2;
         if (l2 == null) point.next = l1;
         return newHead;
     }
     ```

2. 递归 O(N + M) O(1)；

   - ```java
     /**
      * 递归
      * @author 网友
      * @param l1 非递减链表 1
      * @param l2 非递减链表 2
      * @return 非递减合并链表
      * @time O(M + N)
      * @space O(1)
      */
     public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
         if (l1 == null) {
             return l2;
         }
         if (l2 == null) {
             return l1;
         }
         if (l1.val <= l2.val) {
             l1.next = mergeTwoLists2(l1.next, l2);
             return l1;
         } else {
             l2.next = mergeTwoLists2(l1, l2.next);
             return l2;
         }
     }
     ```

## Q26. 树的子结构：**两个递归**

​	输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)B是A的子结构， 即 A中有出现和B相同的结构和节点值。

```
例如:
给定的树 A:

	 3
	/ \

   4   5
  / \
 1   2
给定的树 B：

   4 
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
```

解：

1. 递归 O(MN) O(M) - M：A的节点数，N：B的节点数

   1）先序遍历 A 中每个节点 nA（对应 `isSubStructure(A, B)`）；

   2）判断树 A 中以 nA 为根节点的子树是否包含 B（对应`recur(A, B)`）。

   - ```java
     /**
      * 递归：1. 先序遍历 A 中每个节点 nA（对应 isSubStructure(A, B)）；2. 判断树 A 中以 nA 为根节点的子树是否包含 B（对应 recur(A, B)）。
      * @author 网友
      * @param A 二叉树（节点数 M）
      * @param B 二叉树（节点数 N）
      * @return B 是否是 A 子树
      * @time O(MN)
      * @space O(M)
      */
     public boolean isSubStructure(TreeNode A, TreeNode B) {
         if (A == null || B == null) return false; // 空树不为任何树子树
         return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
     }
     public boolean recur(TreeNode A, TreeNode B) {
         if (B == null) return true;
         if (A == null) return false;
         return A.val == B.val && recur(A.left, B.left) && recur(A.right, B.right);
     }
     ```

## Q27. 二叉树的镜像：递归、栈

​	请完成一个函数，输入一个二叉树，该函数输出它的镜像。

​	例如输入：

​		 4

​	   /   \
​	  2     7
​	 / \   / \
​	1   3 6   9
镜像输出：

​		 4

​	   /   \
​	  7     2
​	 / \   / \
​	9   6 3   1

```
示例 1：

输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

1. 递归 O(N) O(N)；

   - ```java
     /**
      * 递归
      * @author PAN
      * @param root 树根
      * @return 镜像后的树根
      * @time O(N)
      * @space O(N)
      */
     public TreeNode mirrorTree(TreeNode root) {
         if (root == null) return null;
         return change(root, root.left, root.right);
     }
     public TreeNode change(TreeNode root, TreeNode left, TreeNode right) {
         if (left == null && right == null) return root; // 叶子节点
         else if (left != null && right == null) {       // 有左子，无右子
             root.left = null;
             root.right = change(left, left.left, left.right);
         }
         else if (left == null && right != null) {       // 有右子，无左子
             root.left = change(right, right.left, right.right);
             root.right = null;
         }
         else {  // 双子
             root.left = change(right, right.left, right.right);
             root.right = change(left, left.left, left.right);
         }
         return root;
     }
     
     /**
      * 递归 2
      * @author 网友
      * @param root 树根
      * @return 镜像后的树根
      * @time O(N)
      * @space O(N)
      */
     public TreeNode mirrorTree2(TreeNode root) {
         if(root == null) return null;
         TreeNode tmp = root.left;
         root.left = mirrorTree2(root.right);
         root.right = mirrorTree2(tmp);
         return root;
     }
     ```

2. 栈 O(N) O(N) - 从上到下，每次用栈保存一层的节点，然后弹出栈进行左右节点的交换；

   - ```java
     /**
      * 栈：从上到下，每次用栈保存一层的节点，然后弹出栈进行左右节点的交换
      * @author 网友 & PAN，借鉴思路实现
      * @return 镜像后的树根
      * @time O(N)
      * @space O(N)
      */
     public TreeNode mirrorTree3(TreeNode root) {
         if (root == null) return root;
         Stack<TreeNode> s = new Stack<>();
         TreeNode node, tmp;
         s.push(root);
         while (!s.isEmpty()) {
             node = s.pop();
             if (node.left != null) s.push(node.left);
             if (node.right != null) s.push(node.right);
             tmp = node.left;
             node.left = node.right;
             node.right = tmp;
         }
         return root;
     }
     ```

## Q28. 对称的二叉树：BFS、**递归**

​	请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

​		1

   	/ \
   	2   2
   	/ \ / \
   	3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

​		1

   	/ \
   	2   2
   	\   \
   	3    3

```
示例 1：

输入：root = [1,2,2,3,4,4,3]
输出：true

示例 2：

输入：root = [1,2,2,null,3,null,3]
输出：false
```

解：

1. BFS O(N) O(N) - 逐层将该层所有节点（包括 null）加入 List，对 List 进行镜像判断；

   - ```java
     /**
      * BFS的思想：逐层将该层所有节点（包括 null）加入 List，对 List 进行镜像判断
      * @author PAN
      * @param root 树根
      * @return 是否是镜像树
      * @time 感觉是 O(N)，不太确定
      * @space O(N)
      */
     public boolean isSymmetric(TreeNode root) {
         if (root == null) return true;
         ArrayList<TreeNode> treeList = new ArrayList<TreeNode>();
         treeList.add(root);
         TreeNode point;
         while (!treeList.isEmpty()) {
             // 先判断 List 中是否镜像
             for (int i = 0; i < treeList.size() / 2; i++) {
                 if ((treeList.get(i) != null && treeList.get(treeList.size() - 1 - i) != null
                         && treeList.get(i).val == treeList.get(treeList.size() - 1 - i).val)    // List 头尾项都不为空，且值相等
                         || (treeList.get(i) == null && treeList.get(treeList.size() - 1 - i) == null )) { // 或者List 头尾项都为空
                     // 满足两个条件则符合镜像规则
                 } else return false;    // 否则不对称
             }
             // 在上一层是镜像的情况下，将下一层节点加入 List，并将上一层的节点移出 List
             int len = treeList.size();
             for (int i = 0; i < len; len--) {
                 point = treeList.get(i);
                 if (point != null) {
                     treeList.add(point.left);
                     treeList.add(point.right);
                 }
                 treeList.remove(i);
             }
         }
         return true;
     }
     ```

2. 递归 O(N) O(N)

   - ![对称二叉树递归](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/对称二叉树递归.png)

   - ```java
     /**
      * 递归：任意俩节点 L、R，有 L 左子和 R 右子对称、L 右子和 R 左子对称
      * @author 网友
      * @param root 树根
      * @return 是否是镜像树
      * @time O(N)
      * @space O(N)
      */
     public boolean isSymmetric2(TreeNode root) {
         return root == null || recur(root.left, root.right);
     }
     public boolean recur(TreeNode left, TreeNode right) {
         if (left == null && right == null) return true;
         if (left == null || right == null || left.val != right.val) return false;
         return recur(left.left, right.right) && recur(left.right, right.left);
     }
     ```

## Q29. 顺时针打印矩阵：**设定四边界**

​	输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

```
示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

解：

1. 设定四边界 O(MN) O(1) - 设定右、下、左、上边界，一个小人从左上角开始顺时针由外向里走，每转一圈边界缩小一圈；

   - ```java
     /**
      * 设定四边界：设定右、下、左、上边界，一个小人从左上角开始顺时针由外向里走，每转一圈边界缩小一圈
      * @author PAN
      * @param matrix 二维数组
      * @return 顺时针由外向里遍历结果
      * @time O(M * N)：M：行数，N：列数
      * @space O(1)：题目本身要求返回 int[]，这个数组不算在 space 里
      */
     public static int[] spiralOrder(int[][] matrix) {
         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
         // m：右边界，n：下边界，mm：左边界，nn：上边界，因为从左上顶点开始，所以 mm 初始值为 1（防止重复）
         int m = matrix.length, n = matrix[0].length, mm = 1, nn = 0;
         int num = m * n;
         int[] array = new int[num];
         int x = 0, y = 0; // 小人坐标(x, y)
         for (int i = 0; i < num; ) {
             while (y < n) { // 向右
                 array[i] = matrix[x][y];
                 i++;
                 y++;
             }
             if (i == num) break;    // 已遍历完
             y--;    // 使得超过下标的 y 复原成不超过下标
             x++;    // 防止重复添加顶点
             while (x < m) { // 向下
                 array[i] = matrix[x][y];
                 i++;
                 x++;
             }
             if (i == num) break;
             x--;
             y--;
             while (y >= nn) { // 向左
                 array[i] = matrix[x][y];
                 i++;
                 y--;
             }
             if (i == num) break;
             y++;
             x--;
             while (x >= mm) { // 向上
                 array[i] = matrix[x][y];
                 i++;
                 x--;
             }
             if (i == num) break;
             x++;
             y++;
             // 右、下、左、上一次顺时针后，将边界范围缩小一圈
             m--;
             n--;
             mm++;
             nn++;
         }
         return array;
     }
     ```

## Q30. 包含 min 函数的栈：双栈模拟

​	定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

```
示例:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

解：

1. 双栈模拟 O(1) O(N)

   - ```java
     public class MinStack {
         Stack<Integer> s1, s2;
         /**
          * 双栈模拟：一个正常栈 s1、一个存 s1 中的最小值
          * @author PAN
          * @time O(1)
          * @space O(N)
          */
         public MinStack() {
             s1 = new Stack<>();
             s2 = new Stack<>(); // 辅助栈，用来存放 s1 栈中的最小值
         }
     
         public void push(int x) {
             s1.push(x);
             if (s2.isEmpty()) s2.push(x);
             else s2.push(s2.peek() < x ? s2.peek() : x);
         }
     
         public void pop() {
             s1.pop();
             s2.pop();
         }
     
         public int top() {
             return s1.peek();
         }
     
         public int min() {
             return s2.peek();
         }
     }
     ```

## Q31. 栈的压入、弹出序列：双指针模拟、栈辅助

​	输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

```
示例 1：

输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

示例 2：

输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

解：

1. 双指针模拟 O(N ^ 2) O(1) - 双指针模拟栈操作，将 pushed 数组当成栈操作：top1、top2 分别指向两个数组模拟栈顶位置，俩 top 不相等则入栈，相等则出栈；

   - ```java
     /**
      * 双指针模拟栈操作，将 pushed 数组当成栈操作：
      * 1. top1、top2 分别指向两个数组模拟栈顶位置；
      * 2. 俩 top 不相等，则入栈，相等则出栈
      * @author PAN
      * @param pushed 入栈
      * @param popped 出栈
      * @return 是否成立
      * @time O(N ^ 2)
      * @space O(1) 时间换空间，最大程度节省空间
      */
     public static boolean validateStackSequences(int[] pushed, int[] popped) {
         int top1 = 0, top2= 0;
         int len1 = pushed.length, len2 = popped.length;
         if (len1 == 0) return true;
         while (top1 < len1 && top2 < len2) {
             if (top1 < 0) top1++;   // 防止 top1 = -1
             if (pushed[top1] != popped[top2]) { // 入栈
                 top1++;
             } else {    // 出栈
                 top1--;
                 len1--;
                 top2++;
                 for (int i = top1 + 1; i < len1; i++) { // 出栈点后的数组内容前移
                     pushed[i] = pushed[i + 1];
                 }
             }
             if (len1 == 0) return true;
         }
         return false;
     }
     ```

2. 栈辅助 O(N) O(N) - 利用一个栈辅助，先将 pushed 元素入栈，再判断栈顶与 popped 是否相等，相等则出栈，不等则继续进栈；

   - ```java
     /**
      * 栈辅助：利用一个栈辅助，先将 pushed 元素入栈，再判断栈顶与 popped 是否相等，相等则出栈，不等则继续进栈
      * @author 网友
      * @param pushed 入栈
      * @param popped 出栈
      * @return 是否成立
      * @time O(N)
      * @space O(N)
      */
     public static boolean validateStackSequences2(int[] pushed, int[] popped) {
         Stack<Integer> s = new Stack<>();
         int j = 0;
         for (int i = 0; i < pushed.length; i++) {
             s.push(pushed[i]);
             while (!s.isEmpty() && s.peek() == popped[j]) {
                 s.pop();
                 j++;
             }
         }
         return s.isEmpty();
     }
     ```

## Q32 - I. 从上到下打印二叉树：队列 BFS

​	从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

```
例如:
给定二叉树: [3,9,20,null,null,15,7],
	3
   / \
  9  20
    /  \
   15   7
   
返回：
[3,9,20,15,7]
```

解：

1. 队列 BFS O(N) O(N) - 用队列辅助逐行加入节点

   - ```java
     /**
      * 队列 BFS：用队列辅助逐行加入节点
      * @author PAN
      * @param root 树根
      * @return 从上到下，从左到右顺序打印
      * @time O(N)
      * @space O(N)
      */
     public int[] levelOrder(TreeNode root) {
         ArrayList<TreeNode> list = new ArrayList();
         if (root != null) list.add(root);
         TreeNode point;
         int i = 0;
         while (i < list.size()) {
             point = list.get(i);
             if (point.left != null) list.add(point.left);   // 左子不为空
             if (point.right != null) list.add(point.right); // 右子不为空
             i++;
         }
         int[] treeNode = new int[list.size()];
         i = 0;
         for (TreeNode node : list) {
             treeNode[i] = node.val;
             i++;
         }
         return treeNode;
     }
     ```

## Q32 - II. 从上到下打印二叉树 II：队列 BFS、**递归 BFS**

​	从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

```
例如:
给定二叉树: [3,9,20,null,null,15,7],
	3
   / \
  9  20
    /  \
   15   7
   
返回其层次遍历结果：
[
  [3],
  [9,20],
  [15,7]
]
```

解：

1. 队列 BFS O(N) O(N) - 用队列辅助逐行加入节点，用上一问代码改动（加入分层）；

   - ```java
     /**
      * 队列 BFS - 用队列辅助逐行加入节点，用上一问代码改动（加入分层）
      * @author PAN
      * @param root 树根
      * @return 从上到下从左到右打印节点
      * @time O(N)
      * @space O(N)
      */
     public List<List<Integer>> levelOrder2(TreeNode root) {
         List<List<Integer>> treeNodeVal2 = new ArrayList<>();
         ArrayList<TreeNode> list = new ArrayList();
         if (root != null) list.add(root);
         TreeNode point;
         while (!list.isEmpty()) {
             List<Integer> tmp = new ArrayList<>();
             int len = list.size();
             for (int j = len; j > 0; j--){
                 point = list.remove(0);
                 tmp.add(point.val);
                 if (point.left != null) list.add(point.left);   // 左子不为空
                 if (point.right != null) list.add(point.right); // 右子不为空
             }
             treeNodeVal2.add(tmp);
         }
     
         return treeNodeVal2;
     }
     ```

2. 递归 BFS O(N) O(N) - 递归完成 BFS；

   - ```java
     public List<List<Integer>> treeNodeVal = new ArrayList<>();
     
     /**
      * 递归 - 递归完成BFS
      * @author 网友
      * @param root 树根
      * @return 从上到下从左到右打印节点
      * @time O(N)
      * @space O(N)
      */
     public List<List<Integer>> levelOrder(TreeNode root) {
         BFS(root, 0);
         return treeNodeVal;
     }
     public void BFS(TreeNode root, int k) { // k 记录层数
         if (root == null) return;
         if (treeNodeVal.size() <= k) treeNodeVal.add(new ArrayList<>());
         treeNodeVal.get(k).add(root.val);
         if (root.left != null) BFS(root.left, k + 1);
         if (root.right != null) BFS(root.right, k + 1);
     }
     ```

## Q32 - III. 从上到下打印二叉树 III：队列 BFS、递归 BFS

​	请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

```java
例如:
给定二叉树: [3,9,20,null,null,15,7],
	3
   / \
  9  20
    /  \
   15   7
   
返回其层次遍历结果：
[
  [3],
  [20,9],
  [15,7]
]
```

解：

1. 队列 BFS O(N) O(N) - 用队列辅助逐行加入节点，用上一问代码改动（奇数层向队头添加，欧偶数层向队尾添加）；

   - ```java
     /**
      * 队列 BFS - 用队列辅助逐行加入节点，用上一问代码改动（奇数层向队头添加，欧偶数层向队尾添加）
      * @author PAN
      * @param root 树根
      * @return 奇数层从左到右，偶数层从右到左打印节点
      * @time O(N)
      * @space O(N)
      */
     public List<List<Integer>> levelOrder2(TreeNode root) {
         List<List<Integer>> treeNodeVal2 = new ArrayList<>();
         ArrayList<TreeNode> list = new ArrayList();
         if (root != null) list.add(root);
         TreeNode point;
         while (!list.isEmpty()) {
             List<Integer> tmp = new ArrayList<>();
             for (int j = list.size(); j > 0; j--){
                 point = list.remove(0);
                 // 奇数层向队头添加，欧偶数层向队尾添加
                 if (treeNodeVal2.size() % 2 == 1) tmp.add(0, point.val);
                 else tmp.add(point.val);
                 if (point.left != null) list.add(point.left);   // 左子不为空
                 if (point.right != null) list.add(point.right); // 右子不为空
             }
             // 或者用倒序函数
             // if (treeNodeVal2..size() % 2 == 1) Collections.reverse(tmp);
             treeNodeVal2.add(tmp);
         }
         return treeNodeVal2;
     }
     ```

2. 递归 BFS O(N) O(N) - 递归完成BFS，奇数层向队头添加，欧偶数层向队尾添加；

   - ```java
     public List<List<Integer>> treeNodeVal = new ArrayList<>();
     
     /**
      * 递归 - 递归完成BFS，奇数层向队头添加，欧偶数层向队尾添加
      * @author 网友
      * @param root 树根
      * @return 奇数层从左到右，偶数层从右到左打印节点
      * @time O(N)
      * @space O(N)
      */
     public List<List<Integer>> levelOrder(TreeNode root) {
         BFS(root, 0);
         return treeNodeVal;
     }
     public void BFS(TreeNode root, int k) {
         if (root == null) return;
         if (treeNodeVal.size() <= k) treeNodeVal.add(new ArrayList<>());
         // 奇数层向队头添加，欧偶数层向队尾添加
         if (k % 2 == 1) treeNodeVal.get(k).add(0, root.val);
         else treeNodeVal.get(k).add(root.val);
     
         BFS(root.left, k + 1);
         BFS(root.right, k + 1);
     }
     ```

## Q33. 二叉搜索树的后序遍历序列：递归、**辅助单调栈**

​	输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

```
参考以下这颗二叉搜索树：
 	 5
	/ \
   2   6
  / \
 1   3
 
示例 1：

输入: [1,6,3,2,5]
输出: false

示例 2：

输入: [1,3,2,6,5]
输出: true
```

解：

1. 递归 O(N ^ 2) O(N)：

   - ```java
     /**
      * 递归：
      * 1. 二叉搜索树左子都比 root 小，右子都比 root 大；
      * 2. 数组最后一个元素为 root，从头开始找到一直比 root 小的范围数组下标 left，再找比 root 大的范围数组 right；
      * 3. 若找到的 right != len - 1，意味不符合二叉搜索树；
      * @author PAN
      * @param postorder 后序遍历数组
      * @return 后序遍历是否是二叉搜索树
      * @time O(N ^ 2)
      * @space O(N)
      */
     public static boolean verifyPostorder(int[] postorder) {
         // 为空或者长度为 0 意味到达了叶子节点，返回 true
         if (postorder == null || postorder.length == 0) return true;
         int len = postorder.length;
         int root = postorder[len - 1];
         // 二叉搜索树左子都比 root 小，右子都比 root 大
         int left = 0;
         while (postorder[left] < root) left++;
         int right = left;
         while (postorder[right] > root) right++;
     
         if (right == len - 1) return verifyPostorder(Arrays.copyOfRange(postorder, 0, left)) && verifyPostorder(Arrays.copyOfRange(postorder, left, right));
         else return false;  // 不是二叉搜索树
     }
     ```

2. 辅助单调栈 O(N) O(N)

   - ![辅助单调栈](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/辅助单调栈.png)
   
   - ```java
     /**
      * 辅助单调栈：后序遍历的倒序（根、右、左）类似先序遍历的镜像；
      * @author 网友
      * @param postorder 后序遍历数组
      * @return 后序遍历是否是二叉搜索树
      * @time O(N)
      * @space O(N)
      */
     public boolean verifyPostorder2(int[] postorder) {
         Stack<Integer> stack = new Stack<>();
         int root = Integer.MAX_VALUE;
         for(int i = postorder.length - 1; i >= 0; i--) {
             if(postorder[i] > root) return false;
             while(!stack.isEmpty() && stack.peek() > postorder[i])
                 root = stack.pop();
             stack.add(postorder[i]);
         }
         return true;
     }
     ```

## Q34. 二叉树中和为某一值的路径

​	输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

```
示例:
给定如下二叉树，以及目标和 target = 22，

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1

返回:
[
   [5,4,11,2],
   [5,8,4,5]
]
```

解：

1. DFS O(N) O(N)

   - ```java
     public class PathSum {
         List<List<Integer>> pathsList = new ArrayList<>();
         ArrayList<Integer> pathList = new ArrayList<>();
         int target;
         
         /**
          * DFS：从根节点加到叶子节点，返回符合要求的路径
          * @author PAN
          * @param root 跟节点
          * @param target 目标节点路径和
          * @return 路径列表
          * @time O(N)
          * @space O(N)
          */
         public List<List<Integer>> pathSum(TreeNode root, int target) {
             this.target = target;
             DFS(root, 0);
             return pathsList;
         }
         public void DFS(TreeNode root, int sum) {
             if (root == null) return;
             if (root.left == null && root.right == null) {
                 pathList.add(root.val);
                 sum += root.val;
                 if (sum == target) pathsList.add(new ArrayList<>(pathList));
                 pathList.remove(pathList.size() - 1);
                 return;
             }
             pathList.add(root.val);
             sum += root.val;
             DFS(root.left, sum);
             DFS(root.right, sum);
             pathList.remove(pathList.size() - 1);
         }
     }
     ```

## Q35. 复杂链表的复制：遍历、**哈希表**、**拼接 + 拆分**

​	请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

解：

1. 遍历 O(N ^ 2) O(1) - 先利用 next 形成链表，再找到 random 进行复制；

   - ```java
     /**
      * 遍历：先利用 next 形成链表，再找到 random 进行复制
      * @author PAN
      * @param head 待复制的链表头节点
      * @return 复制的新链表头节点（【注意】是深拷贝）
      * @time O(N ^ 2)
      * @space O(1)
      */
     public Node copyRandomList(Node head) {
     
         if (head == null) return null;
     
         Node newHead = new Node(head.val), oldPoint = head, newPoint = newHead;
         // 先把新链表利用 next 链接在一起（深拷贝）
         while (oldPoint.next != null) {
             newPoint.next = new Node(oldPoint.next.val);
             oldPoint = oldPoint.next;
             newPoint = newPoint.next;
         }
     
         // 再补充每个节点的 random
         oldPoint = head;
         newPoint = newHead;
         while (oldPoint != null) {
             if (oldPoint.random == null) {
                 newPoint.random = null;
             } else {
                 Node tmp1 = head, tmp2 = newHead;
                 while (tmp1 != oldPoint.random) {   // 走完整个队列找到 random 的位置
                     tmp1 = tmp1.next;
                     tmp2 = tmp2.next;
                 }
                 newPoint.random = tmp2;
             }
             oldPoint = oldPoint.next;
             newPoint = newPoint.next;
         }
     
         return newHead;
     }
     ```

2. 哈希表 O(N) O(N) - 先构建原链表节点、新链表对应节点的 key, value 键值对，再遍历构建新链表各节点的 next 和 random；

   - ```java
     /**
      * 哈希表：
      * 1. 构建 原链表节点 和 新链表对应节点的 key, value 键值对；
      * 2. 再遍历构建新链表各节点的 next 和 random
      * @author 网友 & PAN，学习网友思路自己实现
      * @param head 原链表节点
      * @return 新链表节点
      * @time O(N)
      * @space O(N)
      */
     public Node copyRandomList2(Node head) {
     
         if (head == null) return null;
     
         // 构建 原链表节点 和 新链表对应节点 的 key, value 键值对；
         Node point = head;
         HashMap<Node, Node> nodeMap = new HashMap<>();
         while (point != null) {
             nodeMap.put(point, new Node(point.val));
             point = point.next;
         }
     
         // 再遍历构建新链表各节点的 next 和 random
         point = head;
         while (point != null) {
             nodeMap.get(point).next = nodeMap.get(point.next);
             nodeMap.get(point).random = nodeMap.get(point.random);
             point = point.next;
         }
         return nodeMap.get(head);
     }
     ```

3. 拼接 + 拆分 O(N) O(1) - 先在原链表中构建新链表，每一个节点复制一个到其后；再利用 point.next.random = point.random.next 构建 random；最后拆分新旧链表。

   - ```java
     /**
      * 拼接 + 拆分：
      * 1. 在原链表中构建新链表，每一个节点复制一个到其后面；
      * 2. 利用 point.next.random = point.random.next 构建 random；
      * 3. 拆分新旧链表
      * @author 网友 & PAN，学习网友思路自己实现
      * @param head 原链表节点
      * @return 新链表节点
      * @time O(N)
      * @space O(1)
      */
     public Node copyRandomList3(Node head) {
     
         if (head == null) return null;
     
         // 先构建链表，将每一个节点复制一个到其后面
         Node point = head, tmp;
         while (point != null) {
             tmp = new Node(point.val);
             tmp.next = point.next;
             point.next = tmp;
             point = tmp.next;
         }
     
         // 再构建 random
         point = head;
         while (point != null) {
             if (point.random != null)
                 point.next.random = point.random.next;
             point = point.next.next;
         }
     
         // 最后将连在一起的新旧链表分离出来
         point = head;
         Node newHead = point.next, newPoint = newHead;
         while (newPoint.next != null) {
             point.next = point.next.next;
             newPoint.next = newPoint.next.next;
             point = point.next;
             newPoint = newPoint.next;
         }
         point.next = null;
         return newHead;
     }
     ```

## Q36. 二叉搜索树与双向链表：**红黑树旋转**、**中序遍历 + 递归**

​	输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

解：

1. 红黑树旋转 O(N) O(1)；

   - ```java
     /**
      * 红黑树旋转：运用红黑树旋转的思想来形成双向链表
      * 1. 因是二叉搜索树，所以对左子节点判断其是否有右子（有则左旋）、对右子节点判断是否有左子（有则右旋）；
      * 2. 每轮旋转可能导致子树结构变化，故需要对树从上至下进行层层旋转；
      * 3. 直到整个树左子只有左子、右子只有右子（即可视为类单链表结构）；
      * 4. 再从 root 开始构建双向的链表，即左子的 right 指向上一层节点、右子的 left 指向上一层节点；
      * 5. 最后将 head、end 首尾相连；
      * @author PAN
      * @param root 二叉搜索树根
      * @return 排序循环双向链表头节点
      * @time O(N)
      * @space O(1)
      */
     public Node treeToDoublyList(Node root) {
         if (root == null) return null;
     
         // 左子树的旋转、右子树的旋转
         Node head = leftDFS(root, root.left), end = rightDFS(root, root.right);
         // 无左子树或无右子树对 head、end的处理
         if (head == null) head = root;
         if (end == null) end = root;
     
         // 利用旋转好的类单链表结构构建双链表
         while (root.left != null) { // 左子的 right 指向上一层节点
             root.left.right = root;
             root = root.left;
         }
         while (root.right != null) {
             root.right.left = root; // 右子的 left 指向上一层节点
             root = root.right;
         }
         // head、end 首尾相连
         head.left = end;
         end.right = head;
     
         return head;
     }
     
     /**
      * 层层旋转
      * @param father 旋转处的父节点
      * @param root 待旋转的节点
      * @return 树最左下角的节点
      */
     public Node leftDFS(Node father, Node root) {
         if (root == null) return null;  // 空则不转
     
         // 层层找到第一个右子不为空的节点
         while (root.right == null && root.left != null) {
             father = root;
             root = root.left;
         }
     
         // 对这个节点开始往下逐层旋转
         while (root.right != null) {
             root = leftSpin(root);  // 左旋
             father.left = root;     // 旋转完毕后将父节点指向新的节点
             // 旋转后找到下一个需要旋转的点
             while (root.right == null && root.left != null) {
                 father = root;
                 root = root.left;
             }
         }
         return root;
     }
     public Node rightDFS(Node father, Node root) {
         if (root == null) return null;  // 空则不转
     
         // 层层找到第一个左子不为空的节点
         while (root.left == null && root.right != null) {
             father = root;
             root = root.right;
         }
     
         // 对这个节点开始往下逐层旋转
         while (root.left != null) {
             root = rightSpin(root); // 左旋
             father.right = root;    // 旋转完毕后将父节点指向新的节点
             // 旋转后找到下一个需要旋转的点
             while (root.left == null && root.right != null) {
                 father = root;
                 root = root.right;
             }
         }
         return root;
     }
     public Node leftSpin(Node root) {   // 左旋
         // 1. 将待旋转节点 root 的右子作为其父节点：形成新的 newRoot，newRoot.left = root；
         // 2. 再将 newRoot 旧左子改为 root 的右子；
         Node newRoot = root.right, tmp = newRoot.left;
         newRoot.left = root;
         root.right = tmp;
         return newRoot;
     }
     public Node rightSpin(Node root) {  // 右旋
         // 1. 将待旋转节点 root 的左子作为其父节点：形成新的 newRoot，newRoot.right = root；
         // 2. 再将 newRoot 旧右子改为 root 的左子；
         Node newRoot = root.left, tmp = newRoot.right;
         newRoot.right = root;
         root.left = tmp;
         return newRoot;
     }
     ```

2. 中序遍历 + 递归；

   - ```java
     Node pre, head;
     
     /**
      * 中序遍历 + 递归
      * @author 网友
      * @param root 二叉搜索树根
      * @return 排序循环双向链表头节点
      * @time O(N)
      * @space O(N)
      */
     public Node treeToDoublyList2(Node root) {
         if(root == null) return null;
         dfs(root);
         head.left = pre;
         pre.right = head;
         return head;
     }
     // cur：当前中序节点，pre：当前节点在中序中的上一个节点
     void dfs(Node cur) {
         if(cur == null) return;
         dfs(cur.left);
         if(pre != null) pre.right = cur;
         else head = cur;
         cur.left = pre;
         pre = cur;
         dfs(cur.right);
     }
     ```

## Q37. 序列化二叉树：队列实现层序遍历

请实现两个函数，分别用来序列化和反序列化二叉树。

```
示例: 

你可以将以下二叉树：
	1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```

解：

1. 队列实现层序遍历：

   - ```java
     public class Codec {
     
         public static void main(String[] args) {
             String s = "[1,2,null,3,null,4,null,5]";
             Codec codec = new Codec();
             TreeNode treeNode = codec.deserialize(s);
             String ss = codec.serialize(treeNode);
         }
     
         /**
          * 利用队列：将一颗树按照从上到下从左到右序列化为 String
          * @author PAN
          * @param root 树根
          * @return String
          * @time O(N)
          * @space O(N)
          */
         public String serialize(TreeNode root) {
             if (root == null) return "";
     
             ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
             nodeList.add(root);
             StringBuilder s = new StringBuilder();
             while (!nodeList.isEmpty()) {
                 TreeNode tmp = nodeList.remove(0);   // 取出对头，将其左右子加入队列
                 if (tmp != null) {
                     s.append(tmp.val).append(",");
                     nodeList.add(tmp.left);
                     nodeList.add(tmp.right);
                 } else s.append("null,");   // 为空则加入 "null"，会导致叶子节点也将其左右子的 "null" 加入 String，需要后面剔除
             }
     
             // 将序列最后连续的 "null" 全部删除
             String[] split = s.toString().split(",");
             int i = split.length - 1;
             while (split[i].equals("null"))
                 i--;
     
             // 重新构建符合要求的 String
             StringBuilder str = new StringBuilder("[");
             for (int j = 0; j < i; j++)
                 str.append(split[j]).append(",");
             str.append(split[i]).append("]");
     
             return str.toString();
         }
     
         /**
          * 利用双队列：将序列化的 String 重新构建为树
          *  1. 父层队列头节点出队，不为空则将子层队列的头两个节点为其左右子；
          *  2. 一轮结束后旧子层变为新父层，再构建新子层；
          * @author PAN
          * @param data 序列化 String
          * @return root
          * @time O(N)
          * @space O(N)
          */
         public TreeNode deserialize(String data) {
             if (data.equals("")) return null;
     
             // 删除首位的括号
             StringBuilder s = new StringBuilder(data);
             s.delete(0 ,1);
             s.delete(s.length() - 1, s.length());
     
             // 切分后构建所有树节点并给 val 赋值
             String[] split = s.toString().split(",");
             TreeNode[] nodes = new TreeNode[split.length];
             for (int i = 0; i < split.length; i++) {
                 if (!split[i].equals("null")) {
                     nodes[i] = new TreeNode(Integer.parseInt(split[i]));
                 }
             }
     
             ArrayList<TreeNode> fatherList = new ArrayList<>(); // 父层所有节点
             ArrayList<TreeNode> sonList = new ArrayList<>();    // 子层所有节点
             fatherList.add(nodes[0]);
             int sonNum = fatherList.size() * 2, restNum = nodes.length - 1 , i = 1;
             while (sonNum < restNum) {          // son 个数小于 nodes[] 里剩余没访问节点的个数时循环
                 int count = 0, notNullNum = 0;  // count：子的个数，notNullNum：子中非空的个数（用来合理计算再下一层子数）
                 while (count < sonNum) {        // 构建子层队列
                     if (nodes[i] != null) notNullNum++;
                     sonList.add(nodes[i]);
                     i++;    // node[] 数组的下标
                     count++;
                 }
                 restNum -= sonList.size();  // 每构建一次子层将剩余节点个数更新
     
                 // 构建父与左右子的联系
                 int j = 0;
                 while (!fatherList.isEmpty()) {
                     TreeNode tmp = fatherList.remove(0);
                     if (tmp != null) {
                         tmp.left = sonList.get(j);
                         tmp.right = sonList.get(j + 1);
                         j += 2;
                     }
                 }
     
                 // 旧子层变为新父层
                 ArrayList<TreeNode> tmp = fatherList;
                 fatherList = sonList;
                 sonList = tmp;
                 sonNum = notNullNum * 2;
             }
     
             // 处理剩余的父子节点
             for ( ; i < nodes.length; i++)
                 sonList.add(nodes[i]);
             while (!fatherList.isEmpty()) {
                 TreeNode tmp = fatherList.remove(0);
                 if (tmp != null) {
                     tmp.left = sonList.isEmpty() ? null : sonList.remove(0);
                     tmp.right = sonList.isEmpty() ? null : sonList.remove(0);
                 }
             }
     
             return nodes[0];
         }
     ```

## Q38. 字符串的排列：**回溯实现全排列**

​	输入一个字符串，打印出该字符串中字符的所有排列。

​	你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

```
示例:
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

解：

1. **回溯 O(N! * N) O(N ^ 2)：**

   - ![字符串全排列](/Users/panpan/Documents/Code/DevelopTips/图/算法/字符串全排列.png)

   - ```java
     public class Permutation {
         ArrayList<String> list = new ArrayList<>();
         char[] c;
     
         public static void main(String[] args) {
             String s = "abcd";
             String[] result = new Permutation().permutation(s);
             for (int i = 0; i < result.length; i++) {
                 System.out.println(result[i]);
             }
         }
     
         /**
          * 回溯实现全排列：通过字符交换，先固定第 1 位、再第 2 位、最后第 n 位
          * @author 参考网友自己实现
          * @param s 字符串
          * @return 全排列字符串数组
          * @time O(N! * N)
          * @space O(N ^ 2)
          */
         public String[] permutation(String s) {
             c = s.toCharArray();
             recur(c, 0, c.length);
             return list.toArray(new String[c.length]);
         }
         public void recur(char[] c, int start, int len) {
             if (start == len - 1) {
                 list.add(String.valueOf(c));
             } else {
                 HashSet<Character> set = new HashSet<>();   // 防止同一层递归出现重复元素
                 for (int i = start; i < len; i++) {
                     if (set.contains(c[i])) continue;
                     set.add(c[i]);
                     swap(c, start, i);
                     recur(c, start + 1, len);   // 进入下一层递归，即下一位
                     swap(c, start, i);               // 返回时交换回来
                 }
             }
         }
         public  void swap(char[] c, int i, int j) {
             char tmp = c[i];
             c[i] = c[j];
             c[j] = tmp;
         }
     }
     ```

## Q39. 数组中出现次数超过一半的数字：哈希表统计、数组排序、**摩尔投票**

​	数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

​	你可以假设数组是非空的，并且给定的数组总是存在多数元素。

```
示例 1:
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

解：

1. 哈希表统计 O(N) O(N)；

2. 数组排序 - 排在中间的那个就是；

3. **摩尔投票 O(N) O(1)；**

   - ```java
     /**
      * 摩尔投票：票数正负抵消，该数要比剩余所有数还要多
      * @author PAN
      * @param nums 数组
      * @return 出现超多一半的数字
      * @time O(N)
      * @space O(1)
      */
     public int majorityElement(int[] nums) {
         int num = nums[0], count = 0;
         for (int i = 0; i < nums.length; i++) {
             if (nums[i] == num) count++;
             else {
                 count--;
                 if (count <= 0) {
                     num = nums[i];
                     count = 1;
                 }
             }
         }
         return num;
     }
     ```

## Q40. 最小的 k 个数：比较排挤、计数排序、官方 `sort()`、**快速排序**

​	输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

```
示例 1：
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]

示例 2：
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

解：

1. 默认前 k 个最小，比较排挤最大值 O(N * k) O(K)；

   - ```java
     /**
      * 默认前 k 个是最小的，再从 k + 1 开始比较，发现更小的挤掉原来最大的
      * @author PAN
      * @param arr 数组
      * @param k k 个
      * @return 最小 k 个数
      * @time O(N * k)
      * @space O(K)
      */
     public int[] getLeastNumbers(int[] arr, int k) {
         if (k == 0) return new int[0];
         int[] minArr = Arrays.copyOfRange(arr, 0, k);   // 将前 k 个拷贝为新数组并排序
         Arrays.sort(minArr);
         ArrayList<Integer> list = new ArrayList<>();    // 利用 List 来实现比较并挤出最大值
         for (int value : minArr) list.add(value);
     
         for (int i = k; i < arr.length; i++) {  // 从第 k + 1 开始与 List 中最大值比较
             if (arr[i] < list.get(k - 1)) {
                 list.remove(k - 1); // 找到比最大值小的挤出最大值
                 int len = list.size();
                 for (int j = k - 2; j >= 0; j--) {  // 插入新值
                     if (list.get(j) <= arr[i]) {
                         list.add(j + 1, arr[i]);
                         break;
                     }
                 }
                 if (list.size() == len) list.add(0, arr[i]);
             }
         }
     
         for (int i = 0; i < list.size(); i++)
             minArr[i] = list.get(i);
         return minArr;
     }
     ```

2. 计数法排序 O(N * logN) O(N)；

   - ```java
     /**
      * 哈希表：先统计每个数字出现的次数，在从小到大生成长度为 k 的数组
      * @author PAN
      * @param arr 数组
      * @param k k 个
      * @return 最小 k 个数
      * @time O(N * logN)
      * @space O(N)
      */
     public int[] getLeastNumbers2(int[] arr, int k) {
         HashMap<Integer, Integer> map = new HashMap<>();
         for (int key : arr) {
             if (map.containsKey(key)) map.put(key, map.get(key) + 1);
             else map.put(key, 1);
         }
         int[] minArr = new int[k];
         Integer[] keys = map.keySet().toArray(new Integer[0]);
         Arrays.sort(keys);
         int i = 0, sum = 0;
         for (Integer key : keys) {
             if (sum + map.get(key) <= k) {
                 sum += map.get(key);
                 for ( ; i < sum; i++)
                     minArr[i] = key;
             } else {
                 for ( ; i < k; i++)
                     minArr[i] = key;
                 break;
             }
         }
         return minArr;
     }
     ```

3. 调用官方 `sort()` O(N * logN) O(1)；

   - ```java
     /**
      * 调官方 sort 直接返回前 k 个
      * @author PAN
      * @param arr 数组
      * @param k k 个
      * @return 最小 k 个数
      * @time O(N * logN)
      * @space O(1)
      */
     public int[] getLeastNumbers3(int[] arr, int k) {
         Arrays.sort(arr);
         return Arrays.copyOfRange(arr, 0, k);
     }
     ```

4. **快速排序 O(N) O(logN)；**

   - ```java
     /**
      * 快速排序：利用快排的哨兵划分成最小的 k 个数和其他数
      * @author 网友
      * @param arr 数组
      * @param k k 个
      * @return 最小 k 个数
      * @time O(N)
      * @space O(LogN)
      */
     public int[] getLeastNumbers4(int[] arr, int k) {
         if (k >= arr.length) return arr;
         return quickSort(arr, k, 0, arr.length - 1);
     }
     private int[] quickSort(int[] arr, int k, int l, int r) {
         int i = l, j = r;
         while (i < j) {
             while (i < j && arr[j] >= arr[l]) j--;  // 找到比基准小的
             while (i < j && arr[i] <= arr[l]) i++;  // 找到比基准大的
             swap(arr, i, j);    // 使得小的在基准左边、大的在基准右边
         }
         swap(arr, i, l);    // 将基准放到中间
         // 一轮过后，基准前的数恰好为 k 直接返回、大于 k 继续递归左边、小于 k 继续递归右边
         if (i > k) return quickSort(arr, k, l, i - 1);
         if (i < k) return quickSort(arr, k, i + 1, r);
         return Arrays.copyOf(arr, k);
     }
     private void swap(int[] arr, int i, int j) {
         int tmp = arr[i];
         arr[i] = arr[j];
         arr[j] = tmp;
     }
     ```

## Q41. 数据流中的中位数：插入排序、**大小堆**

​	如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

```
例如，
[2,3,4] 的中位数是 3
[2,3] 的中位数是 (2 + 3) / 2 = 2.5
```

解：

1. 插入排序 O(N ^ 2) O(N)；

   - ```java
     /**
      * 链表实现：addNum 时用插入排序，findMedian 时按奇偶判断返回
      * @author PAN
      * @time O(N ^ 2)
      * @space O(N)
      */
     ArrayList<Integer> list;
     public MedianFinder() {
         list = new ArrayList<>();
     }
     
     public void addNum(int num) {
         int i = 0;
         while (i < list.size() && num > list.get(i)) i++;
         list.add(i, num);
     }
     
     public double findMedian() {
         return list.size() % 2 == 0 ?
                 list.get(list.size() / 2) + list.get(list.size() / 2 - 1) / 2.0 :
                 list.get(list.size() / 2);
     }
     ```

2. **大小堆 O(log N) O(N)；**

   - ```java
     /**
      * 优先队列实现大小堆：小顶堆存储较大的一半，大顶堆存储较小的一半；
      * @author 网友
      * @time O(log N)
      * @space O(N)
      */
     Queue<Integer> A, B;
     public MedianFinder() {
         A = new PriorityQueue<>();
         B = new PriorityQueue<>((x, y) -> (y - x));
     }
     
     public void addNum2(int num) {
         if (A.size() != B.size()) {
             A.add(num);
             B.add(A.poll());
         } else {
             B.add(num);
             A.add(B.poll());
         }
     }
     
     public double findMedian2() {
         return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
     }
     ```

## Q42. 连续子数组的最大和：**DP 动态规划**

​	输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。

```
示例1:
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

解：

1. **DP 动态规划 O(N) O(1)；**

   - ```java
     /**
      * DP：转移方程 dp[i - 1] > 0 执行 dp[i] = dp[i - 1] + nums[i]、dp[i - 1] <= 0 执行 dp[i] = nums[i]
      * @author 网友
      * @param nums 数组
      * @return 连续子数组最大和
      * @time O(N)
      * @space O(1)
      */
     public int maxSubArray(int[] nums) {
         int maxSum = nums[0];
         for (int i = 1; i < nums.length; i++) {
             nums[i] += Math.max(nums[i - 1], 0);
             maxSum = Math.max(maxSum, nums[i]);
         }
         return maxSum;
     }
     ```

## Q43. 1 ～ n 整数中 1 出现的次数：优化暴力、**递归**、**找规律**

​	输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

```
示例 1：
输入：n = 12
输出：5

示例 2：
输入：n = 13
输出：6
```

解：

1. 优化暴力 O(N ^ 2) O(1)；

   - ```java
     /**
      * 优化全暴力：利用 bitNum * Math.pow(10, bitNum - 1) 减少部分计算量，还是超时
      * @author PAN
      * @param n 整数
      * @return 含 1 的个数
      * @time O(N ^ 2)
      * @space O(1)
      */
     public int countDigitOne(int n) {
         int count = 0, bitNum = 0, num = n;
         while (num != 0) {
             num /= 10;
             bitNum++;
         }
         bitNum--;
         count = (int) (bitNum * Math.pow(10, bitNum - 1));
         int i = (int) Math.pow(10, bitNum);
         for ( ; i <= n; i++) {
             String s = String.valueOf(i);
             if (s.contains("1"))
                 for (int j = 0; j < s.length(); j++)
                     if (s.charAt(j) == '1') count++;
         }
         return count;
     }
     ```

2. **递归 O(log N) O(log N)；**

   - ```java
     /**
      * 递归：在利用 bitNum * Math.pow(10, bitNum - 1) 的基础上，对剩余的数再递归
      * @author 网友
      * @param n 整数
      * @return 含 1 的个数
      * @time O(log N)
      * @space O(log N)
      */
     public int countDigitOne2(int n) {
         return f(n);
     }
     private int f(int n ) {
         if (n <= 0)
             return 0;
         String s = String.valueOf(n);
         int high = s.charAt(0) - '0';
         int pow = (int) Math.pow(10, s.length()-1);
         int last = n - high * pow;
         if (high == 1) {
             return f(pow-1) + last + 1 + f(last);
         } else {
             return pow + high * f(pow-1) + f(last);
         }
     }
     ```

3. **找规律 O(log N) O(1)；**

   - ![统计1的个数](/Users/panpan/Documents/Code/DevelopTips/图/LeetCode/《剑指Offer（第2版）》/统计1的个数.png)

   - ```java
     /**
      * 找规律
      * @author 网友
      * @param n 整数
      * @return 含 1 的个数
      * @time O(log N)
      * @space O(1)
      */
     public int countDigitOne3(int n) {
         int digit = 1, res = 0;
         int high = n / 10, cur = n % 10, low = 0;
         while(high != 0 || cur != 0) {
             if(cur == 0) res += high * digit;
             else if(cur == 1) res += high * digit + low + 1;
             else res += (high + 1) * digit;
             low += cur * digit;
             cur = high % 10;
             high /= 10;
             digit *= 10;
         }
         return res;
     }
     ```























