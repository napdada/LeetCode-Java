package exam.huawei;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 华为特战队秋招笔试第一题（AC）
 * N 个围成环的站点，K 个乘客，出租车在圆形线路上运行（可顺可逆）。
 * 输入 N、K 及 K 行乘客乘车记录（起始时间、上车站点、下车站点），输出最大同时运营车数。
 */
public class Exam2 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int N = input.nextInt(), K = input.nextInt(), max = 1;
        int[] time = new int[K], up = new int[K], down = new int[K];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < K; i++) {
            time[i] = input.nextInt();      // 起始时间（下车时间不算）
            up[i] = input.nextInt();        // 上车站点
            down[i] = input.nextInt();      // 下车站点
            if (up[i] != down[i]) {         // 上下车站点同样的订单作废
                int num1 = Math.abs(up[i] - down[i]), num2 = N - num1;  // 在成环的路径里找最短路径
                int tmp = Math.min(num1, num2) * 5;                     // 相邻站点行车时间为 5
                // 将每一个订单在时间轴上画行车时间线（HashMap），统计每秒线的数量即可得最大同时运营车数
                for (int j = 0; j < tmp; j++) {
                    if (map.containsKey(time[i] + j)) {
                        map.put(time[i] + j, map.get(time[i] + j) + 1);
                        max = Math.max(max, map.get(time[i] + j));
                    }
                    else map.put(time[i] + j, 1);
                }
            }
        }

        System.out.println(max);
    }
}
