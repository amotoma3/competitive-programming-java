package amotoma3.graph;

import net.egork.graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphUtils {

    /**
     * 最大クリークのサイズを求める
     * @param graph　無向グラフ
     * @return 最大クリークのサイズ
     */
    public static int maximumClique(Graph graph) {
        int n = graph.vertexCount();
        int m = graph.edgeCount();

        boolean[][] g = new boolean[n][n];
        int[] cnt = new int[n];
        int edgeCnt = m;
        for (int i = 0; i < m; i++) {
            int from = graph.source(i);
            int to = graph.destination(i);
            if (!g[from][to]) {
                cnt[from]++;
                cnt[to]++;
                g[from][to] = g[to][from] = true;
            } else {
                edgeCnt--;
            }
        }

        boolean[] used = new boolean[n];

        int ret = 0;
        outer: for (int i = 0; i < n; i++) {
            int lim = (int) Math.sqrt(2 * edgeCnt);
            List<Integer> cand = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (!used[j] && cnt[j] < lim) {
                    cand.add(j);
                    for (int k = 0; k < n; k++) {
                        if (k == j) continue;
                        if (g[j][k]) cand.add(k);
                    }
                    ret = Math.max(ret, calcSize(g, cand));

                    used[j] = true;
                    for (int k = 0; k < n; k++) {
                        if (k == j) continue;
                        if (g[j][k]) {
                            edgeCnt--;
                            cnt[j]--;
                            g[j][k] = g[k][j] = false;
                        }
                    }

                    continue outer;
                }
            }
            break;
        }

        List<Integer> cand = new ArrayList<>();
        for (int i = 0; i < n; i++) if (!used[i]) cand.add(i);
        ret = Math.max(ret, calcSize(g, cand));

        return ret;
    }

    private static int calcSize(boolean[][] g, List<Integer> cand) {
        int n = cand.size();
        int ret = 0;
        int[] bit = new int[n];
        for (int i = 0; i < n; i++) {
            bit[i] |= 1 << i;
            for (int j = i + 1; j < n; j++) {
                if (g[cand.get(i)][cand.get(j)]) {
                    bit[i] |= 1 << j;
                    bit[j] |= 1 << i;
                }
            }
        }
        for (int i = 0; i < 1 << n; i++) {
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if ((i & bit[j]) != i) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                ret = Math.max(ret, Integer.bitCount(i));
            }
        }

        return ret;
    }

    public static void floydWarshall(long[][] d) {
        int n = d.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    d[j][k] = Math.min(d[j][k], d[j][i] + d[i][k]);
                }
            }
        }
    }
}
