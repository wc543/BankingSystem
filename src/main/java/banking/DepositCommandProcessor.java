package banking;

public class DepositCommandProcessor {
	private Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	void process(String[] command) {
		String id = command[1];
		double amount = Double.parseDouble(command[2]);
		bank.getAccount().get(id).depositMoney(amount);
	}

}
