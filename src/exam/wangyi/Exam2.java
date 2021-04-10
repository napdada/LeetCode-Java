package exam.wangyi;

import java.math.BigInteger;

/**
 * 网易笔试第二题：链表数字相减（AC）
 */
public class Exam2 {
    public static void main(String[] args) {
        new Exam2().minusList(null, null);
    }

    // 利用 StringBuilder 读入整个链表，再构造 BigInteger 相减（用普通 int、long 估计会溢出），最后再构造新链表（对头节点符号判断一下）
    public ListNode minusList (ListNode minuendList, ListNode subtrahendList) {
//        StringBuilder s1 = new StringBuilder("-1234546645"), s2 = new StringBuilder("-2654657");  // test
        ListNode point1 = minuendList, point2 = subtrahendList;
        StringBuilder s1 = new StringBuilder(), s2 = new StringBuilder();
        while (point1 != null) {
            s1.append(point1.val);
            point1 = point1.next;
        }
        while (point2 != null) {
            s2.append(point2.val);
            point2 = point2.next;
        }
        BigInteger num1 = new BigInteger(String.valueOf(s1));
        BigInteger num2 = new BigInteger(String.valueOf(s2));
        BigInteger num3 = num1.add(num2.multiply(BigInteger.valueOf(-1)));  // num3 = num1 - num2
        StringBuilder s3 = new StringBuilder(String.valueOf(num3));
        boolean flag = false;
        if (s3.charAt(0) == '-') { // 先删除负号便于构造链表
            s3.deleteCharAt(0);
            flag = true;
        }
        ListNode[] nodes = new ListNode[s3.length()];
        for (int i = 0; i < s3.length(); i++) {
            nodes[i] = new ListNode(s3.charAt(i) - '0');
        }
        nodes[0].val = flag ? (s3.charAt(0) - '0')* -1 : nodes[0].val;  // 重新给头节点添加负号
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[nodes.length - 1].next = null;
        return nodes[0];
    }
}
