public abstract class Account {
	private double balance;
	private double apr;
	private String type;

	public Account(double balance, double apr) {
		this.balance = balance;
		this.apr = apr;
	}

	public Account(String type, double apr) {
		this.type = type;
		this.apr = apr;
	}

	public Account() {
		balance = 0;
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
