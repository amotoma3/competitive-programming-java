package amotoma3.string;

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
        String s = "abcabccbc";
        for (char c : s.toCharArray()) {
            pt.add(c);
            displayFrequencies(pt);
        }
    }
}
