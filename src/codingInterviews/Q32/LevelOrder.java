package codingInterviews.Q32;

import java.util.ArrayList;

/**
 * Q32 - I. 从上到下打印二叉树
 * 【题目】
 *      从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 【示例】
 *      例如:
 *      给定二叉树: [3,9,20,null,null,15,7],
 * 	        3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回：
 *      [3,9,20,15,7]
 */
public class LevelOrder {

    /**
     * BFS：用队列辅助逐行加入节点
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
}
