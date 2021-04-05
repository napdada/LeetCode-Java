package exam.tencent;

import java.util.Scanner;

/**
 * 腾讯笔试第二题：将数字串相邻两数之和为 10 的数字删除，删除后形成新串，最后输出无法再合并的长度
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = input.nextInt();
        String s = input.next();
        StringBuilder str = new StringBuilder(s);

        int num1, num2, rest = len;
        int i = 0, min = 0;
        while (i >= 0 && i < str.length() - 1) {
            int  j = i + 1;
            num1 = str.charAt(i) - '0';
            num2 = str.charAt(j) - '0';
            boolean flag = false;
            while (num1 + num2 == 10) {
                flag = true;
                i--;
                j++;
                if (i >= min && j < str.length()) {
                    num1 = str.charAt(i) - '0';
                    num2 = str.charAt(j) - '0';
                } else break;
            }
            if (flag) rest -= j - i - 1;
            i = j;
            if (flag) min = i;
        }
        System.out.println(rest);
    }
}
