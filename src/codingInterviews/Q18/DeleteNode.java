package codingInterviews.Q18;

/**
 * Q18. 删除链表的节点
 * 【题目】
 *      给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
 * 【示例】
 *      输入: head = [4,5,1,9], val = 5
 *      输出: [4,1,9]
 *      解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 */
public class DeleteNode {
    /**
     * 遍历对比删除：从头节点开始依次比较，找到要删除的节点进行删除，即将上一个节点指向下一个节点。
     * @author PAN
     * @param head 头节点
     * @param val 待删除节点的值
     * @return 头节点
     * @time O(N)
     * @space O(1)
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            head = head.next;
            return head;
        }
        ListNode point = head;
        while (point.next != null) {
            if (point.next.val == val) point.next = point.next.next;
            else point = point.next;
        }
        return head;
    }
}
