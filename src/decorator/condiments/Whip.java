package decorator.condiments;

import decorator.beverages.Beverages;

public class Whip extends CondimentDecorator {

	Beverages beverage;
	
	public Whip(Beverages beverage) {
this.beverage = beverage;	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription()+", Whip";
	}

	@Override
	public double costs() {
		return beverage.costs()+0.1;
	}

}
