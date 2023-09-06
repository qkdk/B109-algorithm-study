import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13422 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n, m, k;
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int[] houses = new int[n * 2];
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            for (int i = 0; i < n; i++) {
                houses[i] = Integer.parseInt(st.nextToken());
                houses[i + n] = houses[i];
                if (i < m) sum += houses[i];
            }

            int cnt = 0;
            if (sum < k) cnt++;

            if (n == m) {
                sb.append(cnt);
                sb.append('\n');
                continue;
            }

            for (int i = 0, j = m; i < n - 1; i++, j++) {
                sum -= houses[i];
                sum += houses[j];
                if (sum < k) cnt++;
            }

            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
