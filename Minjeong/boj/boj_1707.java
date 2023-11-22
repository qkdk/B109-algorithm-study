import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1707 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();
        while (k-- > 0) {
            int v, e;
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            List<Integer>[] graph = new List[v + 1];
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; i++) {
                int a, b;
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            int[] group = new int[v + 1];
            Queue<Integer> q = new ArrayDeque<>();
            int sum = 0;
            boolean flag = true;
            for (int i = 1; i <= v; i++) {
                if (group[i] != 0) continue;
                q.add(i);
                group[i] = 1;
                l: while (!q.isEmpty()) {
                    int cur = q.poll();
                    sum++;
                    for (int next: graph[cur]) {
                        if (group[next] == 0) {
                            q.add(next);
                            group[next] = group[cur] ^ 3;
                        }
                        if (group[next] == group[cur]) {
                            flag = false;
                            break l;
                        }
                    }
                }
            }
            sb.append(flag && sum == v ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }
}
