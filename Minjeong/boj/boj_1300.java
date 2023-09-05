import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1300 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        long start = 1;
        long end = n * n;

        while (start < end) {
            long mid = (start + end) / 2;

            long res = 0;
            for (int i = 1; i <= n; i++) {
                res += Math.min(mid / i, n);
            }

            if (res < k) start = mid + 1;
            else end = mid;
        }

        System.out.println(end);
    }
}