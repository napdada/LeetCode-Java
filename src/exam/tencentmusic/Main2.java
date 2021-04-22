package exam.tencentmusic;

/**
 * 腾讯音乐移动客户端（IOS）一面第二题：利用给定递增数组构造平衡查找二叉树
 */
public class Main2 {
    public static void main(String[] args) {
        int[] a = {-10, -3, 0, 5, 9};
        System.out.println(new Main2().sortedArrayToBST(a));
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        int mid = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recur(nums, 0, mid - 1);
        root.right = recur(nums, mid + 1, nums.length - 1);
        return root;
    }
    public TreeNode recur(int[] nums, int l, int r) {
        if (r < l) return null;
        int index = (l + r) / 2;
        TreeNode node = new TreeNode(nums[index]);
        node.left = recur(nums, l, index - 1);
        node.right = recur(nums, index + 1,  r);

        return node;
    }
}
