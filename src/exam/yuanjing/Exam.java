package exam.yuanjing;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 远景笔试第一题：给定粉丝见的关系，计算某个人所有直接粉丝和间接粉丝数（AC）
 */
public class Exam {

    public static void main(String[] args) {
//        int N = 4;
//        String u = "ada";
//        String[][] people = {{"ada", "bryan"}, {"ada", "john"}, {"ada", "rosie"}, {"bryan", "yang"}};

        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        String u = input.next();
        String[][] people = new String[N][2];
        int num1 = 0, num2 = 0;
        boolean[] flag = new boolean[N];        // 记录使用过的关系
        HashSet<String> set = new HashSet<>();  // 存储某个人的直接、间接粉丝
        for (int i = 0; i < N; i++) {
            people[i][0] = input.next();
            people[i][1] = input.next();
            if (people[i][0].equals(u) && !people[i][1].equals(u)) {
                num1++;                 // 直接关注
                set.add(people[i][1]);  // 直接粉丝合集
                flag[i] = true;
            }
        }

        // 遍历剩余关注关系，将间接粉丝再加入 Set，直到 Set 大小不再变化退出循环
        int oldSize = set.size(), newSize = 0;
        while (oldSize != newSize) {
            oldSize = set.size();
            for (int i = 0; i < N; i++) {
                if (!flag[i] && set.contains(people[i][0]) && !people[i][1].equals(u)) {    // 该关系没被使用过、Set 含有被关注的人、间接粉丝不是本人
                    if (!set.contains(people[i][1])) {  // 间接粉丝不在 Set 中将其加入 Set
                        set.add(people[i][1]);
                        num2++;     // 间接关注
                    }
                }
            }
            newSize = set.size();
        }
        System.out.println(num1 + num2);
    }
}
