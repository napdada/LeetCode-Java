package codingInterviews.Q54;

import java.util.ArrayList;

/**
 * Q54. 二叉搜索树的第 k 大节点
 * 【题目】
 *      给定一棵二叉搜索树，请找出其中第k大的节点。
 * 【示例】
 *      输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 *      输出: 4
 */
public class KthLargest {
    ArrayList<Integer> list = new ArrayList<>();
    int k, res;
    boolean flag = false;

    /**
     * 反向中序遍历：右子、root、左子，得到递减序列，得到第 k 个直接终止递归
     * @author NAP
     * @param root 二叉搜索树根节点
     * @param k 第 k 大
     * @return 第 k 大的数
     * @time O(log N)
     * @space O(N)
     */
    public int kthLargest(TreeNode root, int k) {
        if (k == 0 || root == null) return 0;
        this.k = k;
        recur(root);
        return res;
    }

    public void recur(TreeNode root) {
        if (root == null) return;
        recur(root.right);
        if (k == 0) return;
        if (-- k == 0) res = root.val;
        recur(root.left);
    }

    /**
     * 中序遍历：左子、root、右子，得到递增序列
     * @author NAP
     * @param root 二叉搜索树根节点
     * @param k 第 k 大
     * @return 第 k 大的数
     * @time O(log N)
     * @space O(N)
     */
    public int kthLargest2(TreeNode root, int k) {
        if (k == 0 || root == null) return 0;
        this.k = k;
        recur(root);
        return list.get(list.size() - k);
    }

    public void recur2(TreeNode root) {
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        if (root.left != null) recur(root.left);
        list.add(root.val);
        if (root.right != null) recur(root.right);
    }
}
