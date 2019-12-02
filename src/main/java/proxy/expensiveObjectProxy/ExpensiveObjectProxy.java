package proxy.expensiveObjectProxy;

import proxy.expensiveObject.ExpensiveObject;
import proxy.expensiveObject.ExpensiveObjectImpl;

public class ExpensiveObjectProxy implements ExpensiveObject {

	private static ExpensiveObject object;

	@Override
	public void process() {
		if (object == null) {
			object = new ExpensiveObjectImpl();
		}
		object.process();
	}
}
