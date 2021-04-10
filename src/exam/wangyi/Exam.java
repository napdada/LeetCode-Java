package exam.wangyi;

import java.util.HashMap;

/**
 * 网易笔试第一题：卖饼。卖家初始有两张 5 元，买家排队买（付钱只有 5、10、20），一旦没钱找就不做生意，最多卖几个饼
 */
public class Exam {
    public static void main(String[] args) {
        int[] bills = {5,10,10,10,20};
        int[] bills2 = {5,10,20};
        int[] bills3 = {10,20};
        int[] bills4 = {5,10,20};
        int[] bills5 = {20,10,10,10,5};
        System.out.println(new Exam().billsChange(bills));
        System.out.println(new Exam().billsChange(bills2));
        System.out.println(new Exam().billsChange(bills3));
        System.out.println(new Exam().billsChange(bills4));
        System.out.println(new Exam().billsChange(bills5));

    }

    // 三种情况：5 元的直接买，10 元的必须卖家有 5 元，20 元的必须卖家有 5 + 10 或 5 + 5 + 5
    public int billsChange (int[] bills) {
        if (bills.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(5, 2);
        int num = 0;
        for (int bill : bills) {
            if (bill == 5) {
                map.put(5, map.get(5) + 1);
                num++;
            } else if (bill == 10) {
                if (map.get(5) <= 0) {
                    break;
                } else {
                    if (map.containsKey(10)) map.put(10, map.get(10) + 1);
                    else map.put(10, 1);
                    map.put(5, map.get(5) - 1);
                    num++;
                }
            } else if (bill == 20) {
                if (!map.containsKey(10) || map.get(10) <= 0) {
                    if (map.get(5) >= 3) {
                        if (map.containsKey(20)) map.put(20, map.get(20) + 1);
                        else map.put(20, 1);
                        map.put(5, map.get(5) - 3);
                        num++;
                    } else break;
                } else if (map.get(5) > 0 && map.get(10) > 0) {
                    if (map.containsKey(20)) map.put(20, map.get(20) + 1);
                    else map.put(20, 1);
                    map.put(5, map.get(5) - 1);
                    map.put(10, map.get(10) - 1);
                    num++;
                } else break;
            } else break;
        }
        return num;
    }
}
