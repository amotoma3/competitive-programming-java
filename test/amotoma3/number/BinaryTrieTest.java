package amotoma3.number;

import java.util.Arrays;
import java.util.Random;

public class BinaryTrieTest {

    public static void main(String[] args) {
        BinaryTrie trie = new BinaryTrie();
        final int n = 10;
        int[] a = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(100);
            trie.insert(a[i]);
        }
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < n; i++) {
            System.out.println(trie.get(i));
        }

        System.out.println(trie.contains(a[0]));
        System.out.println(trie.contains(a[0] - 1));
        System.out.println(trie.contains(a[0] + 1));

        trie.erase(a[0]);
        for (int i = 0; i < n - 1; i++) {
            System.out.println(trie.get(i));
        }
    }
}
