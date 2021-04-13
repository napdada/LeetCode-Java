package codingInterviews.Q42;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.jar.JarEntry;

/**
 * Q42. 连续子数组的最大和
 * 【题目】
 *      输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * 【示例】
 *      输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *      输出: 6
 *      解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArray().maxSubArray(nums));
    }

    /**
     * DP：转移方程 dp[i - 1] > 0 执行 dp[i] = dp[i - 1] + nums[i]、dp[i - 1] <= 0 执行 dp[i] = nums[i]
     * @author 网友
     * @param nums 数组
     * @return 连续子数组最大和
     * @time O(N)
     * @space O(1)
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }
}
