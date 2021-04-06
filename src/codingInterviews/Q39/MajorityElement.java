package codingInterviews.Q39;

/**
 * Q39. 数组中出现次数超过一半的数字
 * 【题目】
 * ​	    数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * ​	    你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 【示例】
 *      输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 *      输出: 2
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(new MajorityElement().majorityElement(array));
    }

    /**
     * 摩尔投票：票数正负抵消，该数要比剩余所有数还要多
     * @author PAN
     * @param nums 数组
     * @return 出现超多一半的数字
     * @time O(N)
     * @space O(1)
     */
    public int majorityElement(int[] nums) {
        int num = nums[0], count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) count++;
            else {
                count--;
                if (count <= 0) {
                    num = nums[i];
                    count = 1;
                }
            }
        }
        return num;
    }
}
