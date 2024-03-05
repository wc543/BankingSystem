package banking;

public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {

		String[] input = command.split(" ");

		if (input[0].equalsIgnoreCase("create")) {
			CreateCommandValidator validator = new CreateCommandValidator(bank);
			return validator.validate(input);
		}

		else if (input[0].equalsIgnoreCase("deposit")) {
			DepositCommandValidator validator = new DepositCommandValidator(bank);
			return validator.validate(input);
		}

		return false;
	}
}
