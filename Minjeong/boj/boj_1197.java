import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1197 {
    private static class Node implements Comparable<Node> {
        private int node;
        private int weight;

        private Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int v, e;
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        List<Node>[] graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int sum = 0, start = 1;
        boolean[] visit = new boolean[v + 1];
        visit[start] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.addAll(graph[start]);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visit[cur.node]) continue;
            visit[cur.node] = true;
            sum += cur.weight;
            pq.addAll(graph[cur.node]);
        }

        System.out.println(sum);
    }
}
