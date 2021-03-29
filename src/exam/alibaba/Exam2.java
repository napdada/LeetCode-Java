package exam.alibaba;

import java.util.Scanner;

/**
 * 阿里笔试第二题：Floyd 问题（没调完）
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int demoNum = input.nextInt();  // 测试数据量
        for (int i = 0; i < demoNum; i++) {
            int cityNum = input.nextInt();
            int roadNum = input.nextInt();
            int distance = input.nextInt();
            int[][] roadDistance = new int[cityNum][cityNum];
            int[][] minRoadDistance = new int[cityNum][cityNum];

            int x, y;
            for (int j = 0; j < roadNum; j++) {
                x = input.nextInt();
                y = input.nextInt();
                roadDistance[x][y] = input.nextInt();
                roadDistance[y][x] = roadDistance[x][y];
                minRoadDistance[x][y] = roadDistance[x][y];
                minRoadDistance[y][x] = roadDistance[x][y];
            }

            for (int u = 0; u < cityNum; u++) {
                for (int v = 0; v < cityNum; v++) {
                    System.out.print(roadDistance[u][v] + " ");
                }
                System.out.println();
            }

            System.out.println("------------");
            // 计算任意两点之间的最短路径
            for (int u = 0; u < cityNum; u++) {
                for (int v = u + 1; v < cityNum; v++){
                    int k = u;
                    int tmp = 0;
                    while (k < cityNum) {
                        if (k != v){
                            tmp += roadDistance[k][v];
                            if (minRoadDistance[u][k] > tmp || minRoadDistance[u][k] == 0) {
                                minRoadDistance[u][k] = tmp;
                                minRoadDistance[k][u] = tmp;
                            }
                        }
                        k++;
                    }

                }
            }

            for (int u = 0; u < cityNum; u++) {
                for (int v = 0; v < cityNum; v++) {
                    System.out.print(minRoadDistance[u][v] + " ");
                }
                System.out.println();
            }

        }
    }
}
