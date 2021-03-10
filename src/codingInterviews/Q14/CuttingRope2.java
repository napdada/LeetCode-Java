package codingInterviews.Q14;

/**
 * Q14 - II. 剪绳子 II
 * 【题目】
 *      给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 *      请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 *      例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 *      答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 【示例】
 *      输入: 2
 *      输出: 1
 *      解释: 2 = 1 + 1, 1 × 1 = 1
 *
 *      输入: 10
 *      输出: 36
 *      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 */
public class CuttingRope2 {
    public static void main(String[] args) {
        System.out.println(cuttingRope(2));
        System.out.println(cuttingRope(10));
        System.out.println(cuttingRope(120));
        System.out.println(cuttingRope(127));
    }

    /**
     * 循环求余
     * @author PAN
     * @param n 绳子长度
     * @return 最大乘积
     * @time O(N)
     * @space O(1)
     */
    public static int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        long mul = 1;
        int r = 1000000007;
        for (int i = 0; i < a - 1; i++) {
            mul = (mul * 3) % r;
        }
        if(b == 0) return (int)(mul * 3 % r);
        else if(b == 1) return (int)((4 * mul) % r);
        else return (int)(mul * 6 % r);
    }

    /**
     * 快速求余
     * @author 网友
     * @param n 绳子长度
     * @return 最大乘积
     * @time O(log2 N)
     * @space O(1)
     */
    public int cuttingRope2(int n) {
        if(n <= 3) return n - 1;
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            if(a % 2 == 1) rem = (rem * x) % p;
            x = (x * x) % p;
        }
        if(b == 0) return (int)(rem * 3 % p);
        if(b == 1) return (int)(rem * 4 % p);
        return (int)(rem * 6 % p);
    }
}
