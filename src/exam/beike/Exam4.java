package exam.beike;

import java.util.Scanner;

/**
 * 贝壳找房笔试第四题：小 Q 想要若干橘子总重为 s，可以对橘子筛选，计算现有橘子平均重并向下去整，选择抛弃所有大于平均或小于平均的橘子
 * 对于每次询问，判断是否能通过若干次筛选使得总重为 s，可以输出 YES、否则输出 NO
 */
public class Exam4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), q = input.nextInt(), sum = 0, avg = 0;
        int[] a = new int[n], s = new int[q];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
            sum += a[i];
        }
        avg = sum / n;
        boolean flag = false;
        for (int i = 0; i < q; i++) {
            s[i] = input.nextInt();
            if (s[i] > sum) flag = false;
            if (s[i] == sum) flag = true;
            int tmpSum = sum;
//            while (s[i] < tmpSum) {
//                if (avg > s[i]) {
//                    tmpSum = 0;
//                    for (int j = 0; j < n; j++) {
//                        if (a[j] <= avg)
//                            tmpSum += a[j];
//                    }
//                } else {
//
//                }
//            }

            if (flag) System.out.println("YES");
            else System.out.println("NO");
        }


    }
}
