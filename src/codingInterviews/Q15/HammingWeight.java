package codingInterviews.Q15;

/**
 * Q15. 二进制中1的个数
 * 【题目】
 *      请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 *      例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 * 【示例】
 *      输入：00000000000000000000000000001011
 *      输出：3
 *      解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *      输入：00000000000000000000000010000000
 *      输出：1
 *      解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 */
public class HammingWeight {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(hammingWeight(n));
        System.out.println(hammingWeight2(n));
        System.out.println(hammingWeight3(n));
    }

    /**
     * 巧用 n & (n - 1)
     * (n−1)： 二进制数字 n 最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
     * n & (n - 1)： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
     * @author 网友 & PAN，借鉴 n & (n - 1) 的思想
     * @param n 无符号二进制数
     * @return 1 的个数
     * @time O(M) M 为 1 的个数
     * @space O(1)
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 逐位判断：将 n 和 1 做与操作，结果为 1 时计数，再将 n 向右移位重复操作。
     * @author 网友
     * @param n 无符号二进制数
     * @return 1 的个数
     * @time O(log2 N) M 为 1 的个数
     * @space O(1)
     */
    public static int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1; // 这里需要写成>>>，Java中的无符号右移
            // n = n >> 1; // 自己的写法
        }
        return count;
    }

    public static int hammingWeight3(int n) {
        return Integer.bitCount(n);
    }

}
