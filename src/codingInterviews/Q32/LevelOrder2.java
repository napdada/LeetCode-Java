package codingInterviews.Q32;

import java.util.ArrayList;
import java.util.List;

/**
 * Q32 - II. 从上到下打印二叉树II
 * 【题目】
 *      从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 【示例】
 *      例如:
 *      给定二叉树: [3,9,20,null,null,15,7],
 * 	        3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回其层次遍历结果：
 *      [
 *          [3],
 *          [9,20],
 *          [15,7]
 *      ]
 */
public class LevelOrder2 {
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
}
