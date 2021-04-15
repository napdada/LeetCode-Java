package codingInterviews.Q45;

import java.util.Arrays;

/**
 * Q45. 把数组排成最小的数
 * 【题目】
 *      输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 【示例】
 *      输入: [10,2]
 *      输出: "102"
 *      输入: [3,30,34,5,9]
 *      输出: "3033459"
 */
public class MinNumber {
    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(new MinNumber().minNumber(nums));
    }

    /**
     * 自定义排序：
     * 若拼接字符串 x + y > y + x，则 x “大于” y ；反之，若 x + y < y + x，则 x “小于” y ；
     * @author 网友
     * @param nums 待排序数组
     * @return 最小数
     * @time O(N * log N)
     * @space O(N)
     */
    public String minNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            str[i] = String.valueOf(nums[i]);
        quickSort(str,0, str.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str)
            stringBuilder.append(s);
        return stringBuilder.toString();
    }
    public void quickSort(String[] str, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        String tmp = str[i];
        while (i < j) {
            while ((str[j] + str[l]).compareTo(str[l] + str[j]) >= 0 && i < j) j--;
            while ((str[l] + str[i]).compareTo(str[i] + str[l]) >= 0 && i < j) i++;
            tmp = str[i];
            str[i] = str[j];
            str[j] = tmp;
        }
        str[i] = str[l];
        str[l] = tmp;
        quickSort(str, l, i - 1);
        quickSort(str, i + 1, r);
    }

    /**
     * lambda 内置函数写法
     */
    public String minNumber2(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }

}
