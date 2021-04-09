package exam.microsoft;

import java.util.ArrayList;

/**
 * 一个非递减序列中参杂一些数破坏了非递减，求删除最少的数使得数列恢复非递减（10%）
 */
public class Exam3 {
    public int max = 0; // 非递减队列最大长度

    public static void main(String[] args) {
        int[] A = {1,2,3,1,1,5};
        System.out.println(new Exam3().solution(A));
    }
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 0 || A.length == 1) return 0;

        ArrayList<Integer> list = new ArrayList<>();    // 队列记录非递减数列
        recur(A, 0, true, list);    // 每一个数可以选择加入或者不加入队列
        list.clear();
        recur(A, 0, false, list);   // 每一个数可以选择加入或者不加入队列
        return A.length - max;
    }

    /**
     * 递归函数
     * @param A 数组
     * @param start 数组的下标
     * @param flag 是否加入非递减队列
     * @param list 非递减队列
     */
    public void recur(int[] A, int start, boolean flag, ArrayList<Integer> list) {
        if (start < A.length && flag) list.add(A[start]);   // 当 flag 为 true 时选择加入非递减队列
        if (start == A.length - 1) {
            max = Math.max(max, list.size());   // 一次完整的递归后，比较当前队列和已遍历队列长度，最大值赋给 max
            return;
        }
        if (list.isEmpty()) {
            recur(A, start + 1, true, list);
            list.remove(list.size() - 1);   // 将上一次递归加入的队列尾元素删除
            recur(A, start + 1, false, list);
        } else {
            start++;
            while (start < A.length && A[start] < list.get(list.size() - 1)) {  // 找到下一个不小于队列尾的元素
                start++;
            }
            if (start != A.length) {
                recur(A, start, true, list);
                list.remove(list.size() - 1);   // 将上一次递归加入的队列尾元素删除
                recur(A, start, false, list);
            }
        }
        max = Math.max(max, list.size());
    }
}
