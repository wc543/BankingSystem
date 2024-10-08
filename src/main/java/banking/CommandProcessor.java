package banking;

public class CommandProcessor {

	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public boolean process(String command) {
		String[] input = command.split(" ");

		if (input[0].equalsIgnoreCase("create")) {
			CreateCommandProcessor processor = new CreateCommandProcessor(bank);
			processor.process(input);
		}

		else if (input[0].equalsIgnoreCase("deposit")) {
			DepositCommandProcessor processor = new DepositCommandProcessor(bank);
			processor.process(input);
		}

		else if (input[0].equalsIgnoreCase("withdraw")) {
			WithdrawCommandProcessor processor = new WithdrawCommandProcessor(bank);
			processor.process(input);
		}

		else if (input[0].equalsIgnoreCase("transfer")) {
			TransferCommandProcessor processor = new TransferCommandProcessor(bank);
			processor.process(input);
		}

		return false;
	}
}
