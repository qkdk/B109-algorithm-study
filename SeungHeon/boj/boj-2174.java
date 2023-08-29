import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); // 가로 길이
        int B = Integer.parseInt(st.nextToken()); // 세로 길이

        int[][] map = new int[B + 1][A + 1];

        // 두 번째 줄
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 로봇의 개수
        int M = Integer.parseInt(st.nextToken()); // 로봇에게 내릴 명령

        int[] r = new int[N + 1];
        int[] c = new int[N + 1];
        int[] rot = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String fr = st.nextToken();

            y = B - y + 1;

            map[y][x] = i;
            r[i] = y;
            c[i] = x;

            if (fr.equals("N")) {
                rot[i] = 0;
            } else if (fr.equals("E")) {
                rot[i] = 1;
            } else if (fr.equals("S")) {
                rot[i] = 2;
            } else if (fr.equals("W")) {
                rot[i] = 3;
            }
        }
        // print(map);

        // 명령만큼 반복
        L:for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int robot = Integer.parseInt(st.nextToken());
            String order = st.nextToken();
            int repeat = Integer.parseInt(st.nextToken());

            int ori_r = r[robot];
            int ori_c = c[robot];

            for (int d = 1; d <= repeat; d++) {
                if (order.equals("L")) {
                    rot[robot] -= 1;
                    if (rot[robot] == -1) {
                        rot[robot] = 3;
                    }
                }
                else if (order.equals("R")) {
                    rot[robot] += 1;
                    if (rot[robot] == 4) {
                        rot[robot] = 0;
                    }
                }
                else if (order.equals("F")) {
                    int nr = r[robot] + dr[rot[robot]];
                    int nc = c[robot] + dc[rot[robot]];
                    if (nr > 0 && nr < map.length && nc > 0 && nc < map[0].length) {
                        if (map[nr][nc] == 0) {
                            r[robot] = nr;
                            c[robot] = nc;

                            continue;
                        } else {
                            System.out.println("Robot " + robot + " crashes into robot " + map[nr][nc]);
                            return;
                        }
                    } else {
                        System.out.println("Robot " + robot + " crashes into the wall");
                        return;
                    }
                }
            }
            map[ori_r][ori_c] = 0;
            map[r[robot]][c[robot]] = robot;

        }
        System.out.println("OK");
    }

    static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}