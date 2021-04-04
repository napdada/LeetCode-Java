package exam.meituan;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 美团笔试第三题：给定多个数和一个数 k，这多个数的因子组成的字符串中含有 k 的个数（AC）
 */
public class Exam3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), k = input.nextInt(), sum = 0;
        int[] array = new int[n];
        StringBuilder s;
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
            s = new StringBuilder();
            ArrayList<Integer> list = new ArrayList<>();
            int max = array[i], m = 1;
            for (int j = 1; j < max; j++) {
                if (array[i] % j == 0) {
                    list.add(m - 1, j);
                    max = array[i] / j;
                    if (max != j) {
                        list.add(m, max);
                    }
                    m++;
                }
            }
            for (int x = 0; x < list.size(); x++) {
                s.append(list.get(x));
            }
            if (s.toString().contains(String.valueOf(k))) sum++;
        }
        System.out.println(sum);
    }
}
