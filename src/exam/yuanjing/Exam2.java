package exam.yuanjing;

import java.math.BigInteger;
import java.util.*;

/**
 * 远景笔试第一题：求 n! 尾数 0 的个数（time out，过了80%）
 */
public class Exam2 {
    public HashMap<Integer, BigInteger> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println(new Exam2().CountZero(n));
    }

    public int CountZero (int n) {
        int count = 0;
        // 超时
        // long mul = 1;
        if (n < 13) {
            long mul = 1;
            for (int i = n; i >= 1; i--) {
                mul *= i;

            }
            String s = String.valueOf(mul);
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != '0') break;
                else count++;
            }
        } else {
            BigInteger mul = new BigInteger(String.valueOf(479001600));
            for (int i = n; i >= 13; i--) {
                mul = mul.multiply(BigInteger.valueOf(i));
            }
            String s = String.valueOf(mul);
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) != '0') break;
                else count++;
            }
        }
        return count;
    }

    public int CountZero2 (int n) {
        int count = 0;
        // 改进写法（用空间换时间、记录下计算过的阶乘），没来得及提交
        BigInteger mul = new BigInteger(String.valueOf(1));
        for (int i = 1; i <= n; i++) {
            if (map.containsKey(i)) mul = map.get(i);
            else {
                mul = mul.multiply(BigInteger.valueOf(i));
                map.put(i, mul);
            }
        }
        String s = String.valueOf(mul);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') break;
            else count++;
        }
        return count;
    }
}
