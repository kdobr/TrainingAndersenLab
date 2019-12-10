package taskMultithreading.com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Необходимо уменьшить время выполнения вычислений.
 * <p>
 * Можно изменять этот класс или добавить новый. Решение должно быть максимально простым, без использования экзекуторов.
 * (Runnable, Threads). Аккуратно обработайте потенциальные ошибки (разумеется вычисления если появились ошибки нужно
 * остановить). Количество потоков должно быть ограничено значением константы com.company.TestConsts#MAX_THREADS.
 */
public class Test {
    private static int count = 0;
    static volatile boolean isAlive = true;
    private static Set<Double> set = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread[] arrThreads = new Thread[TestConsts.MAX_THREADS];
        for (int j = 0; j < TestConsts.MAX_THREADS; j++) {
            Thread thread = new MyThread(String.valueOf(j));
            arrThreads[j] = thread;
            thread.start();
        }
        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            arrThreads[i].join();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(set + "\n" + set.size() + "\n" + totalTime);
    }

    static synchronized int getAndIncrement() {
        return count++;
    }
    static synchronized void addAll(Set<Double> newSet) {
     set.addAll(newSet);
    }
}
