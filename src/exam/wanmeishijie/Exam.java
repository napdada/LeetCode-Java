package exam.wanmeishijie;

import java.io.*;
import java.util.Arrays;

/**
 * 完美世界笔试第一题：每人有各自体重，一艘船最多 2 人且有重量上限，最少需要多少条船（AC）
 */
public class Exam {

    public static int countLeastBoat(int[] weights, int maxLoad) {
        Arrays.sort(weights);
        int i = 0, j = weights.length - 1, count = 0;
        while (i < j) {
            if (weights[i] + weights[j] <= maxLoad) i++;
            j--;
            count++;
        }
        if (i == j) count++;
        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s =br.readLine();

        String[]str1 = s.split(" ");

        int[]weights = new int[str1.length];

        for(int i =0;i<str1.length;i++){

            weights[i] = Integer.parseInt(str1[i]);

        }

        int maxLoad =Integer.parseInt(br.readLine());

        int count =countLeastBoat(weights,maxLoad);

        System.out.println(count);

    }

}
