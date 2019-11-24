package amotoma3.string;

import net.egork.generated.collections.pair.CharIntPair;

import java.util.ArrayList;
import java.util.List;

public class StringUtilsPlus {
    public static List<CharIntPair> createSequenceCountList(String s) {
        List<CharIntPair> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            while (j < s.length() - 1 && s.charAt(j) == s.charAt(j + 1)) j++;
            list.add(new CharIntPair(s.charAt(i), j - i + 1));
            i = j;
        }
        return list;
    }
}
