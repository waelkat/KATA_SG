import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sg.bankaccount.model.Client;

class ClientTest {

	@Test
	public void defaultClientCreationTest() {
		Client client = new Client(1, "Wael");

		assertEquals(client.getBalance(), 0);
		assertEquals(client.getClientName(), "Wael");
		assertEquals(client.getOperations().size(), 0);

	}

	@Test
	public void clientWithBalanceCreationTest() {
		Client client = new Client(1, "Wael", 20f);

		assertEquals(client.getBalance(), 20f);
		assertEquals(client.getClientName(), "Wael");
		assertEquals(client.getOperations().size(), 0);

	}
	
}
