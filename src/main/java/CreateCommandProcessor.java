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
		return false;
	}
}
