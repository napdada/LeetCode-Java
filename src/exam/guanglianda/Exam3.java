package exam.guanglianda;

import java.util.Scanner;

/**
 * 广联达笔试第三题：n 罐蜂蜜，每罐输入若干份，小熊最多吃 k 份，求使得最大的相邻两罐之和的最小值（45%，数组创建过大）
 */
public class Exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = input.nextInt();

        int[] sum = new int[n - 1];
        for (int i = 0; i < n - 1; i++)
            sum[i] = a[i] + a[i + 1];

        Exam3 exam3 = new Exam3();
        for (int i = 0; i < k; i++) {
            int index = exam3.getMaxIndex(sum);
            if (Math.max(a[index], a[index + 1]) <= 0) {
                System.out.println(0);
                return;
            }
            if (a[index] > a[index + 1]) {
                a[index]--;
                sum[index]--;
                if (index - 1 >= 0) sum[index - 1]--;
            } else {
                a[index + 1]--;
                sum[index]--;
                if (index + 1 < sum.length) sum[index + 1]--;
            }
        }

        int max = sum[0];
        for (int value : sum) max = Math.max(max, value);
        System.out.println(max);
    }

    public int getMaxIndex(int[] a) {
        int max = a[0], index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }
}
