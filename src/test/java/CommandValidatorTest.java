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
