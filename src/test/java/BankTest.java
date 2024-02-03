import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	public static final String CHECKING_ACCOUNT = "Checking";
	public static final String CHECKING_ACCOUNT_ID = "10000000";
	public static final String SAVINGS_ACCOUNT = "Savings";
	public static final String SAVINGS_ACCOUNT_ID = "20000000";
	public static final double MONEY_TO_DEPOSIT = 1000;
	public static final double MONEY_TO_WITHDRAW = 1000;

	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
	}

	@Test
	void bank_has_no_accounts_initially() {
		assertTrue(bank.getAccount().isEmpty());
	}

	@Test
	void add_one_account_to_bank() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		assertEquals(CHECKING_ACCOUNT, bank.getAccount().get(CHECKING_ACCOUNT_ID).getType());
	}

	@Test
	void add_two_accounts_to_bank() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		assertEquals(CHECKING_ACCOUNT, bank.getAccount().get(CHECKING_ACCOUNT_ID).getType());
		assertEquals(SAVINGS_ACCOUNT, bank.getAccount().get(SAVINGS_ACCOUNT_ID).getType());
	}

	@Test
	void retrieve_correct_account_from_bank() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		assertEquals(CHECKING_ACCOUNT, bank.getAccount().get(CHECKING_ACCOUNT_ID).getType());
	}

	@Test
	void deposit_money_into_correct_account() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);
		account.depositMoney(MONEY_TO_DEPOSIT);

		assertEquals(1000, account.getBalance());
	}

	@Test
	void withdraw_money_from_correct_account() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);
		account.depositMoney(MONEY_TO_DEPOSIT);
		account.withdrawMoney(MONEY_TO_WITHDRAW);

		assertEquals(0, account.getBalance());
	}

	@Test
	void deposit_twice_to_account_through_bank() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);
		account.depositMoney(MONEY_TO_DEPOSIT);
		account.depositMoney(MONEY_TO_DEPOSIT);

		assertEquals(2000, account.getBalance());
	}

	@Test
	void withdraw_twice_from_account_through_bank() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT);
		bank.addAccount(SAVINGS_ACCOUNT_ID, SAVINGS_ACCOUNT);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);
		account.depositMoney(MONEY_TO_DEPOSIT);
		account.depositMoney(MONEY_TO_DEPOSIT);

		account.withdrawMoney(MONEY_TO_WITHDRAW);
		account.withdrawMoney(MONEY_TO_WITHDRAW);

		assertEquals(0, account.getBalance());
	}
}
