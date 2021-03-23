package codingInterviews.Q33;

import java.util.Arrays;
import java.util.Stack;

/**
 * Q33. 二叉搜索树的后序遍历序列
 * 【题目】
 *      输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 *      如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。
 * 【示例】
 *      参考以下这颗二叉搜索树：
 *  	     5
 * 	        / \
 *         2   6
 *        / \
 *       1   3
 *      示例 1：
 *      输入: [1,6,3,2,5]
 *      输出: false
 *      示例 2：
 *      输入: [1,3,2,6,5]
 *      输出: true
 */
public class VerifyPostorder {
    public static void main(String[] args) {
        int[] a1 = {1, 6, 3, 2, 5};
        int[] a2 = {1, 3, 2, 6, 5};
        int[] a3 = {1,2,5,10,6,9,4,3};
        System.out.println(verifyPostorder(a1));
        System.out.println(verifyPostorder(a2));
        System.out.println(verifyPostorder(a3));
    }

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
}
