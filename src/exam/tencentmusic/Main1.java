package exam.tencentmusic;

/**
 * 腾讯音乐移动客户端（IOS）一面第一题：青蛙跳台阶（AC）
 */
public class Main1 {
    public static void main(String[] args) {
        System.out.println(new Main1().climbStairs(5));
    }
    public int climbStairs(int n) {
        int num1 = 1, num2 = 2, sum = 0;
        if (n == 1) return num1;
        if (n == 2) return num2;
        for (int i = 2; i < n; i++) {
            sum = num1 + num2;
            num1 = num2;
            num2 = sum;
        }
        return sum;
    }
}
