package exam.microsoft;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 微软笔试第一题：无序数组中任意两数的最大差值（AC）
 */
public class Exam {
    public static void main(String[] args) {
        int[] X = {1,8,7,3,4,1,8}, Y = {6,4,1,8,5,1,7};
        int[] X2 = {5,5,5,7,7,7}, Y2 = {3,4,5,1,3,7};
        int[] X3 = {6,10,1,4,3}, Y3 = {2,5,3,1,6};
        int[] X4 = {4,1,5,4,65}, Y4 = {4,5,1,3,0};
        System.out.println(new Exam().solution(X, Y));
        System.out.println(new Exam().solution(X2, Y2));
        System.out.println(new Exam().solution(X3, Y3));
        System.out.println(new Exam().solution(X4, Y4));
        System.out.println("--------------");
        System.out.println(new Exam().solution2(X, Y));
        System.out.println(new Exam().solution2(X2, Y2));
        System.out.println(new Exam().solution2(X3, Y3));
        System.out.println(new Exam().solution2(X4, Y4));
    }

    public int solution(int[] X, int[] Y) {
        // write your code in Java SE 8
        int[] xCopy = Arrays.copyOf(X, X.length);   // 拷贝一份横坐标，防止篡改原数据
        Arrays.sort(xCopy);     // 对横坐标进行排序，O(n * log n)
        int maxLen = 1;
        for (int i = 1; i < xCopy.length; i++) {
            maxLen = Math.max((xCopy[i] - xCopy[i - 1]), maxLen);
        }
        return maxLen;
    }

    public int solution2(int[] X, int[] Y) {
        // write your code in Java SE 8
        int max = X[0], min = X[0];
        for (int x : X) {
            max = Math.max(x, max);
            min = Math.min(x, min);
        }
        int len = max - min + 1;
        int[] count = new int[len];
        for (int x : X) {
            if (count[x - min] == 0) count[x - min] = 1;
        }
        int maxLen = 1, num = 0;
        for (int value : count) {
            if (value == 0) num++;
            else {
                maxLen = Math.max(maxLen, num);
                num = 0;
            }
        }
        return maxLen + 1;
    }

    public int[] solutionn(int[] A, int K) {
        // write your code in Java SE 8
        if (A.length == 0) return new int[0];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            list.add(A[i]);
        }
        for (int i = 0; i < K; i++) {
            int tmp = list.remove(list.size() - 1);
            list.add(0, tmp);
        }
        int[] a = new int[A.length];
        for (int i = 0; i < a.length; i++)
            a[i] = list.get(i);
        return a;
    }
}
