package codingInterviews.Q20;

import java.util.HashMap;
import java.util.Map;

/**
 * Q20. 表示数值的字符串
 * 【题目】
 *      请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *      例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 *      但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
public class IsNumber {
    public static void main(String[] args) {
        String[] s = {"+100", "5e2", "-123", "3.1416", "-1E-16", "0123",
                      "12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"};
        for (String value : s) {
            System.out.println("Method1 : " + isNumber(value));
            System.out.println("Method2 : " + isNumber2(value));
        }
    }

    /**
     * 遍历判断：逐位遍历判断是否符合数值要求
     * 1. '.'出现正确的情况：只出现一次，且在e/E前；
     * 2. 'e/E'出现正确的情况：只出现一次，且出现前有数字；
     * 3. '+/-'出现正确的情况：只能在开头或者e/E后一位；
     * @author 网友 & PAN，想到了 1-3 点但实现参考网友思路，如何将想法快速简单实现很关键
     * @param s 待判断的字符串
     * @return 是否是数值
     * @time O(N)
     * @space O(1)
     */
    public static boolean isNumber(String s) {
        boolean hasNum = false, hasDecimal = false, hasE = false; // 是否有数字、小数、e/E
        s = s.trim(); // 删除前后多余空格
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') hasNum = true;
            else if (s.charAt(i) == '.' && !hasDecimal && !hasE) {
                hasDecimal = true;
            } else if ((s.charAt(i) == 'e' || s.charAt(i) == 'E') && !hasE && hasNum){
                hasE = true;
                hasNum = false;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

            } else {
                return false;
            }
        }
        return hasNum;
    }

    /**
     * 有限状态自动机：根据字符类型和合法数值的特点，先定义状态，再画出状态转移图
     * @author 网友
     * @param s 待判断的字符串
     * @return 是否是数值
     * @time O(N)
     * @space O(1)
     */
    public static boolean isNumber2(String s) {
        Map[] states = {
                new HashMap<Character, Integer>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<Character, Integer>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<Character, Integer>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<Character, Integer>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<Character, Integer>() {{ put('d', 3); }},                                        // 4.
                new HashMap<Character, Integer>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<Character, Integer>() {{ put('d', 7); }},                                        // 6.
                new HashMap<Character, Integer>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<Character, Integer>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if(!states[p].containsKey(t)) return false;
            p = (int)states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }

}
