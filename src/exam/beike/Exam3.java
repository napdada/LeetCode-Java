package exam.beike;

import java.util.Scanner;

/**
 * 贝壳找房笔试第三题：一个序列大于 k 的元素个数超过不大于 k 的元素个数是完美序列，求一个序列 a 的连续子序列中完美序列的最长长度（93.33%）
 */
public class Exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt();    // n：序列长度，k：要判断元素
        int[] a = new int[n], b = new int[n];
        int max = 0, min = 0;
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
            if (a[i] > k) max++;
            else min++;
        }
        if (max > min) {
            System.out.println(n);
            return;
        }
        if (a[0] > k) b[0] = 1;
        else b[0] = -1;
        for (int i = 1; i < n; i++) {
            if (a[i] > k) b[i] = b[i - 1] + 1;
            else b[i] = b[i - 1] - 1;
        }

        int res, count = 0;
        if (max == 0) res = 0;
        else res = 1;
        for (int i = 0; i < n; i++) {
            if (b[i] > 0) count++;
            else {
                res = Math.max(res, count);
                count = 0;
            }
        }
        System.out.println(res);
    }
}
