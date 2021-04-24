package exam.jingdong;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 京东笔试第一题：找出投票最多的（AC）
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();
        int[] a = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
            if (map.containsKey(a[i])) map.put(a[i], map.get(a[i]) + 1);
            else map.put(a[i], 1);
        }

        int max = 0, maxIndex = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (entry.getValue() > max) {
                max = entry.getValue();
                maxIndex = entry.getKey();
            }
        System.out.println(maxIndex + " " + max);
    }
}
