package decorator.condiments;

import decorator.beverages.Beverages;

public class Soy extends CondimentDecorator {

	Beverages beverage;

	public Soy(Beverages beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	@Override
	public double costs() {
		return beverage.costs() + 0.15;
	}

}
