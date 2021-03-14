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
        System.out.println(Arrays.toString(exchange2(nums)));

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

    /**
     * 双指针交换：一头一尾指针指向原数组，头指针为奇数时右移，尾指针为偶数时左移，找到第一个不满足条件的两个值交换，然后继续循环。
     * @author PAN
     * @param nums 待调整的数组
     * @return 按奇偶调整后的数组
     * @time O(N)
     * @space O(1)
     */
    public static int[] exchange2(int[] nums) {
        int i = 0, j = nums.length - 1;
        int tmp;
        while (i < j) {
            boolean flagI = (nums[i] % 2 == 0);
            boolean flagJ = (nums[j] % 2 == 1);
            if (flagI && flagJ) {
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            } else {
                if (!flagI) i++;
                if (!flagJ) j--;
            }
        }
        return nums;
    }

    /**
     * 双指针交换2
     * @author 网友
     * @param nums 待调整的数组
     * @return 按奇偶调整后的数组
     * @time O(N)
     * @space O(1)
     */
    public int[] exchange3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 != 0) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }
}
