package codingInterviews.Q4;

/**
 * Q4. 二维数组中的查找
 * 【题目】
 *      在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *      请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 【示例】
 *      现有矩阵 matrix 如下：
 *      [
 *          [1,   4,  7, 11, 15],
 *          [2,   5,  8, 12, 19],
 *          [3,   6,  9, 16, 22],
 *          [10, 13, 14, 17, 24],
 *          [18, 21, 23, 26, 30]
 *      ]
 *      给定 target = 5，返回 true。
 *      给定 target = 20，返回 false。
 */
public class FindNumberIn2DArray {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target = 30;

        System.out.println("find result = " + findNumberIn2DArray(matrix, target));
        System.out.println("find result2 = " + findNumberIn2DArray2(matrix, target));
    }

    /**
     * 线性查找：从二维数组右上角元素开始与 target 比较，target 大则向下比较， target 小则向左比较
     * @author 网友
     * @param matrix 二维数组
     * @param target 查找目标
     * @return true/false
     * @time O(N + M)
     */
    public static boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        } else {
            int i = 0, j = matrix[0].length - 1;
            do {
                if (target < matrix[i][j]) {
                    j--;
                } else if (target == matrix[i][j]) {
                    return true;
                } else {
                    i++;
                }
            } while (i < matrix.length && j > -1);

            return false;
        }
    }

    /**
     * 暴力求解
     * @author PAN
     * @param matrix int[][]
     * @param target int
     * @return true/false：查找结果
     * @time O(N * M)
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (target == matrix[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
