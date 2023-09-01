import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class boj_1956 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] graph = new int[v + 1][v + 1];
        for (int i = 1; i < v + 1; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 3);
            graph[i][i] = 0;
        }

        for (int i = 0; i < e; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        for (int k = 1; k < v + 1; k++) {
            for (int i = 1; i < v + 1; i++) {
                if (i == k) continue;
                for (int j = 1; j < v + 1; j++) {
                    if (k == j) continue;
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int minVal = Integer.MAX_VALUE / 3;
        for (int i = 1; i < v + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                if (i == j) continue;
                minVal = Math.min(minVal, graph[i][j] + graph[j][i]);
            }
        }

        if (minVal == Integer.MAX_VALUE / 3) System.out.println(-1);
        else System.out.println(minVal);
    }
}