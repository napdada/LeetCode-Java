package codingInterviews.Q19;

/**
 * Q19. 正则表达式匹配
 * 【题目】
 *      请实现一个函数用来匹配包含 . 和 * 的正则表达式。
 *      模式中的字符 . 表示任意一个字符，而 * 表示它前面的字符可以出现任意次（含0次）。
 *      在本题中，匹配是指字符串的所有字符匹配整个模式。
 *      例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * 【示例】
 *      输入:
 *      s = "aa"
 *      p = "a"
 *      输出: false
 *      解释: "a" 无法匹配 "aa" 整个字符串。
 *      输入:
 *      s = "aa"
 *      p = "a*"
 *      输出: true
 *      解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 */
public class IsMatch {
    public static void main(String[] args) {
        System.out.println(isMatch2("aa", "a"));
        System.out.println(isMatch2("aa", "a*"));
        System.out.println(isMatch2("ab", ".*"));
        System.out.println(isMatch2("aab", "c*a*b"));
        System.out.println(isMatch2("mississippi", "mis*is*p*."));
    }

    /**
     * 双指针遍历判断匹配：太复杂，放弃了
     * @author PAN
     * @param s 字符串
     * @param p 正则表达式
     * @return 匹配成功与否
     */
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) return false;
        if (s.length() == 0) {
            boolean flag = true;
            for (int i = 0; i < p.length() - 1; i++) {
                if (p.charAt(i) == '.' || p.charAt(i + 1) == '.') {
                    flag = false;
                    break;
                }
                if (p.charAt(i) >= 'a' && p.charAt(i) <= 'z' && p.charAt(i + 1) >= 'a' && p.charAt(i + 1) <= 'z') {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        int sI = 0, pI = 0;
        while (sI < s.length() && pI < p.length()) {
            if (p.charAt(pI) >= 'a' && p.charAt(pI) <= 'z') {
                if (p.charAt(pI) == s.charAt(sI)) {
                    sI++;
                    pI++;
                } else {
                    return false;
                }
            } else if (p.charAt(pI) == '.') {
                sI++;
                pI++;
            } else if (p.charAt(pI) == '*') {
                if (pI - 1 < 0) pI++;
                else {
                    char starVal = p.charAt(pI - 1);
                    int starValCount = 0;
                    while (sI < s.length() && s.charAt(sI) == starVal) {
                        sI++;
                        starValCount++;
                    }
                    if (sI >= s.length()) {
                        for (int i = pI; i < p.length() - 1; i++) {
                            if (p.charAt(pI) == '.' || p.charAt(pI + 1) == '.') return false;
                            if (p.charAt(pI) >= 'a' && p.charAt(pI) <= 'z' && p.charAt(pI + 1) >= 'a' && p.charAt(pI + 1) <= 'z')
                                return false;
                        }
                    }
                    int tmp = pI;
                    while (pI < p.length() - 1 && p.charAt(pI + 1) == starVal) {
                        if (pI - tmp <= starValCount) pI++;
                        else return false;
                    }
                }
            }
        }
        for (int i = pI; i < p.length() - 1; i++) {
            if (p.charAt(pI) == '.' || p.charAt(pI + 1) == '.') return false;
            if (p.charAt(pI) >= 'a' && p.charAt(pI) <= 'z' && p.charAt(pI + 1) >= 'a' && p.charAt(pI + 1) <= 'z')
                return false;
        }
        return true;
    }

    /**
     * 动态规划：每轮添加一个字符并判断是否能匹配，直至添加完整个字符串 s 和 p
     * @author 网友
     * @param s 字符串
     * @param p 正则表达式
     * @return 匹配成功与否
     * @time O(NM)
     * @space O(NM)
     */
    public static boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 2; j < p.length() + 1; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) dp[i][j] = true;
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true;
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
