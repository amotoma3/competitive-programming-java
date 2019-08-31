package amotoma3.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modulo {
    public static long mod;
    private long value;
    public static long[] nors;
    public static long[] revs;
    public static Map<Long, List<Long>> pows = new HashMap<>();

    public Modulo(long value) {
        this.value = (value % mod + mod) % mod;
    }

    public static long add(long a, long b) {
        return ((a + b) % mod + mod) % mod;
    }

    public static long add(long... a) {
        long ret = 0;
        for (long x : a) {
            ret = add(ret, x);
        }
        return ret;
    }

    public static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }

    public static long mlt(long a, long b) {
        return ((a % mod + mod) * (b % mod + mod)) % mod;
    }

    public static long rev(long a) {
        //return pow(a, mod - 2);
        return (Euclid.extgcd(a, mod, new long[3])[0] % mod + mod) % mod;
    }

    public static long div(long a, long b) {
        return mlt(a, rev(b));
    }

    public static long pow(long a, long x) {
        if (pows.containsKey(a) && x < pows.get(a).size()) return pows.get(a).get((int) x);
        long ans = 1 % mod;
        while (x > 0) {
            if ((x & 1) != 0) ans = (ans * a) % mod;
            x >>= 1;
            a = (a * a) % mod;
        }
        return ans;
    }

    public static void buildPow(long a, int n) {
        List<Long> list = new ArrayList<>(n + 1);
        list.add(1L);
        for (int i = 1; i <= n; i++) {
            list.add(mlt(list.get(i - 1), a));
        }
        pows.put(a, list);
    }

    public Modulo add(long a) {
        setValue(add(value, a));
        return this;
    }

    public Modulo add(Modulo m) {
        return add(m.getValue());
    }

    public Modulo sub(long a) {
        setValue(sub(value, a));
        return this;
    }

    public Modulo sub(Modulo m) {
        return sub(m.getValue());
    }

    public Modulo mlt(long a) {
        setValue(mlt(value, a));
        return this;
    }

    public Modulo mlt(Modulo m) {
        return mlt(m.getValue());
    }

    public Modulo div(long a) {
        setValue(div(value, a));
        return this;
    }

    public Modulo div(Modulo m) {
        return div(m.getValue());
    }

    /**
     * Determinant
     * O(n^3)
     */
    public static long det(long[][] a) {
        int n = a.length;
        long det = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                while (a[j][i] != 0) {
                    long r = a[i][i] / a[j][i];
                    for (int k = i; k < n; k++) {
                        long t = (a[i][k] - r * a[j][k]) % mod;
                        a[i][k] = a[j][k];
                        a[j][k] = t;
                    }
                    det = -det;
                }
            }
            det = (det * a[i][i]) % mod;
        }
        return det;
    }

    public static void buildCombination(int n) {
        nors = new long[n + 1];
        revs = new long[n + 1];

        nors[0] = 1;
        for (int i = 1; i <= n; i++) {
            nors[i] = mlt(nors[i - 1], i);
        }

        revs[n] = rev(nors[n]);
        for (int i = n - 1; i >= 0; i--) {
            revs[i] = mlt(revs[i + 1], i + 1);
        }
    }

    /**
     * nHk
     */
    public static long h(int n, int k) {
        return k <= 0 ? 1 : new Modulo(nors[n + k - 1]).mlt(revs[n]).mlt(revs[k - 1]).getValue();
    }

    /**
     * nCk
     */
    public static long c(int n, int k) {
        return n - k < 0 ? 0 : new Modulo(nors[n]).mlt(revs[k]).mlt(revs[n - k]).getValue();
    }

    /**
     * nPk
     */
    public static long p(int n, int k) {
        return n - k < 0 ? 0 : new Modulo(nors[n]).mlt(revs[n - k]).getValue();
    }

    /**
     * n!
     */
    public static long f(int n) {
        return nors[n];
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
