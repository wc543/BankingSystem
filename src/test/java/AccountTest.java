import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

	public static final double MONEY_TO_DEPOSIT = 1000.00;
	public static final double STARTING_BALANCE = 1000.00;
	public static final double MONEY_TO_WITHDRAW = 1000.00;
	Account account;

	@BeforeEach
	public void setUp() {
		account = new Account() {
		};
	}

	@Test
	void account_apr_is_same_as_supplied_apr() {
		double actual = account.getApr(2);

		assertEquals(2, actual);
	}

	@Test
	void account_balance_increases_by_amount_deposited() {
		double actual = account.depositMoney(MONEY_TO_DEPOSIT);

		assertEquals(1000, actual);
	}

	@Test
	void account_balance_decreases_by_amount_withdrawn() {
		Checking checking = new Checking(STARTING_BALANCE);
		double actual = checking.withdrawMoney(1000);

		assertEquals(0, actual);
	}

	@Test
	void account_balance_cannot_go_below_zero_when_withdrawing() {
		Checking checking = new Checking(STARTING_BALANCE);
		double actual = checking.withdrawMoney(2000);

		assertEquals(0, actual);
	}

	@Test
	void account_can_be_deposited_into_twice() {
		account.depositMoney(MONEY_TO_DEPOSIT);
		account.depositMoney(MONEY_TO_DEPOSIT);

		double actual = account.getBalance();

		assertEquals(2000, actual);
	}

	@Test
	void account_can_be_withdrawn_from_twice() {
		Checking checking = new Checking(2000);

		checking.withdrawMoney(MONEY_TO_WITHDRAW);
		checking.withdrawMoney(MONEY_TO_WITHDRAW);

		double actual = checking.getBalance();

		assertEquals(0, actual);
	}
}