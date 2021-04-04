package exam.meituan;

import java.util.Scanner;

/**
 * 美团笔试第二题：切蛋糕（NAC，45%）
 */
public class Exam2 {
    public static void main(String[] args) {
//        int n = 2;
//        int[][] array = {{1, 0}, {0, 180}};

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            array[i][0] = input.nextInt();
            array[i][1] = input.nextInt();
        }

        int raw = 1, column = 1;
        for (int i = 0; i < n; i++) {
            if (array[i][0] == 0) {
                raw++;
            } else {
                boolean flag = false;
                for (int j = 0; j < i; j++) {
                    if (array[j][0] == 1 && (array[j][1] + array[i][1] == 180 || array[j][1] + array[i][1] == 540)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) column *= 2;
            }
        }
        System.out.println(raw * column);
    }
}
