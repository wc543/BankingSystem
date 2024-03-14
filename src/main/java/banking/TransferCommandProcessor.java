package banking;

public class TransferCommandProcessor {
	private Bank bank;

	public TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String[] command) {
		String fromAccountId = command[1];
		String toAccountId = command[2];
		double amount = Double.parseDouble(command[3]);

		bank.getAccount().get(fromAccountId).withdrawMoney(amount);
		bank.getAccount().get(toAccountId).depositMoney(amount);
	}
}
