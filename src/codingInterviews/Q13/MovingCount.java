package codingInterviews.Q13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Q13. 机器人的运动范围
 * 【题目】
 *      地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 *      一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 *      例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 *      请问该机器人能够到达多少个格子？
 * 【示例】
 *      输入：m = 2, n = 3, k = 1
 *      输出：3
 *      输入：m = 3, n = 1, k = 0
 *      输出：1
 */
public class MovingCount {
    HashSet<String> xy = new HashSet<>();

    public static void main(String[] args) {
        MovingCount mv = new MovingCount();
        System.out.println(mv.movingCount(3, 1, 0));
        System.out.println(mv.movingCount2(3, 1, 0));
        System.out.println(mv.movingCount3(3, 1, 0));
    }

    /**
     * 递归求解，与Q12类似，仅需向右、向下移动
     * @author PAN
     * @param m 行数
     * @param n 列数
     * @param k 位数和上限
     * @return 可达数
     * @time O(MN)
     * @space O(MN)
     */
    int movingCount(int m, int n, int k) {
        DFS(m, n, 0, 0, k);
        return xy.size();
    }

    /**
     * DFS：递归函数
     * @author PAN
     * @param m 行数
     * @param n 列数
     * @param i 初始位置
     * @param j 初始位置
     * @param k 位数和上限
     */
    void DFS(int m, int n, int i, int j, int k) {
        int sum = i / 100 + i / 10 + i % 10 + j / 100 + j / 10 + j % 10;
        boolean flag = sum > k ? false : true;
        String tmp = i + "," + j;
        if (i < 0 || i >= m || j < 0 || j >= n || !flag || xy.contains(tmp)) return;
        xy.add(tmp);
        DFS(m, n, i, j + 1, k);
        DFS(m, n, i + 1, j, k);

        // 优化，不必向上、向左，也意味不需要判断 i < 0 || j < 0
        // DFS(m, n, i, j - 1, k);
        // DFS(m, n, i - 1, j, k);
    }

    /**
     * 递归求解优化：用 visited 数组记录是否访问，并精简代码
     * @author PAN
     * @param m 行数
     * @param n 列数
     * @param k 位数和上限
     * @return 可达数
     * @time O(MN)
     * @space O(MN)
     */
    int movingCount2(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return DFS2(m, n, 0, 0, k, visited);
    }
    int DFS2(int m, int n, int i, int j, int k, boolean[][] visited){
        int sum = i / 100 + i / 10 + i % 10 + j / 100 + j / 10 + j % 10;
        if (i >= m || j >= n || sum > k || visited[i][j]) return 0;
        visited[i][j] = true;
        return DFS2(m, n, i, j + 1, k, visited) + DFS2(m, n, i + 1, j, k, visited) + 1;
    }

    /**
     * BFS：用队列实现，重点在于数位和增量公式(不需要每次都用整除和求余去计算位数和)
     * @author 网友
     * @param m 行数
     * @param n 列数
     * @param k 位数和上限
     * @return 可达数
     * @time O(MN)
     * @space O(MN)
     */
    public int movingCount3(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue= new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0, 0 });
        while(queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if(i >= m || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res ++;
            queue.add(new int[] { i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj }); // 数位和增量公式
            queue.add(new int[] { i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8 });
        }
        return res;
    }

}
