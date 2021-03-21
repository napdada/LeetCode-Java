package codingInterviews.Q31;

import java.util.Stack;

/**
 * Q31. 栈的压入、弹出序列
 * 【题目】
 *      输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 *      假设压入栈的所有数字均不相等。
 *      例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 *      但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * 【示例】
 *      输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 *      输出：true
 *      解释：我们可以按以下顺序执行：
 *      push(1), push(2), push(3), push(4), pop() -> 4,
 *      push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *      输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 *      输出：false
 *      解释：1 不能在 2 之前弹出。
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] push1 = {1, 2, 3, 4, 5}, pop1 = {4, 5, 3, 2, 1};
        int[] push2 = {1, 2, 3, 4, 5}, pop2 = {4, 3, 5, 1, 2};
        //System.out.println(validateStackSequences(push1, pop1));
        //System.out.println(validateStackSequences(push2, pop2));
        System.out.println(validateStackSequences2(push1, pop1));
        System.out.println(validateStackSequences2(push2, pop2));
    }

    /**
     * 双指针模拟栈操作，将 pushed 数组当成栈操作：
     * 1. top1、top2 分别指向两个数组模拟栈顶位置；
     * 2. 俩 top 不相等，则入栈，相等则出栈
     * @author PAN
     * @param pushed 入栈
     * @param popped 出栈
     * @return 是否成立
     * @time O(N ^ 2)
     * @space O(1) 时间换空间，最大程度节省空间
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int top1 = 0, top2= 0;
        int len1 = pushed.length, len2 = popped.length;
        if (len1 == 0) return true;
        while (top1 < len1 && top2 < len2) {
            if (top1 < 0) top1++;   // 防止 top1 = -1
            if (pushed[top1] != popped[top2]) { // 入栈
                top1++;
            } else {    // 出栈
                top1--;
                len1--;
                top2++;
                for (int i = top1 + 1; i < len1; i++) { // 出栈点后的数组内容前移
                    pushed[i] = pushed[i + 1];
                }
            }
            if (len1 == 0) return true;
        }
        return false;
    }

    /**
     * 栈辅助：利用一个栈辅助，先将 pushed 元素入栈，再判断栈顶与 popped 是否相等，相等则出栈，不等则继续进栈
     * @author 网友
     * @param pushed 入栈
     * @param popped 出栈
     * @return 是否成立
     * @time O(N)
     * @space O(N)
     */
    public static boolean validateStackSequences2(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            s.push(pushed[i]);
            while (!s.isEmpty() && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }
        return s.isEmpty();
    }
}
