package exam.meituan;

/**
 * 美团测开一面：一个句子，每个单词反转输出（AC）
 */
public class Exam6 {

    public String reverseStringI (String str) {
        // write code here
        String[] s = str.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            for (int j = s[i].length() - 1; j >= 0; j--) {
                res.append(s[i].charAt(j));
            }
            res.append(" ");
        }
        res.delete(res.length() - 1, res.length());
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Exam6().reverseStringI("i am a student"));
    }
}
