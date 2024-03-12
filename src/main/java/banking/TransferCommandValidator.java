package banking;

public class TransferCommandValidator {
	private Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (fromAccountExistsInBank(command) && toAccountExistsInBank(command)) {
			if (fromAccountExistsInBank(command) == toAccountExistsInBank(command)) {
				return false;
			}

			else {
				return true;
			}
		}

		return false;
	}

	private boolean fromAccountExistsInBank(String[] command) {
		String accountId = command[1];
		return bank.getAccount().containsKey(accountId);
	}

	private boolean toAccountExistsInBank(String[] command) {
		String accountId = command[2];
		return bank.getAccount().containsKey(accountId);
	}

}
