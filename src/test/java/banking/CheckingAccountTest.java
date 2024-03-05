package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

	@Test
	void checking_account_starting_balance_is_zero() {
		Checking checking = new Checking();
		double actual = checking.getBalance();

		assertEquals(0, actual);
	}
}