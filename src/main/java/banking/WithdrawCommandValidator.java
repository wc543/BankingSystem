package banking;

public class WithdrawCommandValidator {
	private Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (command.length < 3) {
			return false;
		}

		if (accountExistsInBank(command)) {
			if (withdrawCheckingAccount(command)) {
				return checkingAccountWithdrawAmount(command[2]);
			}
		}
		return false;
	}

	private boolean accountExistsInBank(String[] command) {
		String accountId = command[1];
		return bank.getAccount().containsKey(accountId);
	}

	private boolean withdrawCheckingAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("checking");
	}

	private boolean checkingAccountWithdrawAmount(String amount) {
		double depositAmount = Double.parseDouble(amount);
		return depositAmount >= 0 && depositAmount <= 400;
	}
}
