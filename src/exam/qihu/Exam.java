package exam.qihu;

import java.util.Scanner;

/**
 * 360 笔试第一题：射击得分（AC）
 */
public class Exam {
    public static void main(String[] args) {
        // int n = 3, sum = 0;
        // int[] array = {1, 2, 3};
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long sum = 0;
        int[] array = new int[n];

        for (int i = 0; i < n; i ++) {
            array[i] = input.nextInt();
            sum += array[i]; // 中一枪
        }
        int tmp;
        for (int i = 0; i < n - 1; i ++) {

            for (int j = i + 1; j < n; j++) {   // 中两枪
                tmp = array[i] | array[j];
                sum += tmp;
            }
        }

        System.out.println(sum);
    }

}
