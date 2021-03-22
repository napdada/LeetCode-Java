package codingInterviews.Q32;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Q32 - III. 从上到下打印二叉树 III
 * 【题目】
 *      请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 *      第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * 【示例】
 *      给定二叉树: [3,9,20,null,null,15,7],
 * 	        3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *      返回其层次遍历结果：
 *      [
 *          [3],
 *          [20,9],
 *          [15,7]
 *      ]
 */
public class LevelOrder3 {
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
}
