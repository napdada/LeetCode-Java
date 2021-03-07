package codingInterviews.Q9;

import java.util.Stack;

/**
 * Q9. 用两个栈实现队列
 * 【题目】
 *      用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 *      分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 【示例】
 *      输入：
 *      ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 *      [[],[],[5],[2],[],[]]
 *      输出：[null,-1,null,null,5,2]
 */
public class CQueue {
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
    public CQueue() {

    }

    public void appendTail(int value) {
        s1.push(value);
    }

    /**
     * 一栈负责进，一栈复杂出
     * @author PAN
     * @return 队列头
     * @time O(N)
     * @space O(N)
     */
    public int deleteHead() {
        if (s2.size() == 0) {
            if (s1.size() == 0) return -1;
            else {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
        }
        return s2.pop();
    }
}
