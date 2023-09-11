import java.io.*;
import java.util.*;

public class boj2304 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] map = new int[1001];
        int biggest_pillar = 0; // 가장 높은 기둥 위치
        int last_pillar = 0; // 가장 마지막 기둥 위치

        // 나머지 줄
        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            map[L] = H;
            // 가장 높은 높이 비교
            if (map[biggest_pillar] < H) {
                biggest_pillar = L;
            }
            last_pillar = Math.max(last_pillar, L); // 가장 마지막 기둥 찾기

        }

        int answer = 0;
        int now_max = map[0]; // 현재 비교 중인 가장 큰 기둥
        // 가장 높은 기둥 기준 왼쪽을 탐색
        for (int i = 0; i < biggest_pillar; i++) {
            // 현재 기준 높은 기둥보다 더 높은 기둥이라면
            if (map[i] > now_max) {
                answer += map[i];
                now_max = map[i];
            } else {
                answer += now_max;
            }
        }

        now_max = map[last_pillar];

        // 가장 높은 기둥 기준 오른쪽 탐색
        for (int i = last_pillar; i > biggest_pillar; i--) {
            // 현재 기준 높은 기둥보다 더 높은 기둥이라면
            if (map[i] > now_max) {
                answer += map[i];
                now_max = map[i];
            } else {
                answer += now_max;
            }
        }

        // 가장 높은 기둥 합산
        answer += map[biggest_pillar];

        System.out.println(answer);


    }
}
