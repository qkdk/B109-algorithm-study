import java.io.*;
import java.util.*;

public class boj5014 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());


        bfs(F, S, G, U, D);


    }

    static class Point {
        int now;
        int level;

        Point(int now, int level) {
            this.now = now;
            this.level = level;
        }
    }

    static void bfs(int F, int S, int G, int U, int D) {

        boolean[] floor = new boolean[F + 1];
        floor[0] = true;

        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(S, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.now == G) {
                System.out.println(p.level);
                return;
            }

            if (p.now + U <= F && floor[p.now + U] == false) {
                floor[p.now + U] = true;
                q.offer(new Point(p.now + U, p.level + 1));
            }

            if (p.now - D >= 1 && floor[p.now - D] == false) {
                floor[p.now - D] = true;
                q.offer(new Point(p.now - D, p.level + 1));
            }
        }

        System.out.println("use the stairs");

    }


}
