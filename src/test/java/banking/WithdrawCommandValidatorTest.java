package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	@Test
	void withdraw_from_checking_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 200");
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_from_checking_account_is_invalid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 -1");
		assertFalse(actual);
	}

	@Test
	void withdraw_0_from_checking_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	void withdraw_more_than_400_from_checking_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 401");
		assertTrue(actual);
	}

	@Test
	void withdraw_maximum_of_400_from_checking_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, CHECKING_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 400");
		assertTrue(actual);
	}

	@Test
	void withdraw_from_savings_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, SAVINGS_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 900");
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_from_savings_account_is_invalid() {
		bank.addAccount(ACCOUNT_ID, SAVINGS_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 -1");
		assertFalse(actual);
	}

	@Test
	void withdraw_0_from_savings_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, SAVINGS_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	void withdraw_more_than_1000_from_savings_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, SAVINGS_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 1001");
		assertTrue(actual);
	}

	@Test
	void withdraw_maximum_of_1000_from_checking_account_is_valid() {
		bank.addAccount(ACCOUNT_ID, SAVINGS_ACCOUNT_TYPE, STARTING_APR);
		boolean actual = commandValidator.validate("withdraw 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void withdraw_from_cd_account_is_valid() {
		bank.addCdAccount(ACCOUNT_ID, CD_ACCOUNT_TYPE, STARTING_APR, 5000);

		boolean actual = commandValidator.validate("withdraw 12345678 5000");
		assertTrue(actual);
	}

	@Test
	void withdraw_less_than_cd_account_total_balance_is_invalid() {
		bank.addCdAccount(ACCOUNT_ID, CD_ACCOUNT_TYPE, STARTING_APR, 5000);

		boolean actual = commandValidator.validate("withdraw 12345678 4999");
		assertFalse(actual);
	}

	@Test
	void withdraw_more_than_cd_account_total_balance_is_valid() {
		bank.addCdAccount(ACCOUNT_ID, CD_ACCOUNT_TYPE, STARTING_APR, 5000);

		boolean actual = commandValidator.validate("withdraw 12345678 5001");
		assertTrue(actual);
	}

}
