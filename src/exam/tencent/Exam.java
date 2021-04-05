package exam.tencent;

import java.util.ArrayList;

/**
 * 腾讯笔试第一题：将树修剪成完全二叉树
 */
public class Exam {
    public static void main(String[] args) {
        // String s = "[1,2,3,4,5,6,7,8]";
        // String s = "[302,196,100,null,162,null,null,178,null]";
        String s = "[1,3,null,5]";
        Exam exam = new Exam();
        TreeNode tree = exam.deserialize(s);
        TreeNode newTree = exam.solve(tree);
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
        while (sonNum < restNum) {          // son 个数小于 nodes[] 里剩余没访问节点的个数时循环
            int count = 0, notNullNum = 0;  // count：子的个数，notNullNum：子中非空的个数（用来合理计算再下一层子数）
            while (count < sonNum) {        // 构建子层队列
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

    public TreeNode solve (TreeNode root) {
        if (root == null) return null;
        ArrayList<TreeNode> fatherList = new ArrayList<>(), sonList = new ArrayList<>();
        fatherList.add(root);
        TreeNode tmp;
        while (!fatherList.isEmpty()) {
            boolean flag = false;
            int i = 0;
            do {
                tmp = fatherList.get(i);
                i++;
                if (!flag && tmp.left != null && tmp.right != null) {
                    sonList.add(tmp.left);
                    sonList.add(tmp.right);
                } else {
                    flag = true;
                    tmp.left = null;
                    tmp.right = null;
                }
            } while (i < fatherList.size());
            if (flag) {
                while (!fatherList.isEmpty()) {
                    TreeNode temp = fatherList.remove(0);
                    temp.left = null;
                    temp.right = null;
                }
            }
            else {
                fatherList = sonList;
            }
            sonList = new ArrayList<>();
        }
        return root;
    }

}
