package amotoma3.misc;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {

    public static int lowerBound(int[] a, int x) {
        int high = a.length;
        int low = -1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (a[mid] < x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static int lowerBound(long[] a, long x) {
        int high = a.length;
        int low = -1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (a[mid] < x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static <T> int lowerBound(T[] a, T x, Comparator<T> comparator) {
        int high = a.length;
        int low = -1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (comparator.compare(a[mid], x) < 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static int upperBound(int[] a, int x) {
        int high = a.length;
        int low = -1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (a[mid] <= x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(long[] a, long x) {
        int high = a.length;
        int low = -1;
        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (a[mid] <= x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int lowerBound(List<Comparable> list, Comparable x) {
        int high = list.size();
        int low = 0;
        while (high > low) {
            int mid = (high + low) / 2;
            if (list.get(mid).compareTo(x) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int upperBound(List<Comparable> list, Comparable x) {
        int high = list.size();
        int low = 0;
        while (high > low) {
            int mid = (high + low) / 2;
            if (list.get(mid).compareTo(x) <= 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
