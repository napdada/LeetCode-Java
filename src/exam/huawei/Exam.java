package exam.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 华为笔试第一题：求交集（NAC）
 */
public class Exam {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        String[] stu = new String[N];
        for (int i = 0; i < N; i++) {
            stu[i] = input.nextLine();
        }
//        int N = 6;
//        String[] stu = {"Jack Tom","Alice John","Jessica Leonie","Tom Alice","John Jack","Leonie Jessica"};
//        int N = 3;
//        String[] stu = {"Xiaoming Xiaohong","Xiaohong Xiaoqiang","Xiaoqiang Xiaoming"};
//        String[][] stus = new String[N][];
        int[] arr = new int[N];
//        for (int i = 0; i < stu.length; i++) {
//            stus[i] = stu[i].split(" ");
//        }
//
//        for (int i = 0; i < stus.length; i++) {
//            if (arr[i] == 1) continue;
//            for (int j = 0; j < stus[i].length; j++) {
//                for (int k = i + 1; k < stus.length; k++) {
//                    if (arr[k] == 0 && (stus[k][0].equals(stus[i][j]) || stus[k][1].equals(stus[i][j]))) {
//                        arr[k] = 1;
//                        if (stus[k][0].equals(stus[i][j]) && !stu[i].contains(stus[k][1]))
//                            stu[i] = stu[i] + " " + stus[k][1];
//                        else if (stus[k][1].equals(stus[i][j]) && !stu[i].contains(stus[k][0]))
//                            stu[i] = stu[i] + " " + stus[k][0];
//                        stus[i] = stu[i].split(" ");
//                    }
//                }
//            }
//        }

        String[][] group = new String[N][];
        for (int i = 0; i < stu.length; i++)
            group[i] = stu[i].split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        map.put(group[0][0], 1);
        map.put(group[0][1], 1);
        int ordSize, newSize = 0, count = N - 1, sum = 1, minIndex = 1;
        while (count != 0) {
            ordSize = map.size();
            for (int i = 1; i < stu.length; i++) {
                if (arr[i] == 1) continue;
                if (map.containsKey(group[i][0]) && !map.containsKey(group[i][1])) {
                    arr[i] = 1;
                    count--;
                    map.put(group[i][1], 1);
                }
                else if (map.containsKey(group[i][1]) && !map.containsKey(group[i][0])) {
                    arr[i] = 1;
                    count--;
                    map.put(group[i][0], 1);
                } else if (map.containsKey(group[i][0]) && map.containsKey(group[i][1])) {
                    arr[i] = 1;
                    count--;
                }
            }
            newSize = map.size();
            if (ordSize == newSize && count != 0) {
                map = new HashMap<>();
                for (int i = 1; i < arr.length; i++)
                    if (arr[i] == 0) {
                        minIndex = i;
                        break;
                    }
                map.put(group[minIndex][0], 1);
                map.put(group[minIndex][1], 1);
                arr[minIndex] = 1;
                count--;
                sum++;
            }
        }


//        int count = 0;
//        for (int value : arr) if (value == 0) count++;
        System.out.println(sum);
    }
}
