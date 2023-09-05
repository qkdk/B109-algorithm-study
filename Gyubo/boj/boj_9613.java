import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class boj_9613 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int[] arr = new int[size];

            for (int j = 0; j < size; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            sb.append(sumGCD(arr)).append("\n");
        }

        System.out.println(sb);
    }

    private static long sumGCD(int[] arr) {
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            BigInteger iNum = BigInteger.valueOf(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                count += iNum.gcd(BigInteger.valueOf(arr[j])).intValue();
            }
        }

        return count;
    }

}
