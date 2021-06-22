package codingInterviews.Q53;

/**
 * Q53 - I. 在排序数组中查找数字：二分法、暴力求解
 * 【题目】
 *      统计一个数字在排序数组中出现的次数。
 * 【示例】
 *      输入: nums = [5,7,7,8,8,10], target = 8
 *      输出: 2
 *      输入: nums = [5,7,7,8,8,10], target = 6
 *      输出: 0
 */
public class SearchNum {

    public static void main(String[] args) {
        int[] nums = {1};
        int target = 1;
        System.out.println(new SearchNum().search(nums, target));
    }

    /**
     * 二分法：先找到 target index，再从其左右分别开始计数
     * @author NAP
     * @param nums 数组
     * @param target 目标数字
     * @return 目标数字个数
     * @time O(log N)
     * @space O(1)
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0, index = -1;
        int l = 0, r = nums.length - 1, m;
        while (l <= r) {
            m = (l + r) / 2;
            if (nums[m] < target) l = m + 1;
            else if (nums[m] > target) r = m - 1;
            else {
                index = m;
                break;
            }
        }
        if (index == -1) return 0;
        int i = index;
        while (i < nums.length && nums[i] == target) {
            count++;
            i++;
        }
        i = index - 1;
        while (i >= 0 && nums[i] == target) {
            count++;
            i--;
        }
        return count;
    }
}
