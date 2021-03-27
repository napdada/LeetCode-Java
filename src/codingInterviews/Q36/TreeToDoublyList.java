package codingInterviews.Q36;

/**
 * Q36. 二叉搜索树与双向链表
 * 【题目】
 *      输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 *      要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class TreeToDoublyList {

    /**
     * 红黑树旋转：运用红黑树旋转的思想来形成双向链表
     * 1. 因是二叉搜索树，所以对左子节点判断其是否有右子（有则左旋）、对右子节点判断是否有左子（有则右旋）；
     * 2. 每轮旋转可能导致子树结构变化，故需要对树从上至下进行层层旋转；
     * 3. 直到整个树左子只有左子、右子只有右子（即可视为类单链表结构）；
     * 4. 再从 root 开始构建双向的链表，即左子的 right 指向上一层节点、右子的 left 指向上一层节点；
     * 5. 最后将 head、end 首尾相连；
     * @author PAN
     * @param root 二叉搜索树根
     * @return 排序循环双向链表头节点
     * @time O(N)
     * @space O(1)
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        // 左子树的旋转、右子树的旋转
        Node head = leftDFS(root, root.left), end = rightDFS(root, root.right);
        // 无左子树或无右子树对 head、end的处理
        if (head == null) head = root;
        if (end == null) end = root;

        // 利用旋转好的类单链表结构构建双链表
        while (root.left != null) { // 左子的 right 指向上一层节点
            root.left.right = root;
            root = root.left;
        }
        while (root.right != null) {
            root.right.left = root; // 右子的 left 指向上一层节点
            root = root.right;
        }
        // head、end 首尾相连
        head.left = end;
        end.right = head;

        return head;
    }

    /**
     * 层层旋转
     * @param father 旋转处的父节点
     * @param root 待旋转的节点
     * @return 树最左下角的节点
     */
    public Node leftDFS(Node father, Node root) {
        if (root == null) return null;  // 空则不转

        // 层层找到第一个右子不为空的节点
        while (root.right == null && root.left != null) {
            father = root;
            root = root.left;
        }

        // 对这个节点开始往下逐层旋转
        while (root.right != null) {
            root = leftSpin(root);  // 左旋
            father.left = root;     // 旋转完毕后将父节点指向新的节点
            // 旋转后找到下一个需要旋转的点
            while (root.right == null && root.left != null) {
                father = root;
                root = root.left;
            }
        }
        return root;
    }
    public Node rightDFS(Node father, Node root) {
        if (root == null) return null;  // 空则不转

        // 层层找到第一个左子不为空的节点
        while (root.left == null && root.right != null) {
            father = root;
            root = root.right;
        }

        // 对这个节点开始往下逐层旋转
        while (root.left != null) {
            root = rightSpin(root); // 左旋
            father.right = root;    // 旋转完毕后将父节点指向新的节点
            // 旋转后找到下一个需要旋转的点
            while (root.left == null && root.right != null) {
                father = root;
                root = root.right;
            }
        }
        return root;
    }
    public Node leftSpin(Node root) {   // 左旋
        // 1. 将待旋转节点 root 的右子作为其父节点：形成新的 newRoot，newRoot.left = root；
        // 2. 再将 newRoot 旧左子改为 root 的右子；
        Node newRoot = root.right, tmp = newRoot.left;
        newRoot.left = root;
        root.right = tmp;
        return newRoot;
    }
    public Node rightSpin(Node root) {  // 右旋
        // 1. 将待旋转节点 root 的左子作为其父节点：形成新的 newRoot，newRoot.right = root；
        // 2. 再将 newRoot 旧右子改为 root 的左子；
        Node newRoot = root.left, tmp = newRoot.right;
        newRoot.right = root;
        root.left = tmp;
        return newRoot;
    }


    Node pre, head;

    /**
     * 中序遍历 + 递归
     * @author 网友
     * @param root 二叉搜索树根
     * @return 排序循环双向链表头节点
     * @time O(N)
     * @space O(N)
     */
    public Node treeToDoublyList2(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    // cur：当前中序节点，pre：当前节点在中序中的上一个节点
    void dfs(Node cur) {
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

}
