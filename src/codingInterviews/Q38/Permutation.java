package codingInterviews.Q38;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Q38. 字符串的排列
 * 【题目】
 * ​	    输入一个字符串，打印出该字符串中字符的所有排列。
 * ​	    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 【示例】
 *      输入：s = "abc"
 *      输出：["abc","acb","bac","bca","cab","cba"]
 */
public class Permutation {
    ArrayList<String> list = new ArrayList<>();
    char[] c;

    public static void main(String[] args) {
        String s = "abcd";
        String[] result = new Permutation().permutation(s);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 回溯实现全排列：通过字符交换，先固定第 1 位、再第 2 位、最后第 n 位
     * @author 参考网友自己实现
     * @param s 字符串
     * @return 全排列字符串数组
     * @time O(N! * N)
     * @space O(N ^ 2)
     */
    public String[] permutation(String s) {
        c = s.toCharArray();
        recur(c, 0, c.length);
        return list.toArray(new String[c.length]);
    }
    public void recur(char[] c, int start, int len) {
        if (start == len - 1) {
            list.add(String.valueOf(c));
        } else {
            HashSet<Character> set = new HashSet<>();   // 防止同一层递归出现重复元素
            for (int i = start; i < len; i++) {
                if (set.contains(c[i])) continue;
                set.add(c[i]);
                swap(c, start, i);
                recur(c, start + 1, len);   // 进入下一层递归，即下一位
                swap(c, start, i);               // 返回时交换回来
            }
        }
    }
    public  void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
