package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandValidatorTest {
	public static final String CHECKING_ACCOUNT_ID = "12345678";
	public static final String CHECKING_ACCOUNT_TYPE = "Checking";
	public static final String SAVINGS_ACCOUNT_ID = "87654321";
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
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	void transfer_to_account_missing_account_id_to_transfer_from_is_invalid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		bank.addAccount(SAVINGS_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("transfer 87654321 200");
		assertFalse(actual);
	}

	@Test
	void transfer_to_account_missing_account_id_to_transfer_to_is_invalid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		bank.addAccount(SAVINGS_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("transfer 12345678 200");
		assertFalse(actual);
	}

	@Test
	void transfer_without_specifying_amount_to_transfer_is_invalid() {
		bank.addAccount(CHECKING_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		bank.addAccount(SAVINGS_ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("transfer 12345678 87654321");
		assertFalse(actual);
	}

}
