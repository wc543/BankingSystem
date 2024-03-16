package banking;

public class Checking extends Account {

	public Checking(String id, double balance, double apr) {

		super(id, balance, apr);
	}

	public Checking(String id, String type, double apr) {
		super(id, type, apr);
	}

	public Checking() {
		super();
	}

}