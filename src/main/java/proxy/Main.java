package proxy;

import proxy.expensiveObject.ExpensiveObject;
import proxy.expensiveObjectProxy.ExpensiveObjectProxy;

public class Main {
	public static void main(String[] args) {
		ExpensiveObject object = new ExpensiveObjectProxy();
		object.process();
		object.process();
	}
}