package codingInterviews.Q50;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Q50. 第一个只出现一次的字符
 * 【题目】
 * 	    在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 【示例】
 *      s = "abaccdeff"
 *      返回 "b"
 *      s = ""
 *      返回 " "
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        System.out.println(new FirstUniqChar().firstUniqChar("abaccdeff"));
    }

    /**
     * 哈希表记录字符出现次数
     * @author PAN
     * @param s 字符串
     * @return 第一个只出现一次的字符
     * @time O(N)
     * @space O(N)
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            else map.put(s.charAt(i), 1);
        }
        for (int i = 0; i < s.length(); i++)
            if (map.get(s.charAt(i)) == 1) return s.charAt(i);

        return ' ';
    }

    /**
     * 有序哈希表
     * @author 网友
     * @param s 字符串
     * @return 第一个只出现一次的字符
     * @time O(N)
     * @space O(N)
     */
    public char firstUniqChar2(String s) {
        LinkedHashMap<Character, Boolean> map = new LinkedHashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc)
            map.put(c, !map.containsKey(c));
        for (Map.Entry<Character, Boolean> m : map.entrySet())
            if (m.getValue()) return m.getKey();
        return ' ';
    }

}
