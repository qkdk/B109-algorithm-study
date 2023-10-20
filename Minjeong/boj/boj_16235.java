import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_16235 {
    private static int[][] arr, land;
    private static final int[][] del = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int age;

        public Node(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.age, o.age);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m, k;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        land = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(land[i], 5);
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Integer>[][] trees = getTreeArray(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            trees[a][b].add(c);
        }

        for (int i = 0; i < k; i++) {
            List<Node> dieTrees = spring(trees, n);
            summer(dieTrees);
            autumn(trees, n);
            winter(n);
        }

        System.out.println(count(trees, n));
    }

    private static List<Integer>[][] getTreeArray(int n) {
        List<Integer>[][] trees = new List[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }
        return trees;
    }

    private static List<Node> spring(List<Integer>[][] trees, int n) {
        List<Node> dieTrees = new ArrayList<>();
        List<Integer>[][] newTrees = getTreeArray(n);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trees[i][j].sort(Integer::compare);
                for (int age : trees[i][j]) {
                    if (land[i][j] >= age) {
                        land[i][j] -= age;
                        newTrees[i][j].add(age + 1);
                    }
                    else dieTrees.add(new Node(j, i, age));
                }
            }
        }

        copyNewTrees(trees, newTrees, n);
        return dieTrees;
    }

    private static void copyNewTrees(List<Integer>[][] trees, List<Integer>[][] newTrees, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trees[i][j].clear();
                for (int age : newTrees[i][j]) {
                    trees[i][j].add(age);
                }
            }
        }
    }

    private static void summer(List<Node> dieTrees) {
        for (Node tree: dieTrees) {
            land[tree.y][tree.x] += tree.age / 2;
        }
    }

    private static void autumn(List<Integer>[][] trees, int n) {
        List<Integer>[][] newTrees = getTreeArray(n);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int age : trees[i][j]) {
                    if (age % 5 != 0) continue;
                    for (int k = 0; k < 8; k++) {
                        int ny = i + del[k][0];
                        int nx = j + del[k][1];
                        if (ny <= 0 || ny > n || nx <= 0 || nx > n) continue;
                        newTrees[ny][nx].add(1);
                    }
                }
            }
        }

        addNewTree(trees, newTrees, n);
    }

    private static void addNewTree(List<Integer>[][] trees, List<Integer>[][] newTrees, int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                trees[i][j].addAll(newTrees[i][j]);
            }
        }
    }

    private static void winter(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                land[i][j] += arr[i][j];
            }
        }
    }

    private static int count(List<Integer>[][] trees, int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                cnt += trees[i][j].size();
            }
        }
        return cnt;
    }
}
