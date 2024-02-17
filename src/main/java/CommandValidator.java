public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		AccountIdValidator accountIdValidator = new AccountIdValidator();
		AprValidator aprValidator = new AprValidator();
		DepositValidator depositValidator = new DepositValidator();

		String[] input = command.split(" ");

		String accountAction = input[0].toLowerCase();
		String accountType = input[1].toLowerCase();
		String accountId = input[2];
		String apr = null;

		if (accountAction.equals("create")) {
			apr = input[3];
		}
		if (!accountAction.equals("create") && !accountAction.equals("deposit")) {
			return false;
		}

		if (!accountType.equals("checking") && !accountType.equals("savings") && !accountType.equals("cd")) {
			return false;
		}

		if (!accountIdValidator.idIsValid(accountId)) {
			return false;
		}

		if (!aprValidator.aprIsValid(apr)) {
			return false;
		}

		if (accountAction.equals("deposit")) {
			if (input.length != 3) {
				return false;
			}

			else if (!depositValidator.validateDeposit(input)) {
				return false;
			}

		}

		return true;
	}
}
