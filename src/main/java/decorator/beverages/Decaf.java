package decorator.beverages;

public class Decaf extends Beverages {

	public Decaf() {
		description = "Decafee";
	}

	@Override
	public double costs() {
		return 1.05;
	}
}
