package exam.ctrip;

import java.util.Scanner;

/**
 * 携程笔试第一题：输出 SQLite 语句中的表名（NAC）
 */
public class Exam {

    public static void main(String[] args) {

        String s = "";
        Scanner input = new Scanner(System.in);
        while (!input.nextLine().isEmpty()) {
            s += input.nextLine();
            input.nextLine();
            if (s == null) break;
        }
        String[] word = s.split(" ");

        for (int i = 0; i < word.length - 1; i++) {
            boolean flag1 = word[i].equals("from") || word[i].equals("join")
                    || word[i].equals("update") || word[i].equals("delete")
                    || word[i].equals("into");
            if (flag1 && !word[i + 1].equals("(")) System.out.println(word[i + 1]);
        }

//        String s = "select t.id, t.name, t.tag_id\n" +
//                "from (\n" +
//                "    select user.id, user.name, tag.tag_id\n" +
//                "    from user\n" +
//                "    inner join user_tag\n" +
//                ") t";
//        String[] line = s.split("\n");
//        String[] word = s.split("\n");
//        for (int i = 0; i < line.length; i++) {
//            line[i].split(" ");
//        }
//        Scanner input = new Scanner(System.in);
//        StringBuilder str = new StringBuilder();
//        String tmp = null;
//        while (true) {
//            str.append(tmp);
//            str.append(input.nextLine());
//            System.out.println("cnm");
//            // System.out.println(Arrays.toString(input.nextLine().split(" ")));
//            tmp = input.nextLine();
//            if (tmp == null) break;
//        }
//        System.out.println("str = " + str);
//        for (int i = 0; i < word.length; i++) {
//            System.out.println(word[i]);
//        }
//
//        System.out.println("---------------");
//
//        for (int i = 0; i < word.length - 1; i++) {
//            boolean flag1 = word[i].equals("from") || word[i].equals("join")
//                    || word[i].equals("update") || word[i].equals("delete")
//                    || word[i].equals("into");
//            if (flag1 && !word[i + 1].equals("(")) System.out.println(word[i + 1]);
//        }
    }
}
