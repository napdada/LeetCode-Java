package exam.qushi;

import java.util.Scanner;

/**
 * 趋势科技笔试第一题：给定一个用字符串表示的 m 位非负整数 N，去掉其中 x 位数后，使得剩下的数字 Y 最小（53%）
 */
public class Exam {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        StringBuilder stringBuilder = new StringBuilder(str);
        int x = input.nextInt(), count = 0;
        if (x == str.length()) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < stringBuilder.length() - 1; ) {
            if (i + 1 < stringBuilder.length() && stringBuilder.charAt(i) > stringBuilder.charAt(i + 1)) {
                stringBuilder.deleteCharAt(i);
                if (i - 1 >= 0 && stringBuilder.charAt(i) < stringBuilder.charAt(i - 1)) i--;
                count++;
            } else i++;
            if (count == x) break;
        }
        while (count < x) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            count++;
        }
        System.out.println(Integer.valueOf(stringBuilder.toString()));
    }

}
