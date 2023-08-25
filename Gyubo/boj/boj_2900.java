import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_2900 {

    static int N;
    static int K;
    static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        a = new int[N];

        Map<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int key = Integer.parseInt(st.nextToken());
            if (map.containsKey(key)) {
                map.compute(key, (k, v) -> v + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (int key : map.keySet()) {
            something(key, map.get(key));
        }

        // 누적합 구하기
        long[] sum = new long[N + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + a[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int startIndex = Integer.parseInt(st.nextToken());
            int endIndex = Integer.parseInt(st.nextToken()) + 1;
            sb.append(sum[endIndex] - sum[startIndex]).append("\n");
        }

        System.out.println(sb);
    }

    static void something(int jump, int addNum) {
        int i = 0;
        while (i < N) {
            a[i] = a[i] + addNum;
            i = i + jump;
        }
    }

}
