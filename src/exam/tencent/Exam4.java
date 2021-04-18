package exam.tencent;

import java.util.Stack;

/**
 * 腾讯第三场笔试第一题：给定链表可以循环移动末尾节点到头，找到所有循环移动链表中最小的那个（41%）
 */
public class Exam4 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode();
        ListNode node2 = new ListNode();
        ListNode node3 = new ListNode();
        ListNode node4 = new ListNode();
        ListNode node5 = new ListNode();
        node1.val = 1;
        node2.val = 2;
        node3.val = 3;
        node4.val = 4;
        node5.val = 1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        System.out.println(new Exam4().solve(node1));

    }
    public ListNode solve (ListNode S) {
        ListNode head = S, newHead = S, newHeadPre = S, tail = null, tmp = null, point = head.next;
        if (point == null) return S;
        Stack<ListNode> stack = new Stack<>();
        while (point != null) {
            stack.push(point);
            if (point.next == null) tail = point;
            point = point.next;
        }
        boolean flag = false;
        while (!stack.isEmpty()) {
            if (stack.peek().val < newHead.val) {
                newHead = stack.pop();
                newHeadPre = stack.isEmpty() ? S : stack.peek();
                flag = false;
            } else if (stack.peek().val == newHead.val) {
                flag = true;
                tmp = stack.pop();
            } else {
                if (flag) {
                    if (newHead != tmp) newHeadPre = stack.pop();
                    else stack.pop();
                    newHead = tmp;
                } else stack.pop();
            }
        }
        if (newHead == head) return head;
        tail.next = head;
        newHeadPre.next = null;
        return newHead;
    }
}
