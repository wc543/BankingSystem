public class CommandProcessor {

	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public boolean process(String command) {
		String[] input = command.split(" ");

		if (input[0].equalsIgnoreCase("create")) {
			CreateCommandProcessor processor = new CreateCommandProcessor(bank);
			return processor.process(input);
		}

		return false;
	}
}
