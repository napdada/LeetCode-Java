package codingInterviews.Q14;

/**
 * Q14 - I. 剪绳子
 * 【题目】
 * 	    给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 * 	    请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 	    例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 【示例】
 *      输入: 2
 *      输出: 1
 *      解释: 2 = 1 + 1, 1 × 1 = 1
 *
 *      输入: 10
 *      输出: 36
 *      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 */
public class CuttingRope {
    public static void main(String[] args) {
        System.out.println(cuttingRope(2));
        System.out.println(cuttingRope(10));
    }

    /**
     * 数学推导/贪心：
     *  1. 绳子越等分乘积越大；
     *  2. 越多切分成长度 3 ，乘积越大；
     *  3. 以长度 3 为基础去切分，对最后一段长度（可能：0， 1， 2）进行讨论计算；
     * @author PAN & 网友：自己推断了1，但2不太确定，可以用求导得驻点是e（2.7）
     * @param n 绳子长度
     * @return 最大乘积
     * @time O(1)
     * @space O(1)
     */
    public static int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int) Math.pow(3, a);
        else if(b == 1) return 4 * (int) Math.pow(3, a - 1);
        else return 2 * (int) Math.pow(3, a);
    }

    /**
     * 动态规划：要求绳子剪掉后最大乘积，可从前面比 n 小的绳子转移而来
     *  1. 用数组记录从 0 到 n 长度的绳子剪掉后最大的乘积，初始化 dp[2] = 1；
     *  2. 从长度 2 开始剪（从 1 剪对乘积没意义）剪了一段后，剩下 i - j 可剪可不；
     *  3. 若剪，乘积为 j * dp[i - j]；若不剪，j * (i - j)；
     *  4. 上述俩者取 max，第一段长度 j 可取区间为 [2, i)，对所有 j 不同情况取max；
     *  5. dp[i] 转移方程： dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
     * @author 网友
     * @param n 绳子长度
     * @return 最大乘积
     * @time O(N^2)
     * @space O(N)
     */
    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i < n + 1; i++){
            for(int j = 2; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
