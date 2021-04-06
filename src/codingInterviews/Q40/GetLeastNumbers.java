package codingInterviews.Q40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Q40. 最小的 k 个数
 * 【题目】
 *  	输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 【示例】
 *      输入：arr = [3,2,1], k = 2
 *      输出：[1,2] 或者 [2,1]
 *      输入：arr = [0,1,2,1], k = 1
 *      输出：[0]
 */
public class GetLeastNumbers {
    public static void main(String[] args) {
        int[] arr = {0,0,2,0,1,1,1,7,3,3,1,3,2,3,7,12,0,10,16,8,3,13,20,4,9,14,16,18,12,1,21,17,3,22,18,15,16,1,3,35,25,33,7,30,41,3,18,10,34,0,33,51,18,26,16,9,0,2,19,53,53,59,53,34,5,3,51,56,12,37,32,3,63,65,15,70,54,4,68,8,66,5,80,0,36,49,50,25,19,42,49,41,30,44,59,74,55,7,33,44};
        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers(arr, 91)));
        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers2(arr, 91)));
        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers3(arr, 91)));
    }

    /**
     * 默认前 k 个是最小的，再从 k + 1 开始比较，发现更小的挤掉原来最大的
     * @author PAN
     * @param arr 数组
     * @param k k 个
     * @return 最小 k 个数
     * @time O(N * k)
     * @space O(K)
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];
        int[] minArr = Arrays.copyOfRange(arr, 0, k);   // 将前 k 个拷贝为新数组并排序
        Arrays.sort(minArr);
        ArrayList<Integer> list = new ArrayList<>();    // 利用 List 来实现比较并挤出最大值
        for (int value : minArr) list.add(value);

        for (int i = k; i < arr.length; i++) {  // 从第 k + 1 开始与 List 中最大值比较
            if (arr[i] < list.get(k - 1)) {
                list.remove(k - 1); // 找到比最大值小的挤出最大值
                int len = list.size();
                for (int j = k - 2; j >= 0; j--) {  // 插入新值
                    if (list.get(j) <= arr[i]) {
                        list.add(j + 1, arr[i]);
                        break;
                    }
                }
                if (list.size() == len) list.add(0, arr[i]);
            }
        }

        for (int i = 0; i < list.size(); i++)
            minArr[i] = list.get(i);
        return minArr;
    }

    /**
     * 哈希表：先统计每个数字出现的次数，在从小到大生成长度为 k 的数组
     * @author PAN
     * @param arr 数组
     * @param k k 个
     * @return 最小 k 个数
     * @time O(N * logN)
     * @space O(N)
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key : arr) {
            if (map.containsKey(key)) map.put(key, map.get(key) + 1);
            else map.put(key, 1);
        }
        int[] minArr = new int[k];
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        int i = 0, sum = 0;
        for (Integer key : keys) {
            if (sum + map.get(key) <= k) {
                sum += map.get(key);
                for ( ; i < sum; i++)
                    minArr[i] = key;
            } else {
                for ( ; i < k; i++)
                    minArr[i] = key;
                break;
            }
        }
        return minArr;
    }

    /**
     * 调官方 sort 直接返回前 k 个
     * @author PAN
     * @param arr 数组
     * @param k k 个
     * @return 最小 k 个数
     * @time O(N * logN)
     * @space O(1)
     */
    public int[] getLeastNumbers3(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    /**
     * 快速排序：利用快排的哨兵划分成最小的 k 个数和其他数
     * @author 网友
     * @param arr 数组
     * @param k k 个
     * @return 最小 k 个数
     * @time O(N)
     * @space O(LogN)
     */
    public int[] getLeastNumbers4(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int k, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;  // 找到比基准小的
            while (i < j && arr[i] <= arr[l]) i++;  // 找到比基准大的
            swap(arr, i, j);    // 使得小的在基准左边、大的在基准右边
        }
        swap(arr, i, l);    // 将基准放到中间
        // 一轮过后，基准前的数恰好为 k 直接返回、大于 k 继续递归左边、小于 k 继续递归右边
        if (i > k) return quickSort(arr, k, l, i - 1);
        if (i < k) return quickSort(arr, k, i + 1, r);
        return Arrays.copyOf(arr, k);
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
