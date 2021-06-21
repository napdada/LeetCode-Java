package codingInterviews.Q51;

/**
 * Q51. 数组中的逆序对
 * 【题目】
 *      在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 【示例】
 *      输入: [7,5,6,4]
 *      输出: 5
 */
public class ReversePairs {
    int[] nums, tmp;

    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();
        int[] arr = {1, 3, 2, 3, 1};
        System.out.println(reversePairs.reversePairs(arr));
    }

    /**
     * 归并排序
     * 分：不断将数组从中点位置划分开，将整个数组的排序问题转换为子数组的排序问题；
     * 治：划分到子数组长度为 1 时，开始向上合并，不断将较短排序数组合并为较长排序数组，直至合并至愿数组完成排序；
     * @author 网友
     * @param nums 数组
     * @return 逆序对数
     * @time O(N * log N)
     * @space O(N)
     */
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }

    // 递归
    public int mergeSort(int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        int count =  mergeSort(l, mid) + mergeSort(mid + 1, r);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = nums[k];
        for (int k = l; k <= r; k++) {
            if (i == mid + 1)       // 左边结束
                nums[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])    // 右边结束 or 左边 < 右边
                nums[k] = tmp[i++];
            else {                  // 左边 > 右边
                nums[k] = tmp[j++];
                count += mid - i + 1;   // 计算该次递归的逆序对
            }
        }
        return count;
    }

    /**
     * 排序交换：比较相邻两数，前数大于后数则交换位置
     * @author NAP
     * @param nums 数组
     * @return 逆序对数
     * @time O(N ^ 2)
     * @space O(1)
     */
    public int reversePairs2(int[] nums) {
        int count = 0, len = nums.length;
        if (len == 1) return 0;
        for (int i = 0; i < len; i++) {
            if (i == len - 1) {
                len--;
                i = 0;
            }
            if (nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
                count++;
            }
        }
        return count;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    /**
     * 暴力求解：比较每一个数字和其后所有数字，并计数
     * @author NAP
     * @param nums 数组
     * @return 计数结果，即逆序对数
     * @time O(N ^ 2)，超时
     * @space O(1)
     */
    public int reversePairs3(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }
}
