import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {
	CommandStorage commandStorage;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandStorage = new CommandStorage(bank);
	}

	@Test
	void check_invalid_command_storage_is_empty() {
		assertEquals(0, commandStorage.getInvalidCommands().size());
	}

	@Test
	void add_one_invalid_command_to_empty_invalid_command_storage() {
		commandStorage.addInvalidCommand("crate checking 12345678 1.2");
		assertEquals(1, commandStorage.getInvalidCommands().size());
	}

	@Test
	void add_two_invalid_commands_to_empty_invalid_command_storage() {
		commandStorage.addInvalidCommand("crate checking 12345678 1.2");
		commandStorage.addInvalidCommand("crate savings 87654321 1.4");
		assertEquals(2, commandStorage.getInvalidCommands().size());
	}

	@Test
	void check_one_invalid_command_is_added_correctly_to_invalid_command_storage() {
		commandStorage.addInvalidCommand("crate checking 12345678 1.2");
		assertEquals("crate checking 12345678 1.2", commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void check_two_invalid_commands_is_added_correctly_to_invalid_command_storage() {
		commandStorage.addInvalidCommand("crate checking 12345678 1.2");
		commandStorage.addInvalidCommand("crate savings 87654321 1.4");
		assertEquals("crate checking 12345678 1.2", commandStorage.getInvalidCommands().get(0));
		assertEquals("crate savings 87654321 1.4", commandStorage.getInvalidCommands().get(1));
	}
}
