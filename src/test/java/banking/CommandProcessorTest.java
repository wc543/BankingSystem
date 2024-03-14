package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	public static final String CREATE_CHECKING_ACCOUNT_COMMAND = "create checking 12345678 1.0";
	public static final String CREATE_SAVINGS_ACCOUNT_COMMAND = "create savings 87654321 1.2";
	public static final String CREATE_CD_ACCOUNT_COMMAND = "create cd 21436587 1.4 2000";
	public static final String CHECKING_ACCOUNT_ID = "12345678";
	public static final String SAVINGS_ACCOUNT_ID = "87654321";
	public static final String CD_ACCOUNT_ID = "21436587";
	public static final String DEPOSIT_500_IN_CHECKING_ACCOUNT_COMMAND = "deposit 12345678 500";
	public static final String DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND = "deposit 12345678 900";
	public static final String DEPOSIT_1300_IN_SAVINGS_ACCOUNT_COMMAND = "deposit 87654321 1300";
	public static final String DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND = "deposit 87654321 2000";
	public static final String WITHDRAW_200_FROM_CHECKING_ACCOUNT_COMMAND = "withdraw 12345678 200";
	public static final String WITHDRAW_600_FROM_SAVINGS_ACCOUNT_COMMAND = "withdraw 87654321 600";

	CommandProcessor commandProcessor;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void create_checking_account_with_correct_id_and_apr() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);

		assertEquals(1.0, account.getApr());
		assertEquals(CHECKING_ACCOUNT_ID, account.getId());
	}

	@Test
	void create_savings_account_with_correct_id_and_apr() {
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		assertEquals(1.2, account.getApr());
		assertEquals(SAVINGS_ACCOUNT_ID, account.getId());
	}

	@Test
	void create_cd_account_with_correct_id_and_apr() {
		commandProcessor.process(CREATE_CD_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CD_ACCOUNT_ID);

		assertEquals(1.4, account.getApr());
		assertEquals(CD_ACCOUNT_ID, account.getId());
		assertEquals(2000, account.getBalance());
	}

	@Test
	void deposit_into_empty_checking_account() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);

		assertEquals(900, account.getBalance());
	}

	@Test
	void deposit_into_empty_savings_account() {
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_1300_IN_SAVINGS_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		assertEquals(1300, account.getBalance());
	}

	@Test
	void deposit_into_checking_account_with_balance() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);

		commandProcessor.process(DEPOSIT_500_IN_CHECKING_ACCOUNT_COMMAND);

		assertEquals(1400, account.getBalance());
	}

	@Test
	void deposit_into_savings_account_with_balance() {
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_1300_IN_SAVINGS_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		commandProcessor.process(DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND);

		assertEquals(3300, account.getBalance());
	}

	@Test
	void deposit_into_checking_account_twice() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);

		commandProcessor.process(DEPOSIT_500_IN_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_500_IN_CHECKING_ACCOUNT_COMMAND);

		assertEquals(1900, account.getBalance());
	}

	@Test
	void deposit_into_savings_account_twice() {
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_1300_IN_SAVINGS_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		commandProcessor.process(DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND);

		assertEquals(5300, account.getBalance());
	}

	@Test
	void withdraw_from_checking_account_with_balance() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(CHECKING_ACCOUNT_ID);

		commandProcessor.process(WITHDRAW_200_FROM_CHECKING_ACCOUNT_COMMAND);

		assertEquals(700, account.getBalance());
	}

	@Test
	void withdraw_from_savings_account_with_balance() {
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND);

		Account account = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		commandProcessor.process(WITHDRAW_600_FROM_SAVINGS_ACCOUNT_COMMAND);

		assertEquals(1400, account.getBalance());
	}

	@Test
	void transfer_from_checking_account_to_savings_account() {
		commandProcessor.process(CREATE_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(CREATE_SAVINGS_ACCOUNT_COMMAND);

		commandProcessor.process(DEPOSIT_900_IN_CHECKING_ACCOUNT_COMMAND);
		commandProcessor.process(DEPOSIT_2000_IN_SAVINGS_ACCOUNT_COMMAND);

		Account checking = bank.getAccount().get(CHECKING_ACCOUNT_ID);
		Account savings = bank.getAccount().get(SAVINGS_ACCOUNT_ID);

		commandProcessor.process("transfer 12345678 87654321 400");

		assertEquals(500, checking.getBalance());
		assertEquals(2400, savings.getBalance());
	}
}
