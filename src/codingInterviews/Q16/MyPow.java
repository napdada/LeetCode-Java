package codingInterviews.Q16;

/**
 * Q16. 数值的整数次方
 * 【题目】
 *      实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 *      不得使用库函数，同时不需要考虑大数问题。
 * 【示例】
 *      输入：x = 2.00000, n = 10
 *      输出：1024.00000
 *      输入：x = 2.10000, n = 3
 *      输出：9.26100
 *      输入：x = 2.00000, n = -2
 *      输出：0.25000
 */
public class MyPow {
    public static void main(String[] args) {
        System.out.println(myPow(-1, -2147483648));
        System.out.println(myPow(2.5, 2));
        System.out.println(myPow(2, -2));

        System.out.println(myPow2(-1, -2147483648));
        System.out.println(myPow2(2.5, 2));
        System.out.println(myPow2(2, -2));
    }

    /**
     * 循环求解：用循环一次次累乘，以求幂运算。
     * 该法有几个坑：
     * 1. 需要对 x = -1, 0, 1 的特殊情况进行判断；
     * 2. 测试用例中有一项 n 为 2147483648，超过 int 范围（2147483647），
     *    需要用long 或者判断 double 精度不够直接置 0；
     * @author PAN
     * @param x 底数
     * @param n 幂次
     * @return x ^ n
     * @time O(N)
     * @space O(1)
     */
    public static double myPow(double x, int n) {
        if (x == 0 || x == 1) return x;
        double pow = 1;
        if (n < 0) {
            n = n * (-1);
            x = 1.0 / x;
        }
        if (x == -1 && n % 2 == 0) return -x;
        else if (x == -1 && n % 2 != 0) return x;
        if (n < 0) return 0;
        for (int i = 0; i < n; i++) {
            pow *= x;
            if (pow == 0) break;
        }
        return pow;
    }

    /**
     * 二进制快速幂：利用十进制数字 n 的二进制表示，可对快速幂进行数学化解释
     * 或二分法快速幂：对 n 不断除以 2 来快速计算，只需要判断 n 的奇偶
     * @author 网友
     * @param x 底数
     * @param n 幂次
     * @return x ^ n
     * @time O(log2 N)
     * @space O(1)
     */
    public static double myPow2(double x, int n) {
        if (x == 0) return x;
        double pow = 1.0;
        long newN = n;
        if (newN < 0) {
            newN = -newN;
            x = 1.0 / x;
        }
        while (newN != 0) {
            if ((newN & 1) == 1) pow *= x;
            x *= x;
            newN >>= 1;
        }
        return pow;
    }
}
