public class Savings extends Account {

	public Savings(String id, double balance, double apr) {
		super(id, balance, apr);
	}

	public Savings(String id, String type, double apr) {
		super(id, type, apr);
	}

	public Savings() {
		super();
	}

}