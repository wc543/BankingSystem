import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {
	private CommandValidator commandValidator;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void empty_command_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	void deposit_into_account_missing_account_id_and_amount_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit");
		assertFalse(actual);
	}

	@Test
	void deposit_into_account_missing_amount_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678");
		assertFalse(actual);
	}

	@Test
	void deposit_negative_amount_into_checking_account_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 -400");
		assertFalse(actual);
	}

	@Test
	void deposit_over_1000_into_checking_account_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 1100");
		assertFalse(actual);
	}

	@Test
	void deposit_into_checking_account_is_valid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 400");
		assertTrue(actual);
	}

	@Test
	void deposit_0_into_checking_account_is_valid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 0");
		assertTrue(actual);
	}

	@Test
	void deposit_maximum_1000_into_checking_account_is_valid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void deposit_negative_amount_into_savings_account_is_invalid() {
		bank.addAccount("12345678", "Savings", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 -400");
		assertFalse(actual);
	}

	@Test
	void deposit_over_2500_into_savings_account_is_invalid() {
		bank.addAccount("12345678", "Savings", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 2600");
		assertFalse(actual);
	}

	@Test
	void deposit_into_savings_account_is_valid() {
		bank.addAccount("12345678", "Savings", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void deposit_0_into_savings_account_is_valid() {
		bank.addAccount("12345678", "Savings", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void deposit_maximum_2500_savings_account_is_valid() {
		bank.addAccount("12345678", "Savings", 1.2);
		boolean actual = commandValidator.validate("deposit 12345678 2500");
		assertTrue(actual);
	}

	@Test
	void deposit_into_cd_account_is_invalid() {
		bank.addAccount("87654321", "cd", 1.2);
		boolean actual = commandValidator.validate("deposit 87654321 100");
		assertFalse(actual);
	}

	@Test
	void deposit_into_account_that_does_not_exisit_is_invalid() {
		bank.addAccount("12345678", "Checking", 1.2);
		boolean actual = commandValidator.validate("deposit 24567890 100");
		assertFalse(actual);
	}
}
