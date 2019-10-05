package amotoma3.math;

import java.util.Random;

public class MathPlusTest {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            double y = r.nextInt(100);
            double x = r.nextInt(100);
            System.out.println(y + ", " + x);
            System.out.println(Math.atan2(y, x));
            System.out.println(MathPlus.atan2(y, x));
            System.out.println();
        }
    }
}
