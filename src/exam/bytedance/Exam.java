package exam.bytedance;

import java.util.*;

/**
 * 字节笔试第一题：解密字符串（0%，最后输出有问题输出了地址，简直无语，理论应该 AC）
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        Character[] c1 = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        Character[] c2 = {'0','1','2','3','4','5','6','7','8','9'};
        Character[] s2 = new Character[s.length()];
        int num;
        boolean flag = false;
        if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z')
            num = s.charAt(0) - 'A' + 1;
        else if (s.charAt(0) >= '0' && s.charAt(0) <= '9')
            num = s.charAt(0) - '0';
        else {
            num = 1;
            flag = true;
        }
        int i = 0;
        if (flag) {
            s2[0] = s.charAt(0);
            i = 1;
        }
        for ( ; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                int tmp = s.charAt(i) - 'A' + num;
                tmp %= 26;
                s2[i] = c1[tmp];
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int tmp = s.charAt(i) - '0' + num;
                tmp %= 10;
                s2[i] = c2[tmp];
            } else s2[i] = s.charAt(i);
        }
        for (Character character : s2) System.out.print(character); // 复盘修改后的输出
        System.out.println();
    }
}