package taskMultithreading.com.company;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Не менять.
 */
class TestCalc {
    private static final Random rnd = new Random();

    static Set<Double> calculate(int num) throws TestException {
        try {
            Thread.sleep(rnd.nextInt(1000) + 1);
            Set<Double> res = new HashSet<>();

            int n = rnd.nextInt(num + 1) + 1;
            System.out.println("Поток " + Thread.currentThread().getName() + " вставляет");
            for (int j = 0; j < n; j++) {
                res.add(rnd.nextDouble());
               // if (j==3) throw new ArithmeticException();
            }
            return res;
        } catch (Exception e) {
            throw new TestException("Execution error.", e);
        }
    }
}
