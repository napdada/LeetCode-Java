package exam.webank;

import java.util.Scanner;
import java.util.Stack;

/**
 * 微众银行笔试第一题：排队决斗，输给后面的人自己退出，输给前面的人继续呆在队伍中（AC）
 */
public class Exam {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = input.nextInt();
        Stack<Integer> stack = new Stack<>();
        stack.push(a[0]);
        for (int i = 1; i < n; i++) {
            if (!stack.isEmpty()) {
                while (!stack.isEmpty() && a[i] > stack.peek()) {
                    stack.pop();
                }
            }
            stack.push(a[i]);
        }
        int len = stack.size();
        System.out.println(len);
        int[] b = new int[len];
        for (int i = len - 1; i >= 0 ; i--)
            b[i] = stack.pop();
        int i = 0;
        for ( ; i < len - 1; i++)
            System.out.print(b[i] + " ");
        System.out.println(b[i]);
    }
}
