package amotoma3.number;

public class BinaryTrie {
    public static int BIT_MAX = 60;

    public BinaryTrie() {
        this.root = new Node();
    }

    public void insert(long val) {
        add(root, val, BIT_MAX - 1);
    }

    public void erase(long val) {
        sub(root, val, BIT_MAX - 1);
    }

    public long get(int idx) {
        return get(root, idx, BIT_MAX - 1);
    }

    public long first() {
        return get(0);
    }

    public long last() {
        return get(size() - 1);
    }

    public long pollFirst() {
        long val = first();
        erase(val);
        return val;
    }

    public long pollLast() {
        long val = last();
        erase(val);
        return val;
    }

    public int lowerBound(long val) {
        return countLower(root, val, BIT_MAX - 1);
    }

    public int upperBound(long val) {
        return lowerBound(val + 1) - 1;
    }

    public int index(long val) {
        if (!contains(val)) return -1;
        return lowerBound(val);
    }

    public int count(long from, long to) {
        return upperBound(to) - lowerBound(from) + 1;
    }

    public int count(long to) {
        return count(0, to);
    }

    public boolean contains(long val) {
        return exists(root, val, BIT_MAX - 1);
    }

    public int size() {
        return root.cnt;
    }

    public boolean empty() {
        return size() == 0;
    }

    private Node root;

    private void add(Node node, long val, int b) {
        node.cnt++;
        if (b < 0) return;

        int f = (int) (val >> b & 1);
        if (node.ch[f] == null) node.ch[f] = new Node();

        add(node.ch[f], val, b - 1);
    }

    private void sub(Node node, long val, int b) {
        node.cnt--;
        if (b < 0) return;

        int f = (int) (val >> b & 1);
        if (node.ch[f] == null) throw new RuntimeException();

        sub(node.ch[f], val, b - 1);
    }

    private long get(Node node, int idx, int b) {
        if (b < 0) return 0;

        int m = node.ch[0] != null ? node.ch[0].cnt : 0;
        return idx < m ? get(node.ch[0], idx, b - 1) : get(node.ch[1], idx - m, b - 1) | 1L << b;
    }

    private boolean exists(Node node, long val, int b) {
        if (b < 0) return true;

        int f = (int) (val >> b & 1);
        if (node.ch[f] == null) return false;

        return exists(node.ch[f], val, b - 1);
    }

    private int countLower(Node node, long val, int b) {
        if (node == null || b < 0) return 0;

        int f = (int) (val >> b & 1);

        return (f == 1 && node.ch[0] != null ? node.ch[0].cnt : 0) + countLower(node.ch[f], val, b - 1);
    }

    class Node {
        int cnt;
        Node[] ch = new Node[2];
    }
}
