package decorator.beverages;

public class Espresso extends Beverages {

	public Espresso() {
		description = "Espresso";
	}

	@Override
	public double costs() {

		return 1.99;
	}
}
