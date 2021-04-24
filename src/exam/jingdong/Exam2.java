package exam.jingdong;

import java.util.Scanner;

/**
 * 京东笔试第二题：抽卡序列，首卡片为 0，使得末尾卡片为 n，相邻俩卡片差不超过 2（18%，这道题 DP 的思路对了，但 DP 方程应该是 DP[i] = DP[i - 1] + DP[i - 3]）
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long num1 = 1, num2 = 1, res = 1;
        for (int i = 2; i < n; i++) {
            res = num1 + num2;
            while (res >= 998244353) {
                res %= 998244353;
            }
            num1 = num2 % 998244353;
            num2 = res % 998244353;
        }
        System.out.println(res);
    }
}
