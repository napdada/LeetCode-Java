package codingInterviews.Q7;

import java.util.HashMap;

/**
 * Q7. 重建二叉树
 * 【题目】
 *      输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 【示例】
 *      例如，给出
 *
 *      前序遍历 preorder = [3,9,20,15,7]
 *      中序遍历 inorder = [9,3,15,20,7]
 *
 *      返回如下的二叉树：
 *
 *             3
 *            / \
 *           9  20
 *             /  \
 *            15   7
 */
public class BuildTree {

    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();

    TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        this.preorder = preorder;
        for (int i = 0; i < n; i++) {
            this.dic.put(inorder[i], i);
        }
        return recur(0, 0, n - 1);
    }

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
}
