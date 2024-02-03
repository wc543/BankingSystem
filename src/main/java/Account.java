public abstract class Account {
	private double balance;
	private double apr;
	private String type;

	public Account(double balance) {
		this.balance = balance;
	}

	public Account(String type) {
		this.type = type;
	}

	public Account() {
		balance = 0;
	}

	public double getBalance() {
		return balance;
	}

	public double getApr(double currentApr) {
		return apr = currentApr;
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
