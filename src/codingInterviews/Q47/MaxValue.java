package codingInterviews.Q47;

/**
 * Q47. 礼物的最大价值
 * 【题目】
 *      在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *      你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 *      给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 【示例】
 *      输入:
 *      [
 *          [1,3,1],
 *          [1,5,1],
 *          [4,2,1]
 *      ]
 *      输出: 12
 *      解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 */
public class MaxValue {
    public int max = 0, num = 0;
    public static void main(String[] args) {
        int[][] a = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new MaxValue().maxValue(a));
        System.out.println(new MaxValue().maxValue2(a));
    }

    /**
     * 递归暴力求解：每个位置可以往右或往下
     * @author PAN
     * @param grid 收益矩阵
     * @return 最大收益
     * @time O(N * M * (N + M))，超时
     * @space O(N + M)
     */
    public int maxValue(int[][] grid) {
        recur(0, 0, grid);
        return max;
    }
    public void recur(int x, int y, int[][] a) {
        if (x == a.length && y == a[0].length) {
            max = Math.max(max, num);
            return;
        }
        num += a[x][y];
        if (x == a.length - 1) {
            for (int j = y + 1; j < a[0].length; j++)
                num += a[x][j];
            max = Math.max(max, num);
            return;
        }
        if (y == a[0].length - 1) {
            for (int j = x + 1; j < a.length; j++)
                num += a[j][y];
            max = Math.max(max, num);
            return;
        }
        int tmp = num;
        recur(x + 1, y, a);
        num = tmp;
        recur(x, y + 1, a);
    }

    /**
     * 动态规划：
     * 1. 初始化最上边行（从左累加到右）、最左边列（从上累加到下）；
     * 2. grid[i][j] = max(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]；
     * @author 网友
     * @param grid 收益矩阵
     * @return 最大收益
     * @time O(N * M)
     * @space O(1)
     */
    public int maxValue2(int[][] grid) {
        for (int i = 1; i < grid.length; i++)
            grid[i][0] += grid[i - 1][0];
        for (int i = 1; i < grid[0].length; i++)
            grid[0][i] += grid[0][i - 1];
        for (int i = 1; i < grid.length; i++)
            for (int j = 1; j < grid[i].length; j++)
                grid[i][j] =  Math.max(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
