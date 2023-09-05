import static java.util.Comparator.comparingInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class boj_21939 {

    private static final Comparator<Problem> COMPARATOR =
        comparingInt((Problem p) -> p.level)
            .thenComparingInt(p -> p.id);

    private static class Problem implements Comparable<Problem> {

        int id;
        int level;

        public Problem(int id, int level) {
            this.id = id;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Problem{" +
                "id=" + id +
                ", level=" + level +
                '}';
        }

        @Override
        public int compareTo(Problem p) {
            return COMPARATOR.compare(this, p);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        TreeSet<Problem> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            addElements(set, map, id, level);
        }

        int c = Integer.parseInt(br.readLine());
        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            processCommand(st, sb, set, map, command);
        }
        System.out.println(sb);
    }

    private static void processCommand(StringTokenizer st, StringBuilder sb, TreeSet<Problem> set,
        Map<Integer, Integer> map, String command) {
        if (command.equals("add")) {
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            addElements(set, map, id, level);
        }

        if (command.equals("recommend")) {
            int secondCommand = Integer.parseInt(st.nextToken());
            recommendElements(sb, set, secondCommand);
        }

        if (command.equals("solved")) {
            int id = Integer.parseInt(st.nextToken());
            set.remove(new Problem(id, map.get(id)));
        }
    }

    private static void recommendElements(StringBuilder sb, TreeSet<Problem> set, int secondCommand) {
        if (secondCommand == 1) {
            sb.append(set.last().id).append("\n");
        } else {
            sb.append(set.first().id).append("\n");
        }
    }

    private static void addElements(TreeSet<Problem> set, Map<Integer, Integer> map, int id,
        int level) {
        set.add(new Problem(id, level));
        map.put(id, level);
    }
}