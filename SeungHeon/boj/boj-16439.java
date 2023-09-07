import java.io.*;
import java.util.*;

public class bj16439 {

    static int[][] map;

    static int[] sel;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 줄
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        sel = new int[3];
        answer = 0;

        for (int i = 0; i < N; i++) {
            // 두 번째 줄
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0, 0);

        System.out.println(answer);


    }

    static void recursive(int count, int index) {
        if (count == sel.length) {
            int nowAnswer = 0;

            // 각 사람에 대하여 검사
            for (int i = 0; i < map.length; i++) {
                // 각 사람의 최고 선호도 저장
                int personAnswer = 0;

                for (int j = 0; j < sel.length; j++) {
                    int nowScore = map[i][sel[j]];
                    personAnswer = Math.max(personAnswer, nowScore);
                }
                nowAnswer += personAnswer;
            }

            answer = Math.max(answer, nowAnswer);

            if (answer == 15) {

            System.out.println(Arrays.toString(sel));
            }
            return;
        }

        for (int i = index; i < map[0].length; i++) {
            sel[count] = i;
            recursive(count + 1, i + 1);
            sel[count] = 0;
        }

    }
}
