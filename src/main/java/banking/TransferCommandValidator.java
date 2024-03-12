package banking;

public class TransferCommandValidator {
	private Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (command.length < 4) {
			return false;
		}

		if (fromAccountExistsInBank(command) && toAccountExistsInBank(command)) {
			if (command[1].equals(command[2])) {
				return false;
			}

			else if (cdAccount(command)) {
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

	private boolean cdAccount(String[] command) {
		String fromAccountId = command[1];
		String toAccountId = command[2];
		Account fromAccount = bank.getAccount().get(fromAccountId);
		Account toAccount = bank.getAccount().get(toAccountId);

		return fromAccount.getType().equalsIgnoreCase("cd") || toAccount.getType().equalsIgnoreCase("cd");
	}

}
