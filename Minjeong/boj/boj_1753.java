import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1753 {
    private static class Node implements Comparable<Node> {
        private int node;
        private int cost;

        private Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v, e, start;
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        List<Node>[] dist = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            dist[i] = new ArrayList<>();
        }
        int[] res = new int[v + 1];
        boolean[] visit = new boolean[v + 1];
        Arrays.fill(res, Integer.MAX_VALUE);

        for (int i = 0; i < e; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            dist[a].add(new Node(b, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        res[start] = 0;
        pq.add(new Node(start, res[start]));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > res[cur.node]) continue;
            visit[start] = true;

            for (Node next: dist[cur.node]) {
                if (!visit[next.node] && res[next.node] > res[cur.node] + next.cost) {
                    res[next.node] = res[cur.node] + next.cost;
                    pq.add(new Node(next.node, res[next.node]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if (res[i] != Integer.MAX_VALUE) sb.append(res[i]).append('\n');
            else sb.append("INF\n");
        }
        System.out.println(sb);
    }
}
