package codingInterviews.Q10;

/**
 * Q10 - I. 斐波那契数列
 * 【题目】
 *      写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 *      > F(0) = 0,   F(1) = 1
 *      > F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *      > 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * ​	    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 【示例】
 *      输入：n = 2
 *      输出：1
 *      输入：n = 5
 *      输出：5
 */
public class Fib {

    public static void main(String[] args) {
        int n = 95;
        // System.out.println(fib(n));
        System.out.println(fib2(n));
    }

    /**
     * 递归求解 - 将斐波那契公式转换为递归形式
     * @author PAN
     * @param n 数列第 n 项
     * @return 斐波那契值
     */
    public static long fib(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            if (fib(n - 1) + fib(n - 2) > 1000000007) return (fib(n - 1) + fib(n - 2)) % 1000000007;
            else return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * 动态规划 - 某一项等于前两项之和，用循环依次计算。【注意！！！】先求余与最后求余返回结果一致，但先求可以防止 int 溢出
     * @author PAN
     * @param n
     * @return
     * @time O(N)
     * @space O(1)
     */
    public static int fib2(int n) {
        int a = 0, b = 1;
        switch (n) {
            case 0: return 0;
            case 1: return 1;
            default: {
                int tmp;
                for (int i = 1; i < n; i++) {
                    tmp = (a + b) % 1000000007;
                    a = b;
                    b = tmp;
                }
                return b;
            }
        }
    }

    /**
     * 动态规划 - 精简代码
     * @author 网友
     * @param n
     * @return
     */
    public static int fib3(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
