import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17265 {

    private static final int[][] vector = {{0, 1}, {1, 0}};
    private static int minValue = Integer.MAX_VALUE;
    private static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        char[][] table = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                table[i][j] = st.nextToken().charAt(0);
            }
        }

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        dfs(table, visited, 0, 0, String.valueOf(table[0][0]));
        System.out.println(maxValue + " " + minValue);

    }

    private static void dfs(char [][] table, boolean[][] visited, int y, int x, String calculateString) {
        if (y == table.length - 1 && x == table[0].length - 1) {
            int value = calculate(calculateString);
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
            return;
        }

        for (int d = 0; d < vector.length; d++) {
            int ny = y + vector[d][0];
            int nx = x + vector[d][1];

            if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                continue;
            }
            if (visited[ny][nx]) {
                continue;
            }

            visited[ny][nx] = true;
            dfs(table, visited, ny, nx, calculateString + table[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    private static int calculate(String calculateString) {
        int value = Character.getNumericValue(calculateString.charAt(0));
        for (int i = 1; i < calculateString.length(); i += 2) {
            int nextValue = Character.getNumericValue(calculateString.charAt(i + 1));
            if (calculateString.charAt(i) == '+') {
                value += nextValue;
            } else if (calculateString.charAt(i) == '-') {
                value -= nextValue;
            } else if (calculateString.charAt(i) == '*') {
                value *= nextValue;
            }
        }
        return value;
    }
}
