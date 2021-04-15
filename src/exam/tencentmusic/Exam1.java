package exam.tencentmusic;

import java.util.Scanner;

/**
 * 腾讯音乐笔试第一题：找一个数组中只出现一次的最小的数，没有输出 -1（AC）
 */
public class Exam1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int sum = input.nextInt();
        for (int i = 0; i < sum; i++) {
            int num = input.nextInt();
            int[] array = new int[num];
            int[] count = new int[1001];
            for (int j = 0; j < num; j++) {
                array[j] = input.nextInt();
                count[array[j]]++;
            }
            boolean flag = false;
            for (int j = 1; j < count.length; j++)
                if (count[j] == 1) {
                    flag = true;
                    System.out.println(j);
                    break;
                }
            if (!flag) System.out.println(-1);
        }
    }
}
