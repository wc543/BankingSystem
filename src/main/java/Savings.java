public class Savings extends Account {

	public Savings(double balance, double apr) {
		super(balance, apr);
	}

	public Savings(String type, double apr) {
		super(type, apr);
	}

	public Savings() {
		super();
	}

}