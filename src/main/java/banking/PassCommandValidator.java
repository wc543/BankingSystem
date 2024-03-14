package banking;

public class PassCommandValidator {
	private Bank bank;

	public PassCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		return validPassTimeNumberOfArguments(command);
	}

	private boolean validPassTimeNumberOfArguments(String[] command) {
		return command.length == 2;
	}
}
