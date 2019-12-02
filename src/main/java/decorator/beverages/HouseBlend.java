package decorator.beverages;

public class HouseBlend extends Beverages {

	public HouseBlend() {
		description = "HouseBland";
	}

	@Override
	public double costs() {
		return 0.89;
	}
}
