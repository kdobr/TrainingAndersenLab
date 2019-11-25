package decorator.beverages;

public class DarkRoast extends Beverages {

	public DarkRoast() {
		description = "Dark Roast";
	}

	@Override
	public double costs() {
		return 0.99;
	}
}
