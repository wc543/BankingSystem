public class CreateCommandProcessor {

	private Bank bank;

	public CreateCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public boolean process(String[] command) {

		if (command[1].equalsIgnoreCase("checking") || command[1].equalsIgnoreCase("savings")) {
			double apr = Double.parseDouble(command[3]);
			bank.addAccount(command[2], command[1], apr);
		}

		else if (command[1].equalsIgnoreCase("cd")) {
			double apr = Double.parseDouble(command[3]);
			double balance = Double.parseDouble(command[4]);

			bank.addCdAccount(command[2], command[1], apr, balance);
		}
		return false;
	}
}
