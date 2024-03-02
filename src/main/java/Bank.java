import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<String, Account> account;

	Bank() {
		account = new HashMap<>();
	}

	public Map<String, Account> getAccount() {
		return account;
	}

	public void addAccount(String Id, String type, double apr) {
		if (type.equalsIgnoreCase("checking")) {
			account.put(Id, new Checking(type, apr));
		}

		else if (type.equalsIgnoreCase("savings")) {
			account.put(Id, new Savings(type, apr));
		}
	}
}
