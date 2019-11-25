package decorator.controller;

import decorator.beverages.Beverages;
import decorator.beverages.DarkRoast;
import decorator.beverages.Espresso;
import decorator.beverages.HouseBlend;
import decorator.condiments.Mocha;
import decorator.condiments.Soy;
import decorator.condiments.Whip;

public class StarBucksCaffee {

	public static void main(String[] args) {
		Beverages espresso = new Espresso();
		System.out.println(espresso.getDescription() + ", costs $: " + espresso.costs());

		Beverages darkRoast = new DarkRoast();
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Mocha(darkRoast);
		darkRoast = new Whip(darkRoast);
		System.out.println(darkRoast.getDescription() + ", costs $: " + darkRoast.costs());

		Beverages houseBlend = new HouseBlend();
		houseBlend = new Soy(houseBlend);
		houseBlend = new Mocha(houseBlend);
		houseBlend = new Whip(houseBlend);
		System.out.println(houseBlend.getDescription() + ", costs $: " + houseBlend.costs());

	}

}
