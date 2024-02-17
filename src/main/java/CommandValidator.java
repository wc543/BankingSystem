public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		AccountIdValidator accountIdValidator = new AccountIdValidator();

		String[] input = command.split(" ");

		String accountAction = input[0].toLowerCase();
		String accountType = input[1].toLowerCase();
		String accountId = input[2];

		if (!accountAction.equals("create") && !accountAction.equals("deposit")) {
			return false;
		}

		if (!accountType.equals("checking") && !accountType.equals("savings") && !accountType.equals("cd")) {
			return false;
		}

		if (!accountIdValidator.idIsValid(accountId)) {
			return false;
		}

		return true;
	}
}
