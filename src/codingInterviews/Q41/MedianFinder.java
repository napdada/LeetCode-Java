package codingInterviews.Q41;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Q41. 数据流中的中位数
 * 【题目】
 *      如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如
 *      果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 【示例】
 *      [2,3,4] 的中位数是 3
 *      [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 */
public class MedianFinder {

    /**
     * 链表实现：addNum 时用插入排序，findMedian 时按奇偶判断返回
     * @author PAN
     * @time O(N ^ 2)
     * @space O(N)
     */
    ArrayList<Integer> list;
    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        int i = 0;
        while (i < list.size() && num > list.get(i)) i++;
        list.add(i, num);
    }

    public double findMedian() {
        return list.size() % 2 == 0 ?
                list.get(list.size() / 2) + list.get(list.size() / 2 - 1) / 2.0 :
                list.get(list.size() / 2);
    }

    /**
     * 优先队列实现大小堆：小顶堆存储较大的一半，大顶堆存储较小的一半；
     * @author 网友
     * @time O(log N)
     * @space O(N)
     */
    Queue<Integer> A, B;
    public MedianFinder(int n) {
        A = new PriorityQueue<>();
        B = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum2(int num) {
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian2() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}
