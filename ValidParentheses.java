import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid(s));
        //true
        String s1 = "()[]{}";
        System.out.println(isValid(s1));
        //true
        String s2 = "(]";
        System.out.println(isValid(s2));
        //false
        String s3 = "([)]";
        System.out.println(isValid(s3));
        //false
        String s4 = "{[]}";
        System.out.println(isValid(s4));
        //true
        String s5 = "";
        System.out.println(isValid(s5));
        //true
    }

    private static boolean isValid(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            }

            if (map.get(stack.peek()) == c && !stack.isEmpty()) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
