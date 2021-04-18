package exam.tencent;

import java.util.Scanner;

/**
 * 腾讯第三场笔试第四题：判断两个字符串是否相等，奇数长度逐位判断、偶数长度递归判断（AC）
 */
public class Exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String s1, s2;
        for (int i = 0; i < n; i++) {
            s1 = input.next(); s2 = input.next();
            if (s1.length() % 2 == 0) {
                System.out.println(Exam3.recur(s1, s2) ? "YES" : "NO");
            } else {
                boolean flag = false;
                for (int j = 0; j < s1.length(); j++) {
                    if (s1.charAt(j) != s2.charAt(j)) {
                        System.out.println("NO");
                        flag = true;
                        break;
                    }
                }
                if (!flag) System.out.println("YES");
            }
        }
    }
    public static boolean recur(String s1, String s2) {
        if (s1.length() % 2 == 1) {
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
        String sub1 = s1.substring(0, s1.length() / 2), sub2 = s1.substring(s1.length() / 2);
        String sub3 = s2.substring(0, s2.length() / 2), sub4 = s2.substring(s1.length() / 2);
        return (recur(sub1, sub3) && recur(sub2, sub4)) || (recur(sub1, sub4) && recur(sub2, sub3));
    }
}
