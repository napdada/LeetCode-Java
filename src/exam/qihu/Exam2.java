package exam.qihu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 360 笔试第二题：0 - 1 背包问题（没调完）
 */
public class Exam2 {
    public static int flyNum = 0;
    public static int flyMax = 0;
    public static int dis = 0;
    public static int disMax = 0;
    public static int[] money;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int M = input.nextInt();
        int K = input.nextInt();
        int[] distance = new int[N];
        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            distance[i] = input.nextInt();
            money[i] = input.nextInt();
        }
       Exam2.money = money;

        int i = 1;
        while (distance[i] <= 10) {
            Exam2.DFS(Arrays.copyOfRange(distance, i + 1, N), i, distance[i]);
            disMax = Math.max(dis, disMax);
            i++;
        }
        System.out.println(disMax);
    }
    public static void DFS(int[] distance, int tmpI, int tmpDis) {
        if (flyNum > flyMax) return;
        flyNum++;
        dis += money[tmpI];
        int i;
        for (i = 0; i < distance.length; i++) {
            distance[i] -= tmpDis;
        }
        i = 0;
        while (distance[i] <= 10) {
            DFS(Arrays.copyOfRange(distance, i + 1, distance.length), i, distance[i]);
            i++;
        }
    }
}
