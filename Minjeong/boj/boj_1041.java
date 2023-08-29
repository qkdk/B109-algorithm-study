import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1041 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min1 = 100;
        int max = -1;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
            min1 = Math.min(min1, dice[i]);
            max = Math.max(max, dice[i]);
            sum += dice[i];
        }

        if (n == 1) {
            System.out.println(sum - max);
            return;
        }

        int[] mins = new int[3];
        mins[0] = Math.min(dice[0], dice[5]);
        mins[1] = Math.min(dice[1], dice[4]);
        mins[2] = Math.min(dice[2], dice[3]);
        int min3 = mins[0] + mins[1] + mins[2];

        Arrays.sort(mins);
        int min2 = mins[0] + mins[1];

        long res = min3 * 4L + (8L * n - 12) * min2 + ((n - 2L) * (n - 2L) + (n - 1L) * (n - 2L) * 4L) * min1;
        System.out.println(res);
    }
}
