package codingInterviews.Q11;

/**
 * Q11. 旋转数组的最小数字
 * 【题目】
 *      把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 *      例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * 【示例】
 *      输入：[3,4,5,1,2]
 *      输出：1
 *      输入：[2,2,2,0,1]
 *      输出：0
 */
public class MinArray {

    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        System.out.println(minArray(array));
        System.out.println(minArray2(array));
    }

    /**
     * 遍历直到第一个变小的数
     * @author PAN
     * @param numbers 两个非递减数列构成的数组
     * @return min
     * @time O(N)
     * @space O(1)
     */
    public static int minArray(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
                break;
            }
        }
        return min;
    }

    /**
     * 二分法：因为整个数组是由两个非递减数列构成的，所以可以用二分来缩小范围
     * @author 网友
     * @param numbers 两个非递减数列构成的数组
     * @return min
     * @time O(logN)
     * @space O(1)
     */
    public static int minArray2(int[] numbers){
        int i = 0, j = numbers.length - 1;
        int m = 0;
        while (i < j) {
            m = (i + j) / 2;
            if (numbers[m] > numbers[j]) i = m + 1;
            else if (numbers[m] == numbers[j]) j--;
            else {
                int min = numbers[i];
                for (int k = i + 1; k < m + 1; k++) {
                    if (numbers[k] < min){
                        min = numbers[k];
                        break;
                    }
                }
                return min;
            }
        }
        return numbers[i];
    }
}
