package codingInterviews.Q43;

/**
 * Q43. 1 ～ n 整数中 1 出现的次数
 * 【题目】
 *      输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *      例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 * 【示例】
 *      输入：n = 12
 *      输出：5
 *      输入：n = 13
 *      输出：6
 */
public class CountDigitOne {
    public static void main(String[] args) {
        int n = 123;
        System.out.println(new CountDigitOne().countDigitOne(n));
    }

    /**
     * 优化全暴力：利用 bitNum * Math.pow(10, bitNum - 1) 减少部分计算量，还是超时
     * @author PAN
     * @param n 整数
     * @return 含 1 的个数
     * @time O(N ^ 2)
     * @space O(1)
     */
    public int countDigitOne(int n) {
        int count = 0, bitNum = 0, num = n;
        while (num != 0) {
            num /= 10;
            bitNum++;
        }
        bitNum--;
        count = (int) (bitNum * Math.pow(10, bitNum - 1));
        int i = (int) Math.pow(10, bitNum);
        for ( ; i <= n; i++) {
            String s = String.valueOf(i);
            if (s.contains("1"))
                for (int j = 0; j < s.length(); j++)
                    if (s.charAt(j) == '1') count++;
        }
        return count;
    }

    /**
     * 递归：在利用 bitNum * Math.pow(10, bitNum - 1) 的基础上，对剩余的数再递归
     * @author 网友
     * @param n 整数
     * @return 含 1 的个数
     * @time O(log N)
     * @space O(log N)
     */
    public int countDigitOne2(int n) {
        return f(n);
    }
    private int f(int n ) {
        if (n <= 0)
            return 0;
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length()-1);
        int last = n - high * pow;
        if (high == 1) {
            return f(pow-1) + last + 1 + f(last);
        } else {
            return pow + high * f(pow-1) + f(last);
        }
    }

    /**
     * 找规律
     * @author 网友
     * @param n 整数
     * @return 含 1 的个数
     * @time O(log N)
     * @space O(1)
     */
    public int countDigitOne3(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

}
