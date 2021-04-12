package codingInterviews.Q30;

import java.util.Stack;

/**
 * Q30. 包含 min 函数的栈
 * 【题目】
 *      定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * 【示例】
 *      MinStack minStack = new MinStack();
 *      minStack.push(-2);
 *      minStack.push(0);
 *      minStack.push(-3);
 *      minStack.min();   --> 返回 -3.
 *      minStack.pop();
 *      minStack.top();      --> 返回 0.
 *      minStack.min();   --> 返回 -2.
 */
public class MinStack {
    Stack<Integer> s1, s2;

    /**
     * 双栈模拟：一个正常栈 s1、一个存 s1 中的最小值
     * @author PAN
     * @time O(1)
     * @space O(N)
     */
    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>(); // 辅助栈，用来存放 s1 栈中的最小值
    }

    public void push(int x) {
        s1.push(x);
        if (s2.isEmpty()) s2.push(x);
        else s2.push(s2.peek() < x ? s2.peek() : x);
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }
}
