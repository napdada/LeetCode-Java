package exam.bytedance;

import java.util.HashSet;

/**
 * 字节测开一面：最长不重复字符子串
 */
public class Exam2 {
    public static void main(String[] args) {
        String str = "bacdefabch";
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0, max = 0, maxIndex = 0;
        for (; i < str.length() && j < str.length(); j++) {
            if (set.contains(str.charAt(j))) {
                if (max < j - 1) {
                    max = j - 1;
                    maxIndex = j;
                }
                while (i < j) {
                    if (str.charAt(i) == str.charAt(j)) {
                        i++;
                        break;
                    } else {
                        set.remove(str.charAt(i));
                    }
                    i++;
                }
            } else {
                set.add(str.charAt(j));
            }
        }
        System.out.println("max = " + max);
        System.out.println("maxIndex = " + maxIndex);
    }
}
