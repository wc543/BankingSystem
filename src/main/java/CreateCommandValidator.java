public class CreateCommandValidator {
	private Bank bank;

	CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (command.length < 2) {
			return false;
		}

		if (command[1].equalsIgnoreCase("checking") || command[1].equalsIgnoreCase("savings")) {
			return validateCheckingOrSavingsCommandArguments(command);
		}

		else if (command[1].equalsIgnoreCase("cd")) {
			return validateCdCommandArguments(command);
		}

		return false;
	}

	private boolean validateCdCommandArguments(String[] command) {
		if (command.length < 5) {
			return false;
		}

		boolean validNumberOfArguments = validCdAccountArgumentNumberOfArguments(command);
		boolean validAccountId = validAccountIdLength(command[2]);
		boolean validApr = validApr(command[3]);
		boolean validStartingBalance = validCdAccountStartingBalance(command[4]);

		return validNumberOfArguments && validAccountId && validApr && validStartingBalance;
	}

	public boolean validateCheckingOrSavingsCommandArguments(String[] command) {
		if (command.length < 4) {
			return false;
		}

		boolean validNumberOfArguments = validCheckingOrSavingsAccountNumberOfArguments(command);
		boolean validAccountId = validAccountIdLength(command[2]);
		boolean validApr = validApr(command[3]);

		return validNumberOfArguments && validAccountId && validApr;
	}

	private boolean validCheckingOrSavingsAccountNumberOfArguments(String[] command) {
		return command.length == 4;
	}

	private boolean validCdAccountArgumentNumberOfArguments(String[] command) {
		return command.length == 5;
	}

	private boolean validAccountIdLength(String accountId) {
		return accountId.length() == 8;
	}

	private boolean validApr(String aprPercent) {
		double apr = Double.parseDouble(aprPercent);
		return apr >= 0 && apr <= 10;
	}

	private boolean validCdAccountStartingBalance(String balance) {
		double startingBalance = Double.parseDouble(balance);
		return startingBalance >= 1000 && startingBalance <= 10000;
	}
}
