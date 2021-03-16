package codingInterviews.Q24;

import java.util.Stack;

/**
 * Q24. 反转链表
 * 【题目】
 *      定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 【示例】
 *      输入: 1->2->3->4->5->NULL
 *      输出: 5->4->3->2->1->NULL
 */
public class ReverseList {
    /**
     * 栈：用栈先按照原先顺序存储，再按照倒序弹出栈
     * @author PAN
     * @param head 链表头节点
     * @return 反转后链表头
     * @time O(N)
     * @space O(N)
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        Stack s = new Stack();
        while (head != null) {
            s.push(head);
            head = head.next;
        }
        ListNode newHead = (ListNode) s.pop();
        ListNode point = newHead;
        while (!s.isEmpty()) {
            point.next = (ListNode) s.pop();
            point = point.next;
        }
        point.next = null;
        return newHead;
    }

    /**
     * 数组：用数组按原先顺序存储，再按倒序反向操作
     * @author PAN
     * @param head 链表头节点
     * @return 反转后链表头
     * @time O(N)
     * @space O(N)
     */
    public ListNode reverseList2(ListNode head){
        if (head == null) return null;
        ListNode[] array = new ListNode[5000];
        int i = 0, len = 0;
        while (head != null) {
            array[i] = head;
            i++;
            len++;
            head = head.next;
        }
        for (i = len - 1; i > 0; i--) {
            array[i].next = array[i - 1];
        }
        array[0].next = null;
        return array[len - 1];
    }

    /**
     * 双指针：用前后相差一位的指针反复移动来实现反转
     * @author PAN
     * @param head 链表头节点
     * @return 反转后链表头
     * @time O(N)
     * @space O(1)
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null) return null;
        ListNode former = head, latter = head.next, tmp;
        if (latter == null) return head;
        former.next = null;
        while (latter != null) {
            tmp = latter.next;
            latter.next = former;
            former = latter;
            latter = tmp;
        }
        return former;
    }

    /**
     * 双指针精简版：将两个指针的初始值设为 null, head 代替自己的 head, head.next 以省略一些多余的判断
     * @author 网友
     * @param head 链表头节点
     * @return 反转后链表头
     * @time O(N)
     * @space O(1)
     */
    public ListNode reverseList4(ListNode head) {
        ListNode cur = head, pre = null;
        while(cur != null) {
            ListNode tmp = cur.next; // 暂存后继节点 cur.next
            cur.next = pre;          // 修改 next 引用指向
            pre = cur;               // pre 暂存 cur
            cur = tmp;               // cur 访问下一节点
        }
        return pre;
    }

    /**
     * 递归：考虑使用递归法遍历链表，当越过尾节点后终止递归，在回溯时修改各节点的 next 引用指向。
     * @author 网友
     * @param head 链表头节点
     * @return 反转后链表头
     * @time O(N)
     * @space O(N)
     */
    public ListNode reverseList5(ListNode head) {
        return recur(head, null);    // 调用递归并返回
    }
    private ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null) return pre; // 终止条件
        ListNode res = recur(cur.next, cur);  // 递归后继节点
        cur.next = pre;              // 修改节点引用指向
        return res;                  // 返回反转链表的头节点
    }

}
