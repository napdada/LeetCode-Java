package exam.tencent;

import java.util.ArrayList;
import java.util.Scanner;

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
