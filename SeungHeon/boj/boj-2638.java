import java.io.*;
import java.util.*;

public class Main {

    static int map[][];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 나머지 줄
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // print();

        int answer = 0;
        while (true) {
            answer++;
            int count = 0;

            // 바깥쪽 공기만 체크
            bfsZero(0, 0);

            // 한번도 검사하지 않은 치즈에 대해서 이어진 치즈 검사
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        count++;
                        bfs(i, j);
                    }
                }
            }

            // map을 0과 1로만 다시 표현
            if (count != 0) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (map[i][j] == -1) {
                            map[i][j] = 0;
                        } else if (map[i][j] == 2) {
                            map[i][j] = 1;
                        } else if (map[i][j] == -9) {
                            map[i][j] = 0;
                        }
                    }
                }
            } else {
                answer--;
                break;
            }
        }

        System.out.println(answer);
    }


    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void bfsZero(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));
        map[i][j] = -9;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int d = 0; d < dr.length; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nr < map.length && nc >= 0 && nc < map[0].length && map[nr][nc] == 0) {
                    map[nr][nc] = -9;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }


    static void bfs(int i, int j) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(i, j));
        map[i][j] = 2;

        while (!q.isEmpty()) {
            Point p = q.poll();

            int zeroCount = 0;
            for (int d = 0; d < dr.length; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (map[nr][nc] == -9) {
                    zeroCount++;
                }
                if (map[nr][nc] == 1) {
                    q.offer(new Point(nr, nc));
                    map[nr][nc] = 2;
                }
            }
            if (zeroCount >= 2) {
                map[p.r][p.c] = -1;
            }
        }
    }


    static void print() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
}