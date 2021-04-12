package codingInterviews.Q34;

import java.util.ArrayList;
import java.util.List;

/**
 * Q34. 二叉树中和为某一值的路径
 * 【题目】
 *      输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * 【示例】
 *      给定如下二叉树，以及目标和 target = 22，
 *           5
 *          / \
 *         4   8
 *        /   / \
 *       11  13  4
 *      /  \    / \
 *     7    2  5   1
 *
 *      返回:
 *      [
 *          [5,4,11,2],
 *          [5,8,4,5]
 *      ]
 */
public class PathSum {
    List<List<Integer>> pathsList = new ArrayList<>();
    ArrayList<Integer> pathList = new ArrayList<>();
    int target;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(-2);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(-2);
        TreeNode node7 = new TreeNode(-1);
        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.left = node6; node3.right = null;
        node4.left = node7; node4.right = null;
        node5.left = null; node5.right = null;
        node6.left = null; node6.right = null;
        node7.left = null; node7.right = null;

        System.out.println(new PathSum().pathSum(node1, -1));
    }

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
