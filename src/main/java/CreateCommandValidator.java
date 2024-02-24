public class CreateCommandValidator {
	private Bank bank;

	CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		if (command.length < 4) {
			return false;
		}

		if (command[1].equalsIgnoreCase("checking") || command[1].equalsIgnoreCase("savings")) {
			return validateCheckingOrSavingsCommandArguments(command);
		}

		return false;
	}

	public boolean validateCheckingOrSavingsCommandArguments(String[] command) {
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
}
