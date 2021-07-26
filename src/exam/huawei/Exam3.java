package exam.huawei;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 华为特战队秋招笔试第二题（AC）
 * N 台仪器，K 台设备，输入 K 台设备占用仪器的时间 T 和优先级 P，输出所有设备运行结束最短耗时。
 * 优先级越高的设备（P 越小）越先使用仪器，同优先级占用时间长的先使用。
 */
public class Exam3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N = input.nextInt(), K = input.nextInt(), max = 0;
        int[] machine = new int[N];
        ArrayList<Integer> t = new ArrayList<>(), p = new ArrayList<>();

        t.add(input.nextInt());
        p.add(input.nextInt());
        for (int i = 1; i < K; i++) {
            int tInput = input.nextInt(), pInput = input.nextInt();
            max = Math.max(max, tInput);    // 最长时间的设备，为了 N >= K 的情况
            // 插入排序，将设备排成先后使用的顺序
            int index = i - 1;
            while (index >= 0 && pInput < p.get(index)) index--;
            while (index >= 0 && pInput == p.get(index) && tInput > t.get(index)) index--;
            index++;
            t.add(index, tInput);
            p.add(index, pInput);
        }

        if (N < K) {
            for (int i = 0; i < N; i++)
                machine[i] = t.get(i);      // N 台仪器的 N 条时间线
            for (int i = N; i < K; i++) {
                // 每次挑出时间线最短的仪器给下一个设备使用，最长时间线的仪器即为结果
                int min = machine[0], minIndex = 0;
                for (int j = 0; j < N; j++) {
                    if (machine[j] < min) {
                        min = machine[j];
                        minIndex = j;
                    }
                }
                machine[minIndex] += t.get(i);
            }
            for (int i = 0; i < N; i++)
                max = Math.max(max, machine[i]);
        }
        System.out.println(max);
    }
}
