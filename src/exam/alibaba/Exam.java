package exam.alibaba;

import java.util.Scanner;

/**
 * 阿里笔试第一题：投票问题（AC）
 */
public class Exam {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();    // 同学数量
        int[] vote = new int[n];    // 每个人投给小明的数量
        int min = 0;
        long sumMing = 0, sumMy = 0;
        for (int i = 0; i < n; i++) {
            vote[i] = input.nextInt();
            sumMing += vote[i];         // 小明获得的总票数
            min = Math.max(vote[i], min);   // k 的最小值
        }
        while (sumMy <= sumMing) {
            sumMy = 0;
            for (int i = 0; i < n; i++) {
                sumMy += min - vote[i];
            }
            min++;
        }
        System.out.println(min - 1);
    }
}
