package exam.webank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 微众银行笔试第二题：从矩阵某个点出发（可上下左右移动），第一次经过拿走格子里所有金币，金币为 -1 表示格子不可走，求回到起点可收获的最大金币（18%，超时）
 */
public class Exam2 {
    public int beginX, beginY, t, n, m, maxMoney;
    public int[][] a;
    public ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        int n = 4, m = 4, k = 2, x = 2 - 1, y = 2 - 1;
        int n = input.nextInt(), m = input.nextInt(), k = input.nextInt(), x = input.nextInt() - 1, y = input.nextInt() - 1;
        Exam2 exam2 = new Exam2();
        exam2.beginX = x;
        exam2.beginY = y;
        exam2.t = k;
        exam2.n = n;
        exam2.m = m;
        int[][] a = new int[n][m];
//        int[][] a = {{0, -1, 1, 2}, {3, 1, 5, 1}, {1, -1, -1, -1}, {1, 1, -1, 0}};
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = input.nextInt();
        exam2.a = a;
        int money = a[x][y];
        HashSet<String> set = new HashSet<>();
        set.add(x + "" + y);
        exam2.list.add(x + "" + y);
        exam2.recur(x + 1, y, money, set, 1);
        exam2.recur(x - 1, y, money, set, 1);
        exam2.recur(x, y + 1, money, set, 1);
        exam2.recur(x, y - 1, money, set, 1);
        System.out.println(exam2.maxMoney);
    }
    public void recur(int x, int y, int money, HashSet<String> set, int step) {
        if (x == n || x == -1 || y == m || y == -1 || a[x][y] == -1) return;  // 越界 or 碰壁
        if (Math.abs(x - beginX) + Math.abs(y - beginY) > t - step) return;
        if (!set.contains(x + "" + y)) {
            money += a[x][y];
            set.add(x + "" + y);
        }
        list.add(x + "" + y);
        if (step == t) {
            if (x == beginX && y == beginY) {
                maxMoney = Math.max(maxMoney, money);
                String tmp = list.remove(list.size() - 1);
                if (!tmp.equals(beginX + "" + beginY)) set.remove(tmp);
            }
            else {
                set.remove(x + "" + y);
                list.remove(list.size() - 1);
            }
            return;
        }
        recur(x + 1, y, money, set, step + 1);
        recur(x - 1, y, money, set, step + 1);
        recur(x, y + 1, money, set, step + 1);
        recur(x, y - 1, money, set, step + 1);
    }
}
