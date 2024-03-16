package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassCommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;

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
	void command_missing_amount_of_time_is_invalid() {
		boolean actual = commandValidator.validate("pass");
		assertFalse(actual);
	}

	@Test
	void command_missing_action_is_invalid() {
		boolean actual = commandValidator.validate("5");
		assertFalse(actual);
	}

	@Test
	void pass_time_is_valid() {
		boolean actual = commandValidator.validate("pass 5");
		assertTrue(actual);
	}

	@Test
	void pass_time_is_negative_is_invalid() {
		boolean actual = commandValidator.validate("pass -1");
		assertFalse(actual);
	}

	@Test
	void pass_time_is_equal_to_0_is_invalid() {
		boolean actual = commandValidator.validate("pass 0");
		assertFalse(actual);
	}

	@Test
	void pass_time_is_equal_to_minimum_of_1_is_valid() {
		boolean actual = commandValidator.validate("pass 1");
		assertTrue(actual);
	}

	@Test
	void pass_time_is_equal_to_maximum_of_60_is_valid() {
		boolean actual = commandValidator.validate("pass 60");
		assertTrue(actual);
	}

	@Test
	void pass_time_is_above_maximum_of_60_is_valid() {
		boolean actual = commandValidator.validate("pass 61");
		assertFalse(actual);
	}
}
