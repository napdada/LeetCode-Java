package exam.tencentmusic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 腾讯音乐全民 K 歌一面第二题：找树最下层最左边节点
 */
public class FindBottomLeftValue {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.offer(root.right);
            if (root.left != null) queue.offer(root.left);
        }
        return root.val;
    }
}
