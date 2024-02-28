public class DepositCommandValidator {
	private Bank bank;

	DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (command.length < 3) {
			return false;
		}

		if (accountExistsInBank(command)) {
			if (depositCheckingAccount(command)) {
				return checkingAccountDepositAmount(command[2]);
			}

			else if (depositSavingsAccount(command)) {
				return savingsAccountDepositAmount(command[2]);
			}

			else if (depositCdAccount(command)) {
				return false;
			}
		}
		return false;
	}

	private boolean accountExistsInBank(String[] command) {
		String accountId = command[1];
		return bank.getAccount().containsKey(accountId);
	}

	private boolean depositCheckingAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("checking");
	}

	private boolean depositSavingsAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("savings");
	}

	private boolean depositCdAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("cd");
	}

	private boolean checkingAccountDepositAmount(String amount) {
		double depositAmount = Double.parseDouble(amount);
		return depositAmount >= 0 && depositAmount <= 1000;
	}

	private boolean savingsAccountDepositAmount(String amount) {
		double depositAmount = Double.parseDouble(amount);
		return depositAmount >= 0 && depositAmount <= 2500;
	}
}
