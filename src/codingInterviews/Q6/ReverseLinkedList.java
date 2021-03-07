package codingInterviews.Q6;

import java.util.Stack;

/**
 * Q6. 从尾到头打印链表
 *【题目】
 *      输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *【示例】
 *      输入：head = [1,3,2]
 *      输出：[2,3,1]
 */
public class ReverseLinkedList {

    public static void main(String[] args) {

    }

    /**
     * 栈：先入后出实现从尾到头打印
     * @author PAN
     * @param head 单链表头
     * @return 反转后单链表（以数组形式）
     * @time O(N)
     * @space O(N)
     */
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> s = new Stack<ListNode>();
        while (head != null) {
            s.push(head);
            head = head.next;
        }
        int len = s.size();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = s.pop().val;
        }
        return array;
    }
}
