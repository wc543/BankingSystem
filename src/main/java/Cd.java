public class Cd extends Account {

	public Cd(String id, double balance, double apr) {
		super(id, balance, apr);
	}

	public Cd(String id, String type, double apr) {
		super(id, type, apr);
	}

	public Cd() {
		super();
	}

}
