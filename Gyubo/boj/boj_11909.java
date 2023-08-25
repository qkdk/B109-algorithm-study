import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_11909 {

    static class Vertex {

        int y, x;

        public Vertex(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static final int[][] vector = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] table = new int[n][n];
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        distance[0][0] = 0;
        Deque<Vertex> q = new ArrayDeque<>();
        q.add(new Vertex(0, 0));

        while (!q.isEmpty()) {

            Vertex cur = q.poll();

            for (int d = 0; d < vector.length; d++) {
                int ny = cur.y + vector[d][0];
                int nx = cur.x + vector[d][1];

                if (ny < 0 || nx < 0 || ny >= table.length || nx >= table[0].length) {
                    continue;
                }

                // 그냥 바로 탐색할수 있는 경우
                if (table[ny][nx] < table[cur.y][cur.x]) {
                    if (distance[cur.y][cur.x] < distance[ny][nx]) {
                        distance[ny][nx] = distance[cur.y][cur.x];
                        q.add(new Vertex(ny, nx));
                    }
                }

                // 그냥 바로 탐색할수 없는 경우
                else {
                    // 거리 계산
                    int plusDist = table[ny][nx] - table[cur.y][cur.x] + 1;
                    if (distance[ny][nx] > distance[cur.y][cur.x] + plusDist) {
                        distance[ny][nx] = distance[cur.y][cur.x] + plusDist;
                        q.add(new Vertex(ny, nx));
                    }
                }
            }
        }
        System.out.println(distance[n - 1][n - 1]);
    }
}
