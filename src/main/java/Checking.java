public class Checking extends Account {

	public Checking(double balance, double apr) {

		super(balance, apr);
	}

	public Checking(String type, double apr) {
		super(type, apr);
	}

	public Checking() {
		super();
	}

}