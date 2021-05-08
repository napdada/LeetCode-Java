package exam.beike;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 贝壳找房笔试第二题：n 个有半径 a 的大饼，a[i] < a[j] 的大饼可以叠加，最少叠几堆大饼（AC）
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();    // 大饼数
        int[] arr = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
            if (map.containsKey(arr[i])) map.put(arr[i], map.get(arr[i]) + 1);
            else map.put(arr[i], 1);
        }
        int max = 0;
        for (Object value : map.values().toArray())
            max = Math.max(Integer.parseInt(value.toString()), max);
        System.out.println(max);
    }
}
