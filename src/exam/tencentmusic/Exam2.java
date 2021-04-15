package exam.tencentmusic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 腾讯音乐笔试第二题：输入两个数组，第一个数组为任意数，第二个数组为这些数自加一的代价，求最小的代价使得第一个数组所有数互异（5%）
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n], b = new int[n];
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
            a[i] = input.nextInt();
        for (int i = 0; i < n; i++)
            b[i] = input.nextInt();
        list.add(a[0]);
        ArrayList<Integer> tmp = new ArrayList<>();
        tmp.add(b[0]);
        lists.add(tmp);
        for (int i = 1; i < n; i++) {
            int j = 0;
            while (j < list.size() && a[i] > list.get(j))
                j++;
            if (j == list.size()) {
                list.add(a[i]);
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(b[i]);
                lists.add(j, temp);
            } else {
                if (a[i] == list.get(j)) {
                    int k = 0;
                    while (k < lists.get(j).size() && b[i] < lists.get(j).get(k))
                        k++;
                    if (k == lists.get(j).size()) lists.get(j).add(b[i]);
                    else lists.get(j).add(k, b[i]);
                } else {
                    list.add(j, a[i]);
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(b[i]);
                    lists.add(j, temp);
                }
            }
        }

        if (list.size() == n) System.out.println(0);
        else {
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                while (lists.get(i).size() > 1) {
                    int temp = lists.get(i).remove(0);
                    sum += temp;
                    if (list.get(i) + 1 == list.get(i + 1)) {
                        int j = 0;
                        while (j < list.size() && temp > lists.get(i + 1).get(j)) {
                            j++;
                        }
                        if (j == list.size()) lists.get(i + 1).add(temp);
                        else lists.get(i + 1).add(j, temp);
                    } {

                    }

                }
            }
        }

    }
}
