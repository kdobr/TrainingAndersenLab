package decorator.condiments;

import decorator.beverages.Beverages;

public class Mocha extends CondimentDecorator {

	Beverages beverage;

	public Mocha(Beverages beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {

		return beverage.getDescription() + ", Mocha";
	}

	@Override
	public double costs() {

		return 0.2 + beverage.costs();
	}

}
