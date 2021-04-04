package exam.meituan;

import java.util.Scanner;

/**
 * 美团笔试第五题： 俩人手上由若干糖果，每个糖果甜度可正可负，拿走第 i 个必须拿走前 i - 1 个，求最大甜度（AC）
 */
public class Exam5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int A = input.nextInt(), B = input.nextInt();
        int[] a = new int[A], b = new int[B];
        for (int i = 0; i < A; i++) {
            a[i] = input.nextInt();
        }
        for (int i = 0; i < B; i++) {
            b[i] = input.nextInt();
        }

        int maxA = 0;
        int tmp = 0;
        for (int i = 0; i < A; i++) {
            tmp +=  a[i];
            maxA = Math.max(tmp, maxA);
        }

        int maxB = 0;
        tmp = 0;
        for (int i = 0; i < B; i++) {
            tmp += b[i];
            maxB = Math.max(tmp, maxB);
        }
        System.out.println(maxA + maxB);
    }
}
