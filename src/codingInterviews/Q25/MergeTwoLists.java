package codingInterviews.Q25;

/**
 * Q25. 合并两个排序的链表
 * 【题目】
 *      输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 【示例】
 *       输入：1->2->4, 1->3->4
 *      输出：1->1->2->3->4->4
 */
public class MergeTwoLists {

    /**
     * 遍历比较合并：遍历逐个比较大小，将小的加入到新建的头节点后面，大的继续比较
     * @author PAN
     * @param l1 非递减链表 1
     * @param l2 非递减链表 2
     * @return 非递减合并链表
     * @time O(M + N)
     * @space O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode newHead = l1.val < l2.val ? l1 : l2, point = newHead;
        if (l1.val < l2.val) l1 = l1.next;
        else l2 = l2.next;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                point.next = l1;
                point = point.next;
                l1 = l1.next;
            } else {
                point.next = l2;
                point = point.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) point.next = l2;
        if (l2 == null) point.next = l1;
        return newHead;
    }

    /**
     * 递归
     * @author 网友
     * @param l1 非递减链表 1
     * @param l2 非递减链表 2
     * @return 非递减合并链表
     * @time O(M + N)
     * @space O(1)
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
