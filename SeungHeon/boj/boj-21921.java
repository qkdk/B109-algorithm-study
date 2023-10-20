import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int a[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int s[] = new int[N + 1];
        s[0] = 0;
        s[1]= a[0];

        for (int i = 2; i < s.length; i++) {
            s[i] = s[i - 1] + a[i - 1];
        }

        int max = 0;
        int maxCount = 0;

        for (int i = X; i < s.length; i++) {
            int now = s[i] - s[i - X];

            if (now == max) {
                maxCount++;
            }

            if (now > max) {
                max = now;
                maxCount = 1;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(maxCount);
        }

    }
}