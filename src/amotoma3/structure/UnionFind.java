package amotoma3.structure;

import java.util.*;

public class UnionFind {
    protected int[] parent;
    protected int size;

    public UnionFind(int size) {
        parent = new int[size];
        this.size = size;
        Arrays.fill(parent, -1);
    }

    public boolean union(int x, int y) {
        x = root(x);
        y = root(y);
        if (x == y) return false;
        if (parent[y] < parent[x]) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        parent[x] += parent[y];
        parent[y] = x;
        size--;
        return true;
    }

    public boolean same(int x, int y) {
        return root(x) == root(y);
    }

    public int root(int x) {
        return parent[x] < 0 ? x : (parent[x] = root(parent[x]));
    }

    public int size(int x) {
        return -parent[root(x)];
    }

    public int size() {
        return size;
    }

    public List<Set<Integer>> getGroups() {
        int n = parent.length;
        List<Set<Integer>> groups = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int r = root(i);
            if (!map.containsKey(r)) {
                map.put(r, groups.size());
                groups.add(new HashSet<>());
            }
            groups.get(map.get(r)).add(i);
        }
        return groups;
    }

    @Override
    public String toString() {
        return getGroups().toString();
    }
}
