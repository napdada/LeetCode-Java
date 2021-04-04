package exam.meituan;

import java.util.Scanner;

/**
 * 美团笔试第四题：给定 n * n 方阵，每行由 1 - k 中的数字填充，求从 1 跳到 k 的最小花费（代价是曼哈顿距离 |x1 - x2| + |y1 - y2|）（NAC，9%）
 */
public class Exam4 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt();
        int[][] array = new int[n][n];
        int[] count = new int[k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = input.nextInt();
                count[array[i][j]]++;
            }
        }
        for (int i = 0; i < k; i++) {
            if (count[i] == 0) {
                System.out.println(-1);
                break;
            }
        }
    }
}
