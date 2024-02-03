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

	public void addAccount(String Id, String type) {

		account.put(Id, new Checking(type));
	}
}
