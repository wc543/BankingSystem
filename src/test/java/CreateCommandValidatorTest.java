import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandValidatorTest {
	private CommandValidator commandValidator;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void empty_command_is_invalid() {
		boolean actual = commandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	void create_account_with_missing_account_type_and_apr_and_account_id_is_invalid() {
		boolean actual = commandValidator.validate("create");
		assertFalse(actual);
	}

	@Test
	void create_account_with_missing_apr_and_account_id_is_invalid() {
		boolean actual = commandValidator.validate("create savings");
		assertFalse(actual);
	}

	@Test
	void create_account_with_missing_apr_is_invalid() {
		boolean actual = commandValidator.validate("create savings 12345678");
		assertFalse(actual);
	}

	@Test
	void create_account_with_account_id_length_less_than_8_is_invalid() {
		boolean actual = commandValidator.validate("create savings 1234567 1.2");
		assertFalse(actual);
	}

	@Test
	void create_account_with_account_id_length_more_than_8_is_invalid() {
		boolean actual = commandValidator.validate("create savings 123456789 1.2");
		assertFalse(actual);
	}

	@Test
	void create_account_with_account_id_length_equals_8_is_valid() {
		boolean actual = commandValidator.validate("create savings 12345678 1.2");
		assertTrue(actual);
	}
}
