package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

	@Test
	void savings_account_starting_balance_is_zero() {
		Savings savings = new Savings();
		double actual = savings.getBalance();

		assertEquals(0, actual);
	}
}