package exam.guanglianda;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 广联达笔试第一题：若干牌，A、B 分别选一个数字，A 先拿走所选数字的所有牌，B 再拿走所选数字的所有牌，输出两人是否能拿走所有牌且拿的牌数相等（91%）
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        if (t <= 0) System.out.println("NO");
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
//            if (n % 2 != 0) {
//                System.out.println("NO");
//                return;
//            }
            int[] a = new int[n];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                a[j] = input.nextInt();
                if (!map.containsKey(a[j]))
                    map.put(a[j], 1);
                else map.put(a[j], map.get(a[j]) + 1);
            }
            if (map.size() != 2) System.out.println("NO");
            else {
                Object[] b = map.keySet().toArray();
                int x = map.get(b[0]), y = map.get(b[1]);
                if (x == y) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
