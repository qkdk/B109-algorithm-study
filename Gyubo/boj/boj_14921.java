import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int startIndex = 0;
        int endIndex = n - 1;

        int result = arr[endIndex] + arr[startIndex];
        while (startIndex < endIndex) {
            int currentValue = arr[endIndex] + arr[startIndex];
            // 둘 중에 0에 가장 가까운것을 result에 할당
            if (Math.abs(currentValue) < Math.abs(result)){
                result = currentValue;
            }
            if (currentValue < 0) {
                startIndex++;
            } else if (currentValue > 0) {
                endIndex--;
            } else {
                System.out.println(0);
                return;
            }
        }
        System.out.println(result);
    }
}
