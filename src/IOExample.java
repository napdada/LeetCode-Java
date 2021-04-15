import java.util.Scanner;

public class IOExample {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        String[] s = in.split(" ");
        for (int i = s.length - 1; i >= 0; i--)
            System.out.print(s[i] + " ");
        System.out.println();
    }
}
