package exam.wanmeishijie;

import java.util.Scanner;

/**
 * 完美世界笔试第二题：给一个有向图，求 V1 点到其他点的最短距离，无法到达输出 9999（AC）
 */
public class Exam2 {
    public static void method (int[][] weightArray, String[] strArray) {
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (weightArray[i][k] >= 0 && weightArray[k][j] >= 0 &&
                            (weightArray[i][k] + weightArray[k][j] < weightArray[i][j] ||
                                    weightArray[i][j] == -1)) {
                        weightArray[i][j] = weightArray[i][k] + weightArray[k][j];
                    }
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            if (weightArray[0][i] < 0) System.out.println(9999);
            else System.out.println(weightArray[0][i]);
        }
    }
    public static void main(String[] args) {
        int[][] weight = new int[6][];
        String[] pointsStr = { "V1", "V2", "V3", "V4", "V5"};
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < pointsStr.length; i++) {
            String[]valuesStr = input.nextLine().split(" ");
            int[] values = new int[valuesStr.length];
            for (int j = 0; j < valuesStr.length; j++) {
                values[j] = Integer.parseInt(valuesStr[j]);
            }
            weight[i] = values;
        }
        input.close();
        method(weight, pointsStr);
    }

}
