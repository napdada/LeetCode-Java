package exam.guanglianda;

import java.util.Scanner;

/**
 * 广联达笔试第二题：给一个字符串矩阵，解密字符串，规则是从左上角开始从内向外绕，字符串最后的 0 为补位不输出（AC）
 */
public class Exam2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Character[][] c = new Character[n][n];
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = input.next();
            for (int j = 0; j < n; j++)
                c[i][j] = s[i].charAt(j);
        }
        int x = 0, y = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0, flag1 = 0, flag2 = n;
        while (i < n * n) {
            while (y < flag2) {
                stringBuilder.append(c[x][y]);
                y++;
                i++;
            }
            if (i >= n * n) break;
            y--;
            x++;
            while (x < flag2) {
                stringBuilder.append(c[x][y]);
                x++;
                i++;
            }
            if (i >= n * n) break;
            x--;
            y--;
            while (y >= flag1) {
                stringBuilder.append(c[x][y]);
                y--;
                i++;
            }
            if (i >= n * n) break;
            y++;
            x--;
            while (x > flag1) {
                stringBuilder.append(c[x][y]);
                x--;
                i++;
            }
            x++;
            y++;
            flag1++;
            flag2--;
        }
        for (int j = stringBuilder.length() - 1; j >= 0; j--) {
            if (stringBuilder.charAt(j) == '0') stringBuilder.deleteCharAt(j);
            else break;
        }
        System.out.println(stringBuilder);
    }
}
