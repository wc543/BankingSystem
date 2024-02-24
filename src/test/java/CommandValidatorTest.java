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
	void validate_create_command_is_valid() {
		boolean actual = commandValidator.validate("create savings 12345678 1.2");
		assertTrue(actual);
	}

	@Test
	void validate_create_command_is_case_insensitive_is_valid() {
		boolean actual = commandValidator.validate("cReAtE SaViNgs 12345678 1.2");
		assertTrue(actual);
	}

	@Test
	void validate_create_command_is_spelled_wrong_is_invalid() {
		boolean actual = commandValidator.validate("crate savings 12345678 1.2");
		assertFalse(actual);
	}

	@Test
	void validate_create_command_missing_action_is_invalid() {
		boolean actual = commandValidator.validate("savings 12345678 1.2");
		assertFalse(actual);
	}
}
