package codingInterviews.Q35;

import java.util.HashMap;

/**
 * Q35. 复杂链表的复制
 * 【题目】
 *      请实现 copyRandomList 函数，复制一个复杂链表。
 *      在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 * 【示例】
 *      输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *      输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *      输入：head = [[1,1],[2,1]]
 *      输出：[[1,1],[2,1]]
 *      输入：head = [[3,null],[3,0],[3,null]]
 *      输出：[[3,null],[3,0],[3,null]]
 *      输入：head = []
 *      输出：[]
 *      解释：给定的链表为空（空指针），因此返回 null。
 */
public class CopyRandomList {

    /**
     * 遍历：先利用 next 形成链表，再找到 random 进行复制
     * @author PAN
     * @param head 待复制的链表头节点
     * @return 复制的新链表头节点（【注意】是深拷贝）
     * @time O(N ^ 2)
     * @space O(1)
     */
    public Node copyRandomList(Node head) {

        if (head == null) return null;

        Node newHead = new Node(head.val), oldPoint = head, newPoint = newHead;
        // 先把新链表利用 next 链接在一起（深拷贝）
        while (oldPoint.next != null) {
            newPoint.next = new Node(oldPoint.next.val);
            oldPoint = oldPoint.next;
            newPoint = newPoint.next;
        }

        // 再补充每个节点的 random
        oldPoint = head;
        newPoint = newHead;
        while (oldPoint != null) {
            if (oldPoint.random == null) {
                newPoint.random = null;
            } else {
                Node tmp1 = head, tmp2 = newHead;
                while (tmp1 != oldPoint.random) {   // 走完整个队列找到 random 的位置
                    tmp1 = tmp1.next;
                    tmp2 = tmp2.next;
                }
                newPoint.random = tmp2;
            }
            oldPoint = oldPoint.next;
            newPoint = newPoint.next;
        }

        return newHead;
    }

    /**
     * 哈希表：
     * 1. 构建 原链表节点 和 新链表对应节点的 key, value 键值对；
     * 2. 再遍历构建新链表各节点的 next 和 random
     * @author 网友 & PAN，学习网友思路自己实现
     * @param head 原链表节点
     * @return 新链表节点
     * @time O(N)
     * @space O(N)
     */
    public Node copyRandomList2(Node head) {

        if (head == null) return null;

        // 构建 原链表节点 和 新链表对应节点 的 key, value 键值对；
        Node point = head;
        HashMap<Node, Node> nodeMap = new HashMap<>();
        while (point != null) {
            nodeMap.put(point, new Node(point.val));
            point = point.next;
        }

        // 再遍历构建新链表各节点的 next 和 random
        point = head;
        while (point != null) {
            nodeMap.get(point).next = nodeMap.get(point.next);
            nodeMap.get(point).random = nodeMap.get(point.random);
            point = point.next;
        }
        return nodeMap.get(head);
    }

    /**
     * 拼接 + 拆分：
     * 1. 在原链表中构建新链表，每一个节点复制一个到其后面；
     * 2. 利用 point.next.random = point.random.next 构建 random；
     * 3. 拆分新旧链表
     * @author 网友 & PAN，学习网友思路自己实现
     * @param head 原链表节点
     * @return 新链表节点
     * @time O(N)
     * @space O(1)
     */
    public Node copyRandomList3(Node head) {

        if (head == null) return null;

        // 先构建链表，将每一个节点复制一个到其后面
        Node point = head, tmp;
        while (point != null) {
            tmp = new Node(point.val);
            tmp.next = point.next;
            point.next = tmp;
            point = tmp.next;
        }

        // 再构建 random
        point = head;
        while (point != null) {
            if (point.random != null)
                point.next.random = point.random.next;
            point = point.next.next;
        }

        // 最后将连在一起的新旧链表分离出来
        point = head;
        Node newHead = point.next, newPoint = newHead;
        while (newPoint.next != null) {
            point.next = point.next.next;
            newPoint.next = newPoint.next.next;
            point = point.next;
            newPoint = newPoint.next;
        }
        point.next = null;
        return newHead;
    }
}
