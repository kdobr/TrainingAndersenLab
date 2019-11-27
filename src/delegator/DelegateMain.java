package delegator;

public class DelegateMain {

    public static void main(String[] args) {
        DelegateMain test = new DelegateMain();
        DelegateMain.Executor executor = test.new Executor();
        executor.work();
        DelegateMain.Delegator delegator = test.new Delegator();
        delegator.work();
    }

    class Executor {
        void work() {
            System.out.println("class A works");
        }
    }

    class Delegator {
        Executor executor = new Executor();

        void work() {
            executor.work();
        }
    }
}