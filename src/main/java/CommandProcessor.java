public class CommandProcessor {

	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public boolean process(String command) {
		return false;
	}
}
