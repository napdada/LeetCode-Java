package codingInterviews.Q12;

/**
 * Q12. 矩阵中的路径
 * 【题目】
 *      请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 *      路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 *      例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 *      [["a","b","c","e"],
 *      ["s","f","c","s"],
 *      ["a","d","e","e"]]
 *
 * ​	    但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * 【示例】
 *      输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *      输出：true
 *      输入：board = [["a","b"],["c","d"]], word = "abcd"
 *      输出：false
 */
public class ExistPath {
    public static void main(String[] args) {
        char[][] board = {{'a', 'b', 'c', 'e'}, {'s', 'f', 'c', 's'},{'a', 'd', 'e', 'e'}};
        String word = "bfce";
        String word2 = "abfb";
        System.out.println(existPath(board, word));
        System.out.println(existPath(board, word2));
    }

    /**
     * DFS + 剪枝：通过递归先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索
     * @author 网友
     * @param board 二维数字
     * @param word 路径字符串
     * @return 是否搜索到目标字符串
     * @time O(3^K*MN)
     * @space O(K)
     */
    public static boolean existPath(char[][] board, String word) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, wordArray, i, j, 0)) return true;
            }
        }
        return false;
    }

    /**
     * DFS 递归：朝上下左右四个方向，探索后将结点标为已经过，探索结束后将结点还原回初始值
     * @author 网友
     * @param board 二维数字
     * @param word 路径字符串
     * @param i 二维数组行号
     * @param j 二维数组列号
     * @param k 字符串下标
     * @return DFS 是否成功
     */
    public static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean result = dfs(board, word, i, j - 1, k + 1) || dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i - 1, j, k + 1);
        board[i][j] = word[k];
        return result;
    }
}
