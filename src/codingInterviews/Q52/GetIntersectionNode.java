package codingInterviews.Q52;

import java.util.HashSet;

/**
 * Q52. 两个链表的第一个公共节点
 * 【题目】
 *      输入两个链表，找出它们的第一个公共节点。
 * 【示例】
 *      输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 *      输出：Reference of the node with value = 8
 *      输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点
 */
public class GetIntersectionNode {
    public static void main(String[] args) {
        GetIntersectionNode test = new GetIntersectionNode();
        ListNode node1 = new ListNode(3), node2 = new ListNode(2);
        node2.next = node1;
        test.getIntersectionNode(node1, node2);
    }

    /**
     * HashSet 记录走过的节点，找出第一个重复走的
     * @author PAN
     * @param headA 链表1
     * @param headB 链表2
     * @return 第一个公共节点
     * @time O(N)
     * @space O(N)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode point1 = headA, point2 = headB;
        while (point1 != null || point2 != null) {
            if (point1 != null && !set.contains(point1)) {
                set.add(point1);
                point1 = point1.next;
            } else if (point1 != null) return point1;
            if (point2 != null && !set.contains(point2)) {
                set.add(point2);
                point2 = point2.next;
            } else if (point2 != null) return point2;
        }
        return null;
    }

    /**
     * 双指针：因为 a + (b - c) = b + (a - c)，a：链表 1 节点数、b：链表 2 节点数、c：公共节点数
     *       因此指针 1 遍历完链表 1 后指向链表 2 头节点，指针 2 遍历完链表 2 后指向链表 1 头节点，最后两个指针会在第一个公共节点相遇
     * @author 网友
     * @param headA 链表1
     * @param headB 链表2
     * @return 第一个公共节点
     * @time O(N)
     * @space O(1)
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }
}
