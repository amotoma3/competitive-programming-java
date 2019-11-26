package amotoma3.misc;

public class Sum2D {
    int n, m;
    long[][] sum;

    public Sum2D(int[][] a) {
        n = a.length;
        m = a[0].length;
        sum = new long[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = a[i][j];
            }
        }
        init();
    }

    public Sum2D(long[][] a) {
        n = a.length;
        m = a[0].length;
        sum = new long[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = a[i][j];
            }
        }
        init();
    }

    public void init() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] += sum[i + 1][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[j + 1][i + 1] += sum[j][i + 1];
            }
        }
    }

    public long query(int fromY, int fromX, int toY, int toX) {
        return sum[toY + 1][toX + 1] - sum[toY + 1][fromX] - sum[fromY][toX + 1] + sum[fromY][fromX];
    }
}
