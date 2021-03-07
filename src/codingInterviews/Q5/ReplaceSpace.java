package codingInterviews.Q5;

/**
 * Q5.替换空格
 * 【题目】
 *      请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 【示例】
 *      输入：s = "We are happy."
 *      输出："We%20are%20happy."
 */
public class ReplaceSpace {
    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
        System.out.println(replaceSpace2(s));
        System.out.println(replaceSpace3(s));
    }

    /**
     * 遍历查找替换：逐个字符遍历字符串查找空格，字符串不为空格追加到新字符串，为空格追加"%20"
     * @author PAN
     * @param s 原字符串
     * @return 替换后字符串
     * @time O(N)
     * @space O(N)
     */
    public static String replaceSpace(String s) {
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c != ' ') {
                newS.append(c);
            } else {
                newS.append("%20");
            }
        }

        return newS.toString();
    }

    public static String replaceSpace2(String s) {
        return s.replace(" ","%20");
    }

    /**
     * 原地修改：不使用新字符串来存储，但在 Java Python 中不行，因它们字符串建立后不可改变，在 C++ 中可以通过两个指针来原地修改
     * @param s 原字符串
     * @return 替换后字符串
     * @time O(N)
     * @space O(1)
     */
    public static String replaceSpace3(String s) {
        return "";
    }
}
