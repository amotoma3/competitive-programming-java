package amotoma3.misc;

public class Sum1D {
    int n;
    long[] sum;

    public Sum1D(int[] a) {
        n = a.length;
        sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = a[i];
        }
        init();
    }

    public Sum1D(long[] a) {
        n = a.length;
        sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = a[i];
        }
        init();
    }

    public void init() {
        for (int i = 0; i < n; i++) {
            sum[i + 1] += sum[i];
        }
    }

    public long query(int from, int to) {
        return sum[to + 1] - sum[from];
    }
}
