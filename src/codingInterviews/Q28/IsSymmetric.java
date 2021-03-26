package codingInterviews.Q28;

import java.util.ArrayList;

/**
 * Q28. 对称的二叉树
 * 【题目】
 *      请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 *      例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * ​		     1
 *    	    / \
 *   	  2   2
 *  	 / \ / \
 * 	    3  4 4  3
 *      但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * ​		    1
 *    	  / \
 *   	 2   2
 *    	  \   \
 *    	  3    3
 * 【示例】
 *      输入：root = [1,2,2,3,4,4,3]
 *      输出：true
 *      输入：root = [1,2,2,null,3,null,3]
 *      输出：false
 */
public class IsSymmetric {
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
}
