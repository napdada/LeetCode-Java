package exam.beike;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 贝壳找房笔试第一题：记录仓库中商品名、卖出一份收益、库存数；按照顾客下单顺序处理，计算盈利，若某单超过库存终止订单发出提示（AC）
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt();   // n：商品种数，m：订单数
        String[] name = new String[n];
        int[] money = new int[n], maxNum = new int[n];
        HashMap<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            name[i] = input.next();
            money[i] = input.nextInt();
            maxNum[i] = input.nextInt();
            map1.put(name[i], maxNum[i]);
            map2.put(name[i], money[i]);
        }
        String[] orderName = new String[m];
        int[] orderNum = new int[m];
        for (int i = 0; i < m; i++) {
            orderName[i] = input.next();
            orderNum[i] = input.nextInt();
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            if (!map1.containsKey(orderName[i]) || map1.get(orderName[i]) < orderNum[i]) {
                System.out.println(-i - 1);
                return;
            } else {
                res += map2.get(orderName[i]) * orderNum[i];
                map1.put(orderName[i], map1.get(orderName[i]) - orderNum[i]);
            }
        }
        System.out.println(res);
    }
}
