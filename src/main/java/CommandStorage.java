import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private Bank bank;

	private List<String> invalidCommands = new ArrayList<>();

	public CommandStorage(Bank bank) {
		this.bank = bank;
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}

	public void addInvalidCommand(String command) {
		invalidCommands.add(command);
	}
}
