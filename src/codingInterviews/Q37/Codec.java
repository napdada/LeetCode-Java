package codingInterviews.Q37;

import java.util.ArrayList;

/**
 * Q37. 序列化二叉树
 * 【题目】
 *      请实现两个函数，分别用来序列化和反序列化二叉树。
 * 【示例】
 *      你可以将以下二叉树：
 * 	        1
 *         / \
 *        2   3
 *           / \
 *          4   5
 *      序列化为 "[1,2,3,null,null,4,5]"
 */
public class Codec {

    public static void main(String[] args) {
        String s = "[1,2,null,3,null,4,null,5]";
        Codec codec = new Codec();
        TreeNode treeNode = codec.deserialize(s);
        String ss = codec.serialize(treeNode);
    }

    /**
     * 利用队列：将一颗树按照从上到下从左到右序列化为 String
     * @author PAN
     * @param root 树根
     * @return String
     * @time O(N)
     * @space O(N)
     */
    public String serialize(TreeNode root) {
        if (root == null) return "";

        ArrayList<TreeNode> nodeList = new ArrayList<TreeNode>();
        nodeList.add(root);
        StringBuilder s = new StringBuilder();
        while (!nodeList.isEmpty()) {
            TreeNode tmp = nodeList.remove(0);   // 取出对头，将其左右子加入队列
            if (tmp != null) {
                s.append(tmp.val).append(",");
                nodeList.add(tmp.left);
                nodeList.add(tmp.right);
            } else s.append("null,");   // 为空则加入 "null"，会导致叶子节点也将其左右子的 "null" 加入 String，需要后面剔除
        }

        // 将序列最后连续的 "null" 全部删除
        String[] split = s.toString().split(",");
        int i = split.length - 1;
        while (split[i].equals("null"))
            i--;

        // 重新构建符合要求的 String
        StringBuilder str = new StringBuilder("[");
        for (int j = 0; j < i; j++)
            str.append(split[j]).append(",");
        str.append(split[i]).append("]");

        return str.toString();
    }

    /**
     * 利用双队列：将序列化的 String 重新构建为树
     *  1. 父层队列头节点出队，不为空则将子层队列的头两个节点为其左右子；
     *  2. 一轮结束后旧子层变为新父层，再构建新子层；
     * @author PAN
     * @param data 序列化 String
     * @return root
     * @time O(N)
     * @space O(N)
     */
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;

        // 删除首位的括号
        StringBuilder s = new StringBuilder(data);
        s.delete(0 ,1);
        s.delete(s.length() - 1, s.length());

        // 切分后构建所有树节点并给 val 赋值
        String[] split = s.toString().split(",");
        TreeNode[] nodes = new TreeNode[split.length];
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("null")) {
                nodes[i] = new TreeNode(Integer.parseInt(split[i]));
            }
        }

        ArrayList<TreeNode> fatherList = new ArrayList<>(); // 父层所有节点
        ArrayList<TreeNode> sonList = new ArrayList<>();    // 子层所有节点
        fatherList.add(nodes[0]);
        int sonNum = fatherList.size() * 2, restNum = nodes.length - 1 , i = 1;
        while (sonNum < restNum) {
            int count = 0, notNullNum = 0;  // count：子的个数，notNullNum：子中非空的个数（用来合理计算再下一层子数）
            while (count < sonNum) {    // 构建子层队列
                if (nodes[i] != null) notNullNum++;
                sonList.add(nodes[i]);
                i++;    // node[] 数组的下标
                count++;
            }
            restNum -= sonList.size();  // 每构建一次子层将剩余节点个数更新

            // 构建父与左右子的联系
            int j = 0;
            while (!fatherList.isEmpty()) {
                TreeNode tmp = fatherList.remove(0);
                if (tmp != null) {
                    tmp.left = sonList.get(j);
                    tmp.right = sonList.get(j + 1);
                    j += 2;
                }
            }

            // 旧子层变为新父层
            ArrayList<TreeNode> tmp = fatherList;
            fatherList = sonList;
            sonList = tmp;
            sonNum = notNullNum * 2;
        }

        // 处理剩余的父子节点
        for ( ; i < nodes.length; i++)
            sonList.add(nodes[i]);
        while (!fatherList.isEmpty()) {
            TreeNode tmp = fatherList.remove(0);
            if (tmp != null) {
                tmp.left = sonList.isEmpty() ? null : sonList.remove(0);
                tmp.right = sonList.isEmpty() ? null : sonList.remove(0);
            }
        }

        return nodes[0];
    }
}
