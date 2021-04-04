package exam.meituan;

import java.util.Scanner;

/**
 * 美团笔试第一题：给定一个字符串，统计该字符串的子串中没有重复字母的个数（NAC）
 */
public class Exam {
    public static void main(String[] args) {
        // 复盘其实是个脑筋急转弯，总共 26 个小写字母，先统计每个字母出现的个数，再用排列组合（从 a - z）
        // 最后结果等于每个字符出现次数 + 1 后的累乘，注意取模和 int、long 溢出即可
    }
}
