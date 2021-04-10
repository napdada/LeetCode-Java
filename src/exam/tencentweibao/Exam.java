package exam.tencentweibao;

import java.util.Stack;

/**
 * 腾讯微保笔试第一题：括号的匹配（AC）
 */
public class Exam {
    public static void main(String[] args) {
        String s = "{";
        System.out.println(new Exam().isValid(s));
    }

    public boolean isValid (String s) {
        if (s.length() == 1) return false;
        Stack<Character> stack = new Stack<>();
        if (s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']')
            return false;
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                stack.push(s.charAt(i));
            else {
                if (stack.peek() == '(') {
                    if (s.charAt(i) == ')') stack.pop();
                    else return false;
                } else if (stack.peek() == '{') {
                    if (s.charAt(i) == '}') stack.pop();
                    else return false;
                } else if (stack.peek() == '[') {
                    if (s.charAt(i) == ']') stack.pop();
                    else return false;
                }

            }
        }
        return true;
    }


}
