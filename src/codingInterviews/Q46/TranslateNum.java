package codingInterviews.Q46;

/**
 * Q46. 把数字翻译成字符串
 * 【题目】
 *      给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 *      一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 【示例】
 *      输入: 12258
 *      输出: 5
 *      解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 */
public class TranslateNum {
    public int n = 0;
    public static void main(String[] args) {
        System.out.println(new TranslateNum().translateNum(12258));
        System.out.println(new TranslateNum().translateNum2(12258));
        System.out.println(new TranslateNum().translateNum3(12258));
    }

    /**
     * 递归：从左往右每次前进一位或两位
     * @author PAN
     * @param num 待翻译数字
     * @return 翻译方法数
     * @time O(N)
     * @space O(N)
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        recur(0, 1, s); // 前进一位
        recur(0, 2, s); // 前进两位
        return n;
    }
    public void recur(int i, int count, String s) {
        // 终止条件
        if (i + count == s.length()) {  // 前进结束
            if (count == 2) {   // 当最后一个数为两位数时需要判断合法性（0 - 25），再决定是否增加方法数
                String tmpS = s.substring(i, i + 2);
                if (tmpS.charAt(0) == '0' || Integer.parseInt(tmpS) > 25) return;
                else n++;       // 当最后一个数为一位数时直接增加方法数
            } else n++;
            return;
        } else if (i + count > s.length()) return;  // 长度溢出不合法

        // 递推工作
        if (count == 1) {
            recur(i + 1, 1, s);
            recur(i + 1, 2, s);
        } else {
            String tmpS = s.substring(i, i + 2);
            if (tmpS.charAt(0) == '0' || Integer.parseInt(tmpS) > 25) return;
            else {
                recur(i + 2, 1, s);
                recur(i + 2, 2, s);
            }
        }
    }

    /**
     * 类似青蛙跳台阶 DP：dp[i] = dp[i + 1] + dp[i + 2] 或 dp[i + 1] ，从右往左和从左往右一样
     * @author 网友
     * @param num 待翻译数字
     * @return 翻译方法数
     * @time O(N)
     * @space O(N)
     */
    public int translateNum2(int num) {
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }

    /**
     * 数字求余优化 DP
     * @author 网友
     * @param num 待翻译数字
     * @return 翻译方法数
     * @time O(N)
     * @space O(1)
     */
    public int translateNum3(int num) {
        int a = 1, b = 1, x, y = num % 10;  // a 记录 dp[i]，b 记录 dp[i - 1]
        while(num != 0) {
            num /= 10;
            x = num % 10;
            int tmp = 10 * x + y;
            int c = (tmp >= 10 && tmp <= 25) ? a + b : a;
            b = a;
            a = c;
            y = x;
        }
        return a;
    }

}
