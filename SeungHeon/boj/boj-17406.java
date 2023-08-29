import java.io.*;
import java.util.*;

public class boj17406 {

    static int[] arr_r;
    static int[] arr_c;
    static int[] arr_s;
    static int[][] map;
    static int answer;

    static int[] sel;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sel = new int[K];
        v = new boolean[K];

        // N개 줄
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int j = 0;
            while (st.hasMoreTokens()) {
                map[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }
//        print(map);

        arr_r = new int[K];
        arr_c = new int[K];
        arr_s = new int[K];

        // K개 줄
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            arr_r[i] = Integer.parseInt(st.nextToken());
            arr_c[i] = Integer.parseInt(st.nextToken());
            arr_s[i] = Integer.parseInt(st.nextToken());


        }
        answer = Integer.MAX_VALUE;
        recursive(0);
        System.out.println(answer);



    } // [E] main

    static void recursive(int count) {
        if (count == sel.length) {
            int[][] copyMap = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            for (int z = 0; z < sel.length; z++ ) {

                int r = arr_r[sel[z]];
                int c = arr_c[sel[z]];
                int s = arr_s[sel[z]];

                int start_r = r - s - 1;
                int start_c = c - s - 1;
                int end_r = r + s - 1;
                int end_c = c + s - 1;

                L: while (true) {
                    int now_r = start_r;
                    int now_c = start_c;

                    int before = copyMap[start_r][start_c]; // 이전 값을 저장하는 변수
                    int now = 0;
                    int rot = 0;

                    while (true) {
                        if (start_r >= end_r || start_c >= end_c) break L;
                        now_r = now_r + dr[rot];
                        now_c = now_c + dc[rot];

                        // 허용 범위 내에 있으면
                        if (now_r >= start_r && now_r <= end_r && now_c >= start_c && now_c <= end_c) {
                            now = copyMap[now_r][now_c];
                            copyMap[now_r][now_c] = before;
                            before = now;

                            // 처음 위치로 되돌아오면
                            if (now_r == start_r && now_c == start_c) {
                                start_r += 1;
                                start_c += 1;
                                end_r -= 1;
                                end_c -= 1;

                                break;
                            }
                        } else {
                            now_r = now_r - dr[rot];
                            now_c = now_c - dc[rot];
                            rot += 1;
                        }
                    }
                }
            }

        for (int i = 0; i < copyMap.length; i++) {
            int row_answer = 0;
            for (int j = 0; j < copyMap[0].length; j++) {
                row_answer += copyMap[i][j];
            }
            answer = Math.min(answer, row_answer);
        }
            return;
        }

        for (int i = 0; i < sel.length; i++) {
            if (v[i] == true) continue;
            v[i] = true;
            sel[count] = i;
            recursive(count + 1);
            sel[count] = 0;
            v[i] = false;
        }
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
