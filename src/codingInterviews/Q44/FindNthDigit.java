package codingInterviews.Q44;

/**
 * Q44. 数字序列中某一位的数字
 * 【题目】
 *      数字以 0123456789101112131415… 的格式序列化到一个字符序列中。
 *      在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *      请写一个函数，求任意第n位对应的数字。
 * 【示例】
 *      输入：n = 3
 *      输出：3
 *      输入：n = 11
 *      输出：0
 */
public class FindNthDigit {
    public static void main(String[] args) {
        System.out.println(new FindNthDigit().findNthDigit(2147483647));
    }

    /**
     * 找规律：位数 = 1 + 9 * (10 ^ 0 * 1 + 10 ^ 1 * 2 + 10 ^ 2 * 3 + …… + 10 ^ (n - 1) * n)
     * @author PAN
     * @param n 第 n 位
     * @return 第 n 位对应数字
     * @time O(log N)
     * @space O(log N)
     */
    public int findNthDigit(int n) {
        long count = 1;
        int oldCount = 1, bit = 0;
        while (true) {
            if (count > n) break;
            oldCount = (int) count;
            bit++;
            count += 9 * Math.pow(10, bit - 1) * bit;
        }
        int sub = n - oldCount, num = (int)Math.pow(10, bit - 1) + sub / bit;
        String s = String.valueOf(num);
        return s.charAt(sub % bit) - '0';
    }

    /**
     * 暴力求解：超内存
     * @author PAN
     * @param n 第 n 位
     * @return 第 n 位对应数字
     */
    public int findNthDigit2(int n) {
        StringBuilder s = new StringBuilder();
        int i = 0;
        while (i >= 0) {
            s.append(i);
            i++;
            if (s.length() > n) break;
        }
        return s.charAt(n) - '0';
    }
}
