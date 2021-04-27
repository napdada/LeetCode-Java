package exam.wanmeishijie;

import java.util.Scanner;

/**
 * 完美世界笔试第三题：每个盒子有长宽，长宽都小于另一个盒子才能嵌套，不允许旋转最多嵌套多少层（AC）
 */
public class Exam3 {

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int maxL = envelopes[i][0], maxW = envelopes[i][1];
            int minL = envelopes[i][0], minW = envelopes[i][1];
            int count = 1;
            for (int j = 0; j < envelopes.length; j++) {
                if (j == i) continue;
                if (envelopes[j][0] > maxL && envelopes[j][1] > maxW) {
                    count++;
                    maxL = envelopes[j][0];
                    maxW = envelopes[j][1];
                } else if (envelopes[j][0] < minL && envelopes[j][1] < minW) {
                    count++;
                    minL = envelopes[j][0];
                    minW = envelopes[j][1];
                }
            }
            max = Math.max(max, count);
        }


        return max;
    }

    public static void main(String[] args) {
        Scanner reader=new Scanner(System.in);
        int x=reader.nextInt();
        int[][]array=new int[x][2];
        for(int i=0;i<x;i++){
            for(int j=0;j<2;j++){
                array[i][j]=reader.nextInt();
            }
        }
        System.out.println(maxEnvelopes(array));
    }
}
