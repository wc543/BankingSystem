import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {
	public static final String INVALID_CREATE_COMMAND = "creat checking 12345678 1.0";
	public static final String INVALID_DEPOSIT_COMMAND = "depositt 12345678 100";
	public static final String VALID_CREATE_COMMAND = "create checking 12345678 1.0";

	MasterControl masterControl;
	List<String> input;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank),
				new CommandStorage(bank));
	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		input.add(INVALID_CREATE_COMMAND);

		List<String> actual = masterControl.start(input);

		assertSingleCommand(INVALID_CREATE_COMMAND, actual);
	}

	@Test
	void typo_in_deposit_command_is_invalid() {
		input.add(INVALID_DEPOSIT_COMMAND);

		List<String> actual = masterControl.start(input);

		assertSingleCommand(INVALID_DEPOSIT_COMMAND, actual);
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add(INVALID_CREATE_COMMAND);
		input.add(INVALID_DEPOSIT_COMMAND);

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals(INVALID_CREATE_COMMAND, actual.get(0));
		assertEquals(INVALID_DEPOSIT_COMMAND, actual.get(1));
	}

	@Test
	void invalid_to_create_accounts_with_same_ID() {
		input.add(VALID_CREATE_COMMAND);
		input.add(VALID_CREATE_COMMAND);

		List<String> actual = masterControl.start(input);

		assertSingleCommand(VALID_CREATE_COMMAND, actual);
	}

}
