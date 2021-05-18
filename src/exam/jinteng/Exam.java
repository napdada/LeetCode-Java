package exam.jinteng;

import java.util.Scanner;

/**
 * 金腾科技一面：判断一个数字是否是回文
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        // 第一种方法：将数字转换为字符串，两个指针指向头尾依次判断
        String s = String.valueOf(num);
        boolean flag = true;
        for (int i = 0; i < s.length() / 2; i++) {
            int j = s.length() - i - 1;
            if (s.charAt(i) != s.charAt(j)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);

        // 面试官有没有别的方法不用字符串
        // 第二种：利用求余整除与非
        int n = 0, tmp = num;
        while (tmp / 10 != 0) {
            tmp /= 10;
            n++;
        }
        while (n > 0) {
            int a = num / (int)Math.pow(10, n);
            num = num % (int)Math.pow(10, n);
            num = num ^ a;
            if (num % 10 == 0) num /= 10;
            else break;
            n--;
        }
        System.out.println(num);
    }
}
