package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<String, Account> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public Map<String, Account> getAccount() {
		return accounts;
	}

	public void addAccount(String id, String type, double apr) {
		if (type.equalsIgnoreCase("checking")) {
			Checking account = new Checking(id, type, apr);
			accounts.put(id, account);
		}

		else if (type.equalsIgnoreCase("savings")) {
			Savings account = new Savings(id, type, apr);
			accounts.put(id, account);
		}
	}

	public void addCdAccount(String id, String type, double apr, double balance) {
		if (type.equalsIgnoreCase("cd")) {
			Cd account = new Cd(id, type, apr, balance);
			accounts.put(id, account);
		}
	}
}
