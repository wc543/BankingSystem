package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {
	public static final String ACCOUNT_ID = "12345678";
	public static final String CHECKING_ACCOUNT_TYPE = "Checking";
	public static final String SAVINGS_ACCOUNT_TYPE = "Savings";
	public static final String CD_ACCOUNT_TYPE = "Cd";
	public static final double STARTING_APR = 1.2;

	CommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void empty_command_is_invalid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_account_missing_account_id_and_amount_is_invalid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_account_missing_amount_is_invalid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678");
		assertFalse(actual);
	}
}
