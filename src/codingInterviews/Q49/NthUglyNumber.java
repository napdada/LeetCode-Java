package codingInterviews.Q49;

public class NthUglyNumber {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(new NthUglyNumber().nthUglyNumber(n));
        System.out.println(new NthUglyNumber().nthUglyNumber2(n));
    }

    /**
     * 暴力求解：从 2 开始判断整数是否是丑数
     * @author PAN
     * @param n 从小到大第 n 个丑数
     * @return 从小到大第 n 个丑数值
     * @time O(N ^ 2)
     * @space O(1)
     */
    public int nthUglyNumber(int n) {
        if (n == 1) return n;
        int count = 1;
        for (int i = 2; ; i++) {
            int num = i;
            while (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) {
                if (num % 2 == 0) num /= 2;
                if (num % 3 == 0) num /= 3;
                if (num % 5 == 0) num /= 5;
            }
            if (num == 1) count++;
            if (count == n) return i;
        }
    }

    /**
     * 三指针：每个丑数必然是由小于它的某个丑数乘 2 或乘 3 或乘 5 得到的，三个指针每次挑出最小的
     * 2 集合：1 * 2、2 * 2、3 * 2、4 * 2、5 * 2、6 * 2、8 * 2
     * 3 集合：1 * 3、2 * 3、3 * 3、4 * 3、5 * 3、6 * 3、8 * 3
     * 5 集合：1 * 5、2 * 5、3 * 5、4 * 5、5 * 5、6 * 5、8 * 5
     * @author 网友思路，自己实现
     * @param n 从小到大第 n 个丑数
     * @return 从小到大第 n 个丑数值
     * @time O(N)
     * @space O(N)
     */
    public int nthUglyNumber2(int n) {
        int res = 1, a = 2, b = 3, c = 5, x = 0, y = 0, z = 0;
        int[] num = new int[n];
        num[0] = 1;
        for (int i = 1; i < n; i++) {
            num[i] = Math.min(Math.min(a, b), c);
            if (num[i] == a) a = num[++x] * 2;
            if (num[i] == b) b = num[++y] * 3;
            if (num[i] == c) c = num[++z] * 5;
        }
        return num[n - 1];
    }
}
