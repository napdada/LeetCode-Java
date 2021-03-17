package codingInterviews.Q27;

import java.util.Stack;

/**
 * Q27. 二叉树的镜像
 * 【题目】
 * ​	    请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * ​	    例如输入：
 *
 *  			4
 *
 *   		 /   \
 *   		2     7
 * 		 / \   / \
 * 		1   3 6   9
 * 	    镜像输出：
 *
 *  			4
 *
 *   		 /   \
 *   		7     2
 * 		 / \   / \
 * 		9   6 3   1
 * 【示例】
 *      输入：root = [4,2,7,1,3,6,9]
 *      输出：[4,7,2,9,6,3,1]
 */
public class MirrorTree {
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
}
