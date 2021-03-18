package codingInterviews.Q26;

import java.util.Stack;

/**
 * Q26. 树的子结构
 * 【题目】
 *      输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 【示例】
 *      例如:
 *      给定的树 A:
 *
 * 	        3
 * 	       / \
 *        4   5
 *      / \
 *     1   2
 *      给定的树 B：
 *       4
 *      /
 *     1
 *      返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 */
public class IsSubStructure {
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
}
