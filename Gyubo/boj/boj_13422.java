import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13422 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            int count = 0;

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n + m - 1];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < m - 1; i++) {
                arr[n + i] = arr[i];
            }

            long[] dp = new long[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                dp[i + 1] = dp[i] + arr[i];
            }

            for (int i = 0; i < dp.length - m; i++) {
                long sum = dp[i + m] - dp[i];
                if (sum >= k) {
                    continue;
                }
                count++;
            }

            if (n == m) {
                if (count > 0) {
                    count = 1;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
