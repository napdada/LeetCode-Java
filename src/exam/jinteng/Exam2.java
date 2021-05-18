package exam.jinteng;

import java.util.Scanner;

/**
 * 金腾科技二面：从有序数组中找某数
 */
public class Exam2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] arr = {1, 2, 3, 4, 7, 8};
        int x = 5;
        int i = 0, j = arr.length, res = -1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (arr[mid] > x) {
                j = mid - 1;
            } else if (arr[mid] < x){
                i = mid + 1;
            } else {
                res = mid;
                break;
            }
        }
        System.out.println(res);
    }
}
