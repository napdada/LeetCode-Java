package exam.aike;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 爱客科技笔试编程第一题
 * 【题目】
 *      输入一个整数 n 为数组的长度，再输入数组内容。输出每一个元素 i 的左边和右边第一个比它小的数的下标（没有则 -1）
 * 【示例】
 *      输入：7 [3, 4, 1, 5, 6, 2, 7]
 *      输出：   -1 2
 *              0 2
 *              -1 -1
 *              2 5
 *              3 5
 *              2 -1
 *              5 -1
 */
public class Exam1 {
    public static void main(String[] args) {
//        int[] arr = {3, 4, 1, 5, 6, 2, 7};
//        findMin(arr);
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int len = in.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = in.nextInt();
            }
            findMin(arr);
        }
    }

    /**
     * 遍历找、利用 HashMap 辅助
     * @author PAN
     * @param arr 输入数组
     * @time 一开始完全靠遍历，后来加了 Map ，过了 75% 的case（感觉问题出在：右边也从左开始了），但还是超时；
     *      发现左边从左边开始找，右边应该从右开始找，改成如下代码，但考试时间到了
     * @space O(N)
     */
    public static void findMin(int[] arr) {
        int len = arr.length;
        int[] leftMin = new int[len], rightMin = new int[len];

        // 用哈希表记录之前的结果，当遇到之前已经找过的数时可以减少循环。key：某数下标 i，value：左边/右边第一个比它小的数的下标
        HashMap<Integer, Integer> leftMap = new HashMap<>();
        HashMap<Integer, Integer> rightMap = new HashMap<>();

        // 找左边第一个小于该数的数，左边的从数组左端开始
        for (int i = 0; i < len; i++) {
            if (leftMap.get(arr[i]) != null) leftMin[i] = leftMap.get(arr[i]); // 如果该数在之前已经找过，从 Map 中找到 value
            else leftMin[i] = -1;

            int l = leftMin[i] == -1 ? 0 : leftMin[i];  // 利用 Map 减少遍历范围（l，i - 1），只对两个相同的数之间的数进行判断，避免重复比较
            for(int left = i - 1; left >= l ; left--) {
                if (arr[left] < arr[i]) {
                    leftMin[i] = left;
                    leftMap.replace(arr[i], leftMin[i]);    // 更新 Map
                    break;
                }
            }
        }

        // 找右边第一个小于该数的数，右边的从数组右边开始
        for (int i = len - 1; i >= 0; i--) {
            if (rightMap.get(arr[i]) != null) rightMin[i] = rightMap.get(arr[i]);
            else rightMin[i] = -1;

            int r = rightMin[i] == -1 ? len : rightMin[i];  // 利用 Map 减少遍历范围（i + 1，r），只对两个相同的数之间的数进行判断，避免重复比较
            for (int right = i + 1; right < r; right++) {
                if (arr[right] < arr[i]) {
                    rightMin[i] = right;
                    rightMap.replace(arr[i], rightMin[i]);  // 更新 Map
                    break;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println(leftMin[i] + " " + rightMin[i]);
        }
    }
}
