package exam.wangyi;

import java.util.Scanner;

/**
 * 网易笔试第三题：加油站之间开车，每个加油站可以加一定油，俩相邻加油站花费一定油，初始油为 0，从哪个开始能绕一圈（AC）
 */
public class Exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] e = {2,3,4}, c = {3,4,3};
        System.out.println(new Exam3().canCompleteRace(e, c));
    }

    public int canCompleteRace (int[] e, int[] c) {
        // write code here
        int n = e.length;
        for (int i = 0; i < n; i++) {
            int j = i, sum = e[j], count = 0;
            while (count < n && sum >= c[j]) {  // 只有当前油量够下一步移动才开车
                int k = j + 1;
                if (j + 1 >= n) k = k % n;  // 下标溢出处理，以形成环
                sum = sum - c[j] + e[k];    // 更新油量
                count++;
                j++;
                if (j >= n) j = j % n;
            }
            if (count == n) return j;   // 转了一圈
        }
        return -1;
    }
}
