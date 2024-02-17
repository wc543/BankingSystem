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
	void create_cd_account_is_valid() {
		boolean actual = commandValidator.validate("create cd 12345678 1.2 5000");
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
	void create_account_with_id_that_already_exists_is_invalid() {
		bank.addAccount("12345678", "Checking");
		boolean actual = commandValidator.validate("create savings 12345678 1.2");
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

	@Test
	void create_cd_account_with_balance_below_1000_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 1.2 500");
		assertFalse(actual);
	}

	@Test
	void create_cd_account_with_balance_above_10000_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 1.2 11000");
		assertFalse(actual);
	}

	@Test
	void deposit_into_checking_account_is_valid() {
		bank.addAccount("12345678", "Checking");
		boolean actual = commandValidator.validate("deposit checking 400");
		assertTrue(actual);
	}

	@Test
	void deposit_into_savings_account_is_valid() {
		bank.addAccount("12345678", "Savings");
		boolean actual = commandValidator.validate("deposit savings 400");
		assertTrue(actual);
	}

	@Test
	void deposit_into_cd_account_is_invalid() {
		bank.addAccount("12345678", "Cd");
		boolean actual = commandValidator.validate("deposit 12345678 400");
		assertFalse(actual);
	}

	@Test
	void deposit_more_than_2500_in_savings_account_is_invalid() {
		bank.addAccount("12345678", "Savings");
		boolean actual = commandValidator.validate("deposit 12345678 2600");
		assertFalse(actual);
	}

	@Test
	void deposit_more_than_1000_in_checking_account_is_invalid() {
		bank.addAccount("12345678", "Checking");
		boolean actual = commandValidator.validate("deposit 12345678 1100");
		assertFalse(actual);
	}

	@Test
	void deposit_negative_amount_into_account_is_invalid() {
		bank.addAccount("12345678", "Checking");
		boolean actual = commandValidator.validate("deposit 12345678 -100");
		assertFalse(actual);
	}

	@Test
	void deposit_zero_into_account_is_valid() {
		bank.addAccount("12345678", "Checking");
		boolean actual = commandValidator.validate("deposit checking 0");
		assertTrue(actual);
	}
}
