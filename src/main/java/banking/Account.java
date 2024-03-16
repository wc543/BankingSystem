package banking;

public abstract class Account {
	private double balance;
	private double apr;
	private String type;
	private String id;

	public Account(String id, double balance, double apr) {
		this.id = id;
		this.balance = balance;
		this.apr = apr;
	}

	public Account(String id, String type, double apr) {
		this.id = id;
		this.type = type;
		this.apr = apr;
	}

	public Account() {
		balance = 0;
	}

	public Account(String id, String type, double apr, double balance) {
		this.id = id;
		this.type = type;
		this.apr = apr;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public double getApr() {
		return apr;
	}

	public void setApr(double apr) {
		this.apr = apr;
	}

	public double depositMoney(double moneyToDeposit) {
		return balance += moneyToDeposit;
	}

	public double withdrawMoney(double moneyToWithdraw) {
		if (moneyToWithdraw > balance) {
			return 0;
		}
		return balance -= moneyToWithdraw;
	}

	public String getType() {
		return type;
	}

}
