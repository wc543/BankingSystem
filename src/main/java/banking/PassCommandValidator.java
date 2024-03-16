package banking;

public class PassCommandValidator {
	private Bank bank;

	public PassCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		return validPassTimeNumberOfArguments(command) && validTime(command);
	}

	private boolean validPassTimeNumberOfArguments(String[] command) {
		return command.length == 2;
	}

	private boolean validTime(String[] command) {
		String timeToPass = command[1];

		int time = Integer.parseInt(timeToPass);
		return time >= 1 && time <= 60;
	}
}
