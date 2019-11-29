package decorator.beverages;

public abstract class Beverages {

	String description = "unknown beverage";

	public String getDescription() {
		return description;
	}

	public abstract double costs();
}
