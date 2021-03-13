package codingInterviews.Q21;

import java.util.Arrays;

/**
 * Q21. 调整数组顺序使奇数位于偶数前面
 * 【题目】
 *      输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 【示例】
 *      输入：nums = [1,2,3,4]
 *      输出：[1,3,2,4]
 *      注：[3,1,2,4] 也是正确的答案之一。
 */
public class Exchange {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange(nums)));
    }

    /**
     * 遍历找奇偶并用数组存储：遍历数组，用两个指针指向新数组头尾，遇到奇数往头部放，遇到偶数往尾部放。
     * @author PAN
     * @param nums 待调整的数组
     * @return 按奇偶调整后的数组
     * @time O(N)
     * @space O(N)
     */
    public static int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;
        int[] exchangeNums = new int[nums.length];
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] % 2 == 0) {
                exchangeNums[j] = nums[k];
                j--;
            }
            else {
                exchangeNums[i] = nums[k];
                i++;
            }
        }
        return exchangeNums;
    }

}
