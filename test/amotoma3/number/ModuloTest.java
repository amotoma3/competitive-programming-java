package amotoma3.number;

import java.util.Arrays;
import java.util.Random;

import static net.egork.misc.MiscUtils.MOD7;

public class ModuloTest {
    public static void main(String[] args) {
        Modulo.mod = MOD7;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(MOD7);
            System.out.println(r + ": " + Modulo.mlt(Modulo.rev(r), r));
        }

        Modulo.mod = 11;
        Modulo.buildCombination(10);
        System.out.println(Arrays.toString(Modulo.revs));
    }
}
