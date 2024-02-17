public class DepositValidator {

	public boolean validateDeposit(String[] input) {
		String accountType = input[1];
		String amount = input[2];

		double amountDeposit = Double.parseDouble(amount);

		if (input.length != 3) {
			return false;
		}

		if (amountDeposit < 0) {
			return false;
		}

		else if (accountType.equals("checking") && amountDeposit > 2500) {
			return false;
		}

		else if (accountType.equals("savings") && amountDeposit > 1000) {
			return false;
		}

		else if (accountType.equals("cd")) {
			return false;
		}

		return true;
	}
}
