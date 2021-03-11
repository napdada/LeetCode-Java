package codingInterviews.Q17;

import java.util.Arrays;

/**
 * Q17. 打印从1到最大的n位数
 * 【题目】
 * 	    输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 * 	    比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 【示例】
 *      输入: n = 1
 *      输出: [1,2,3,4,5,6,7,8,9]
 */
public class PrintNumbers {
    int n, count = 0;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int[] printArray;

    public static void main(String[] args) {
        int n = 2;
        printNumbers(n);

        PrintNumbers pn = new PrintNumbers();
        System.out.println(Arrays.toString(pn.printNumbers2(n)));
    }

    /**
     * 循环打印：直接构建数组循环输出
     * @author PAN
     * @param n 最大位数
     * @return 从 1 到最大的 n 位十进制数
     * @time O(10^N)
     * @space O(1)
     */
    public static int[] printNumbers(int n) {
        int max = (int) Math.pow(10, n) - 1;
        int[] printArray = new int[max];
        for (int i = 0; i < max; i++) {
            printArray[i] = i + 1;
        }
        return printArray;
    }

    /**
     * 大数打印： 题目给定了 int 范围，但实际情况可能会考大数，这时候用 int 无法解，需要用 String。
     * @author 网友 & PAN，根据思路改了一些写法，
     * 但还有问题：这样默认还是 int 范围，应该将 printNumber 返回结果也改为 String
     * @param n 最大位数
     * @return 从 1 到最大的 n 位十进制数
     * @time O(10^N)
     * @space O(10^N)
     */
    public int[] printNumbers2(int n) {
        this.n = n;
        this.num = new char[n];
        this.printArray = new int[(int) Math.pow(10, n) - 1];
        dfs(0);
        return this.printArray;
    }

    /**
     * 递归主体：先固定高位，向低位递归，当个位已被固定时，添加数字的字符串
     * @param x 递归位数（0 - n）
     */
    public void dfs(int x) {
        if (x == this.n) {
            if (Integer.parseInt(String.valueOf(num)) != 0) {
                this.printArray[count] = Integer.parseInt(String.valueOf(num));
                count++;
            }
            return;
        }
        for (char c : this.loop) {
            this.num[x] = c;
            dfs(x + 1);
        }
    }

}
