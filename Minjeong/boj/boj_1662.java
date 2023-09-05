import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1662 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        Stack<Integer> tmp = new Stack<>();

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ')') {
                stack.push(c);
                if (c == '(' && !tmp.isEmpty()) res += tmp.peek();
            }
            else {
                int len = 0;
                while (stack.peek() != '(') {
                    stack.pop();
                    len++;
                }
                stack.pop();
                int n = stack.pop() - '0';
                int pre = 0;
                if (!tmp.isEmpty()) pre = tmp.pop();
                tmp.push(n * (len + pre));
            }
        }
        if (!tmp.isEmpty()) res += tmp.peek();
        System.out.println(res + stack.size());
    }
}
