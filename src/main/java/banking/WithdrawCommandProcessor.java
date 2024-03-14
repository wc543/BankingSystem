package banking;

public class WithdrawCommandProcessor {
	private Bank bank;

	public WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String[] command) {
		String id = command[1];
		double amount = Double.parseDouble(command[2]);
		bank.getAccount().get(id).withdrawMoney(amount);
	}
}
