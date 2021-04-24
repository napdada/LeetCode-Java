package exam.wangyileihuo;

import java.util.Scanner;

/**
 * 网易雷火测开笔试编程：判断由一个字符串变成另一个字符串最少需要几步，可用三个操作：增一个字母、减一个字母、修改一个字母。(25%)
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.next(), s2 = input.next();
        int num = Math.abs(s1.length() - s2.length());
        int[] a = new int[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            boolean flag = false;
            for (int j = 0; j < s1.length(); j++) {
                if (s2.charAt(i) == s1.charAt(j)) {
                    a[i] = j;
                    flag = true;
                }
            }
            if (!flag) a[i] = -1;
        }

        boolean tag = false;
        for (int value : a) {
            tag = value == -1;
        }
        if (tag) {
            System.out.println(s1.length());
            return;
        }
        int maxLen = 0, maxLenIndex = 0, len = 1;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] >= 0 && a[i] < a[i + 1]) len++;
            else {
                if (len > maxLen) {
                    maxLen = len;
                    maxLenIndex = i;
                }
                len = 1;
            }
        }
        maxLen = Math.max(maxLen, len);
        System.out.println(num + s2.length() - maxLen);
    }
}
