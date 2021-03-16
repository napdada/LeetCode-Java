package codingInterviews.Q22;

/**
 * Q22. 链表中倒数第k个节点
 * 【题目】
 * ​	    输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * ​	    例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * 【示例】
 *      给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 *      返回链表 4->5.
 */
public class GetKthFromEnd {

    /**
     * 先求链表长度再找：先遍历一边链表求出长度 len，在利用 len - k 去找该倒数 节点
     * @author PAN
     * @param head 链表头节点
     * @param k 倒数第 k
     * @return 链表倒数第 k 个节点
     * @time O(N)
     * @space O(1)
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        int len = 0;
        ListNode point = head;
        while (point != null) {
            len++;
            point = point.next;
        }
        if (k > len) return null; // 防止 k > len
        int i = 0;
        point = head;
        while (i != len - k) {
            point = point.next;
            i++;
        }
        return point;
    }

    /**
     * 双指针：利用快慢指针，先使得 latter = former + k，再两个指针同步后移，当 latter 为空时 former 即为要找的节点
     * @author 网友 & PAN，参考思路自己实现
     * @param head 链表头节点
     * @param k 倒数第 k
     * @return 链表倒数第 k 个节点
     * @time O(N)
     * @space O(1)
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode former = head, latter = head;
        for (int i = 0; i < k; i++) {
            if (latter == null) return null; // 防止 k > len
            latter = latter.next;
        }
        while (latter != null) {
            former = former.next;
            latter = latter.next;
        }
        return former;
    }
}
