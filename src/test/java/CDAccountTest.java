import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CDAccountTest {

	@Test
	void cd_account_starting_balance_is_same_as_supplied_balance() {
		Cd cd = new Cd("12345678", 1000, 1.2);
		double actual = cd.getBalance();

		assertEquals(1000, actual);
	}
}