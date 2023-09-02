import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1300 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());
        long k = Integer.parseInt(br.readLine());

        long start = 0;
        long end = n * n;
        while (start < end) {
            long mid = (start + end) / 2;

            // 계산
            long size = 0;
            for (int i = 1; i <= n; i++) {
                size += Math.min(mid / i, n);
            }

            if (size >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start);
    }
}