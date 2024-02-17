import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void create_checking_account_is_valid() {
		boolean actual = commandValidator.validate("create checking 12345678 1.2");
		assertTrue(actual);
	}

	@Test
	void create_savings_account_is_valid() {
		boolean actual = commandValidator.validate("create savings 22334455 1.2");
		assertTrue(actual);
	}

	@Test
	void create_account_case_insensitive_is_valid() {
		boolean actual = commandValidator.validate("CReAte ChECkiNg 12345678 1.2");
		assertTrue(actual);
	}

	@Test
	void create_account_with_id_less_than_eight_digits_is_invalid() {
		boolean actual = commandValidator.validate("create checking 1234567 1.2");
		assertFalse(actual);
	}

	@Test
	void create_account_with_id_greater_than_eight_digits_is_invalid() {
		boolean actual = commandValidator.validate("create checking 123456789 1.2");
		assertFalse(actual);
	}

	@Test
	void create_account_with_apr_below_zero_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678 -3.0");
		assertFalse(actual);
	}

	@Test
	void create_account_with_apr_zero_is_valid() {
		boolean actual = commandValidator.validate("create checking 12345678 0.0");
		assertTrue(actual);
	}

	@Test
	void create_account_with_apr_above_ten_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678 11.0");
		assertFalse(actual);
	}
}
