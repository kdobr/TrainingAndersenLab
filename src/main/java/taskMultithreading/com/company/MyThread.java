package taskMultithreading.com.company;

public class MyThread extends Thread {

    MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            int count;
            while ((count = Test.getAndIncrement()) < 10 && Test.isAlive) {
                Test.addAll(TestCalc.calculate(count));
                System.out.println(count);
            }
        } catch (TestException e) {
            Test.isAlive = false;
            System.out.println(e.getMessage());
        }
    }
}

