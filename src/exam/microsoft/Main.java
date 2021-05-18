package exam.microsoft;

import java.util.Scanner;

/**
 * 微软二面
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = {5, 7, 7, 8, 10};
        int target = 8;

        int beginIndex = -1, endIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (beginIndex == -1) beginIndex = i;
                endIndex = i;
            }
        }

        System.out.println("beginIndex = " + beginIndex);
        System.out.println("endIndex = " + endIndex);
    }
}
