package exam.microsoft;

/**
 * 两根绳子切成相等的四段，可以（4-0、3-1、2-2）这样切，求最大的段值，切不了返回 0（73%）
 */
public class Exam2 {
    public static void main(String[] args) {
        int A = 10, B = 21;
        int A2 = 13, B2 = 11;
        int A3 = 2, B3 = 1;
        int A4 = 1, B4 = 8;
        System.out.println(new Exam2().solution(A, B));
        System.out.println(new Exam2().solution(A2, B2));
        System.out.println(new Exam2().solution(A3, B3));
        System.out.println(new Exam2().solution(A4, B4));
    }
    public int solution(int A, int B) {
        // write your code in Java SE 8
        int len1, len2, len3;
        int maxNum = Math.max(A, B);
        int minNum = Math.min(A, B);

        // 一根切 4，一根切 0
        len1 = maxNum / 4;

        // 一根切 3，一根切 1
        len2 = maxNum / 3;
        if (minNum < len2) len2 = 0;

        // 一根切 2，一根切 2
        len3 = minNum / 2;

        int maxLen = 0;
        maxLen = Math.max(len1, len2);
        maxLen = Math.max(maxLen, len3);

        return maxLen;
    }
}
