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

			else if (withdrawSavingsAccount(command)) {
				return savingsAccountWithdrawAmount(command[2]);
			}

			else if (withdrawCdAccount(command)) {
				return cdAccountWithdrawAmount(command);
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
		double withdrawAmount = Double.parseDouble(amount);
		return withdrawAmount >= 0;
	}

	private boolean withdrawSavingsAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("savings");
	}

	private boolean savingsAccountWithdrawAmount(String amount) {
		double withdrawAmount = Double.parseDouble(amount);
		return withdrawAmount >= 0;
	}

	private boolean withdrawCdAccount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		return account.getType().equalsIgnoreCase("cd");
	}

	private boolean cdAccountWithdrawAmount(String[] command) {
		String accountId = command[1];
		Account account = bank.getAccount().get(accountId);

		String withdraw = command[2];
		double withdrawAmount = Double.parseDouble(withdraw);

		return withdrawAmount >= account.getBalance();
	}
}
