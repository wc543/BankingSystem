import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	public static final double STARTING_APR = 1.2;
	public static final String CHECKING_ACCOUNT_TYPE = "Checking";
	public static final String CHECKING_ACCOUNT_ID = "12345678";

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

	@Test
	void validate_deposit_command_missing_action_is_invalid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("12345678 200");
		assertFalse(actual);
	}

	@Test
	void validate_deposit_command_is_spelled_wrong_is_invalid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("deposi 12345678 200");
		assertFalse(actual);
	}

	@Test
	void validate_deposit_command_is_case_insensitive_is_valid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("dEPoSIt 12345678 200");
		assertTrue(actual);
	}

	@Test
	void validate_deposit_command_is_valid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("deposit 12345678 200");
		assertTrue(actual);
	}
}
