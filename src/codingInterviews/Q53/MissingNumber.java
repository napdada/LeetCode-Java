package codingInterviews.Q53;

/**
 * Q53 - II. 0 ～ n - 1 中缺失的数字
 * 【题目】
 *      一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 *      在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 【示例】
 *      输入: [0,1,3]
 *      输出: 2
 *      输入: [0,1,2,3,4,5,6,7,9]
 *      输出: 8
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4};
        System.out.println(new MissingNumber().missingNumber(nums));
    }

    /**
     * 二分法：二分判断 num[mid] 是否等于 mid，从而判断缺失的数字在左或右
     * @author NAP
     * @param nums 数组
     * @return 缺失的数字
     * @time O(log N)
     * @space O(1)
     */
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1, mid = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (nums[mid] > mid) r = mid - 1;
            else l = mid + 1;
        }
        return nums[mid] == mid ? mid + 1 : mid;    // 当 mid 指向最后一个数时，需要判断是该数还是该数加一
    }
}
