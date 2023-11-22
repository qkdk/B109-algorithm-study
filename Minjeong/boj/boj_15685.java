import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15685 {
    private static final boolean[][] map = new boolean[101][101];
    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (n-- > 0) {
            int a, b, c, d;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            draw(a, b, c, d);
        }

        System.out.println(count());
    }

    private static void draw(int a, int b, int c, int d) {
        List<Integer> directions = getDirection(c, d);
        map[b][a] = true;
        for (int dir: directions) {
            if (dir == RIGHT) map[b][++a] = true;
            else if (dir == UP) map[--b][a] = true;
            else if (dir == LEFT) map[b][--a] = true;
            else if (dir == DOWN) map[++b][a] = true;
        }
    }

    private static List<Integer> getDirection(int c, int d) {
        List<Integer> directions = new ArrayList<>();
        directions.add(c);
        for (int i = 1; i <= d; i++) {
            int idx = directions.size();
            while (idx-- > 0) {
                int dir = directions.get(idx);
                directions.add(dir + 1 < 4 ? dir + 1 : 0);
            }
        }
        return directions;
    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) cnt++;
            }
        }
        return cnt;
    }
}
