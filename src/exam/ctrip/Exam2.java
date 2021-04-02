package exam.ctrip;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 携程笔试第二题：输出满足条件的组合的最低花费（NAC）
 */
public class Exam2 {
    public static void main(String[] args) {
//        int n = 3;
//        String[] packages = {"1,2", "2,3", "1,3"};
//        int[] price = {3, 4, 2};
//        int[] value = {1, 2, 3};
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String s = input.nextLine();
        String[] packages = s.split(" ");
        int[] price = new int[packages.length];
        int[] value = new int[packages.length];
        for (int i = 0; i < packages.length; i++){
            price[i] = input.nextInt();
        }
        for (int i = 0; i < packages.length; i++){
            value[i] = input.nextInt();
        }

        int[][] packageArray = new int[packages.length][];
        for (int i = 0; i < packages.length; i++) {
            String[] tmp = packages[i].split(",");
            packageArray[i] = new int[tmp.length];
            for (int j = 0; j < tmp.length; j++) {
                packageArray[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < packageArray.length; i++) {
            for (int j = 0; j < packageArray[i].length; j++) {
                hashMap.put(packageArray[i][j], 1);
            }
        }
        if (hashMap.size() < value.length) System.out.println("-1");
//        System.out.println(Arrays.toString(packageArray[0]));
//        int minNum = 1;
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        for (int i = 0; i < price.length; i++) {
//            hashMap.put(price[i], i);
//        }
//        Arrays.sort(price);
//        System.out.println(Arrays.toString(price));
//        for (int i = 1; i <= n; i++) {
//            int min = 0;
//            for (int j = 0; j < i; j++){
//                min += price[i];
//            }
//
//        }
    }
}
