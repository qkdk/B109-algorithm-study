import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

class boj_21939 {
    static TreeMap<Integer, TreeSet<Integer>> list = new TreeMap<>();
    static Map<Integer, Integer> num = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            add(p, l);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String comm = st.nextToken();

            if (comm.equals("add")) {
                int p = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                add(p, l);
            } else if (comm.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    int l = list.lastKey();
                    System.out.println(list.get(l).last());
                } else {
                    int l = list.firstKey();
                    System.out.println(list.get(l).first());
                }
            } else {
                int p = Integer.parseInt(st.nextToken());
                int l = num.get(p);
                num.remove(p);
                list.get(l).remove(p);
                if (list.get(l).size() == 0) {
                    list.remove(l);
                }
            }
        }
    }

    private static void add(int p, int l) {
        list.computeIfAbsent(l, k -> new TreeSet<>());
        list.get(l).add(p);
        num.put(p, l);
    }
}