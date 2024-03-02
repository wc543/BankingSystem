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

		assertEquals("12345678", account.getApr());

	}
}
