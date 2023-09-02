import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_1662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String readLine = br.readLine();
        Stack<String> stack = new Stack<>();
        for (String s : readLine.split("")) {
            if (s.equals(")")) {
                unzipString(stack);
            } else {
                stack.add(s);
            }
        }

        int count = 0;
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            count += calculateStringSize(pop);
        }

        System.out.println(count);
    }

    private static void unzipString(Stack<String> stack) {
        int count = 0;
        while (true) {
            String pop = stack.pop();
            if (pop.equals("(")) {
                break;
            } else {
                count += calculateStringSize(pop);
            }
        }

        count *= Integer.parseInt(stack.pop());
        stack.add("n" + count);
    }

    private static int calculateStringSize(String pop) {
        return pop.startsWith("n") ? Integer.parseInt(pop.substring(1)) : 1;
    }
}
