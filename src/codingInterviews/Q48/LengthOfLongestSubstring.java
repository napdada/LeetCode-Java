package codingInterviews.Q48;

import java.util.HashMap;
import java.util.HashSet;
import java.util.jar.JarEntry;

/**
 * Q48. 最长不含重复字符的子字符串
 * 【题目】
 *      请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 【示例】
 *      输入: "abcabcbb"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 *      输入: "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 *      输入: "pwwkew"
 *      输出: 3
 *      解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LengthOfLongestSubstring {
    int max = 1;
    public static void main(String[] args) {
        String s = "dvdf";
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring(s));
    }

    /**
     * 滑动窗口：用 HashSet 维护一个滑动窗口，碰到重复的字符就删除上一个出现该字符以及之前的所有字符，每次更新最大长度
     * @author PAN
     * @param s 字符串
     * @return 最大子串长度
     * @time O(N ^ 2)
     * @space O(1)，字符 ASCII 码范围为 0 - 127，所以最多用 O(128) = O(1) 空间
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int record = 0;
        for (int i = 1; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                max = Math.max(max, set.size());
                int j = record;
                while (j < s.length() && s.charAt(j) != s.charAt(i)) {
                    set.remove(s.charAt(j));
                    j++;
                }
                set.remove(s.charAt(j));
                record = j + 1;
            }
            set.add(s.charAt(i));
            max = Math.max(max, set.size());
        }
        return max;
    }

    /**
     * 动态规划：dp[j - 1] < j - i 时，dp[j] = dp[j - 1] + 1；dp[j - 1] >= j - i 时，dp[j] = j - i
     * @author 网友
     * @param s 字符串
     * @return 最大子串长度
     * @time O(N)
     * @space O(1)
     */
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }
}
