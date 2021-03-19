package codingInterviews.Q29;

import java.util.Arrays;

/**
 * Q29. 顺时针打印矩阵
 * 【题目】
 *      输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 【示例】
 *      输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      输出：[1,2,3,6,9,8,7,4,5]
 *      输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *      输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralOrder {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] a2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(Arrays.toString(spiralOrder(a)));
        System.out.println(Arrays.toString(spiralOrder(a2)));
    }

    /**
     * 设定四边界：设定右、下、左、上边界，一个小人从左上角开始顺时针由外向里走，每转一圈边界缩小一圈
     * @author PAN
     * @param matrix 二维数组
     * @return 顺时针由外向里遍历结果
     * @time O(M * N)：M：行数，N：列数
     * @space O(1)：题目本身要求返回 int[]，这个数组不算在 space 里
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        // m：右边界，n：下边界，mm：左边界，nn：上边界，因为从左上顶点开始，所以 mm 初始值为 1（防止重复）
        int m = matrix.length, n = matrix[0].length, mm = 1, nn = 0;
        int num = m * n;
        int[] array = new int[num];
        int x = 0, y = 0; // 小人坐标(x, y)
        for (int i = 0; i < num; ) {
            while (y < n) { // 向右
                array[i] = matrix[x][y];
                i++;
                y++;
            }
            if (i == num) break;    // 已遍历完
            y--;    // 使得超过下标的 y 复原成不超过下标
            x++;    // 防止重复添加顶点
            while (x < m) { // 向下
                array[i] = matrix[x][y];
                i++;
                x++;
            }
            if (i == num) break;
            x--;
            y--;
            while (y >= nn) { // 向左
                array[i] = matrix[x][y];
                i++;
                y--;
            }
            if (i == num) break;
            y++;
            x--;
            while (x >= mm) { // 向上
                array[i] = matrix[x][y];
                i++;
                x--;
            }
            if (i == num) break;
            x++;
            y++;
            // 右、下、左、上一次顺时针后，将边界范围缩小一圈
            m--;
            n--;
            mm++;
            nn++;
        }
        return array;
    }
}
