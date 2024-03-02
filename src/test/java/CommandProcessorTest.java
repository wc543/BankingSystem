import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	CommandProcessor commandProcessor;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void create_checking_account_with_correct_id_and_apr() {
		commandProcessor.process("create checking 12345678 1.0");

		Account account = bank.getAccount().get("12345678");

		assertEquals(1.0, account.getApr());
		assertEquals("12345678", account.getId());
	}

	@Test
	void create_savings_account_with_correct_id_and_apr() {
		commandProcessor.process("create savings 87654321 1.2");

		Account account = bank.getAccount().get("87654321");

		assertEquals(1.2, account.getApr());
		assertEquals("87654321", account.getId());
	}

	@Test
	void create_cd_account_with_correct_id_and_apr() {
		commandProcessor.process("create cd 21436587 1.4 2000");

		Account account = bank.getAccount().get("21436587");

		assertEquals(1.4, account.getApr());
		assertEquals("21436587", account.getId());
		assertEquals(2000, account.getBalance());
	}

	@Test
	void deposit_into_empty_checking_account() {
		commandProcessor.process("create checking 12345678 1.0");
		commandProcessor.process("deposit 12345678 900");

		Account account = bank.getAccount().get("12345678");

		assertEquals(900, account.getBalance());
	}

	@Test
	void deposit_into_empty_savings_account() {
		commandProcessor.process("create savings 87654321 1.2");
		commandProcessor.process("deposit 87654321 1300");

		Account account = bank.getAccount().get("87654321");

		assertEquals(1300, account.getBalance());
	}

	@Test
	void deposit_into_checking_account_with_balance() {
		commandProcessor.process("create checking 12345678 1.0");
		commandProcessor.process("deposit 12345678 900");

		Account account = bank.getAccount().get("12345678");

		commandProcessor.process("deposit 12345678 500");

		assertEquals(1400, account.getBalance());
	}

	@Test
	void deposit_into_savings_account_with_balance() {
		commandProcessor.process("create savings 87654321 1.2");
		commandProcessor.process("deposit 87654321 1300");

		Account account = bank.getAccount().get("87654321");

		commandProcessor.process("deposit 87654321 2000");

		assertEquals(3300, account.getBalance());
	}

	@Test
	void deposit_into_checking_account_twice() {
		commandProcessor.process("create checking 12345678 1.0");
		commandProcessor.process("deposit 12345678 900");

		Account account = bank.getAccount().get("12345678");

		commandProcessor.process("deposit 12345678 500");
		commandProcessor.process("deposit 12345678 500");

		assertEquals(1900, account.getBalance());
	}

	@Test
	void deposit_into_savings_account_twice() {
		commandProcessor.process("create savings 87654321 1.2");
		commandProcessor.process("deposit 87654321 1300");

		Account account = bank.getAccount().get("87654321");

		commandProcessor.process("deposit 87654321 2000");
		commandProcessor.process("deposit 87654321 2000");

		assertEquals(5300, account.getBalance());
	}
}
