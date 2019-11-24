package amotoma3.string;

import java.util.Random;

public class PalindromicTreeTest {
    static void displayFrequencies(PalindromicTree pt) {
        int[] freq = pt.buildFrequency();
        System.out.println("frequencies of each palindrome...");
        for (int i = 0; i < pt.size(); i++) {
            String palindrome = pt.debugIdToString(i);
            System.out.printf("%s : %d\n", palindrome, freq[i]);
        }
    }

    public static void main(String[] args) {
        PalindromicTree pt = new PalindromicTree();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 10000000; i++) {
            sb.append((char) ('a' + r.nextInt(26)));
        }
        for (char c : sb.toString().toCharArray()) {
            pt.add(c);
        }
        displayFrequencies(pt);
    }
}
