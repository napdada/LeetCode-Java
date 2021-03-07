package codingInterviews.Q3;

import java.util.Arrays;

/**
 * Q3. 数组中重复的数字
 * 【题目】
 *      在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 *      数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 【示例】
 *      输入：
 *      [2, 3, 1, 0, 2, 5, 3]
 *      输出：2 或 3
 */
public class FindReapeatNumber {

    public static void main(String[] args) {
        int[] num = {2, 3, 1, 0, 2, 5, 3};

        int repeatNumber = findReapeatNumber(num);
        System.out.println("repeatNumber = " + repeatNumber);

        int repeatNumber2 = findReapeatNumber2(num);
        System.out.println("repeatNumber2 = " + repeatNumber2);
    }

    /**
     * 原地交换
     * 遍历中，第一次遇到数字 xx 时，将其交换至索引 xx 处；
     * 而当第二次遇到数字 xx 时，一定有 nums[x] = x ，此时即可得到一组重复数字。
     * @author 网友
     */
    public static int findReapeatNumber0(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

    /**
     * 遍历 nums[] ，利用 countArray[] 记录数出现的次数
     * @author PAN
     * @param nums int[]
     * @return repeatNumber：第一个重复出现的数字
     */
    public static int findReapeatNumber(int[] nums) {
        int[] countArray = new int[nums.length];
        int repeatNumber = -1;
        for(int num : nums) {
            countArray[num]++;
            if(countArray[num] > 1) {
                repeatNumber = num;
                break;
            }
        }
        return repeatNumber;
    }

    /**
     * 排序 nums[]，再遍历找到第一个重复数字
     * @author PAN
     * @param nums int[]
     * @return repeatNumber：第一个重复出现的数字
     */
    public static int findReapeatNumber2(int[] nums) {
        int repeatNumber = -1;
        Arrays.sort(nums);
        int record = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == record) {
                repeatNumber = record;
                break;
            } else {
                record = nums[i];
            }
        }
        return repeatNumber;
    }


}
