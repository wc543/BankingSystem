import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Checking checking;

	@BeforeEach
	void setUp() {
		checking = new Checking();
		commandValidator = new CommandValidator(checking);
	}

	@Test
	void create_checking_account() {
		boolean actual = commandValidator.validate("create checking 12345678 1.2");
		assertTrue(actual);
	}

	@Test
	void create_savings_account() {
		boolean actual = commandValidator.validate("create savings 22334455 1.2");
		assertTrue(actual);
	}
}
