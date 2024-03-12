package banking;

public class TransferCommandValidator {
	private Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String[] command) {
		return true;
	}
}
