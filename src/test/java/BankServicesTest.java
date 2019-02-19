import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankServicesTest {

	@Test
	public void bankServiceCreationTest() {
		
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);

		assertEquals(bankService.getClientWithID(1), client);

	}

	@Test
	public void makeDepositTest() {

		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);

		float firstDeposit = 15.25f;

		float secondeDeposit = 20f;
		float sumFirstSecond = firstDeposit + secondeDeposit;

		float thirdDeposit = 30.20f;
		float sumAll = sumFirstSecond + thirdDeposit;

		bankService.deposit(currentClient, firstDeposit);
		assertEquals(currentClient.getBalance(), firstDeposit);

		bankService.deposit(currentClient, secondeDeposit);
		assertEquals(currentClient.getBalance(), sumFirstSecond);

		bankService.deposit(currentClient, thirdDeposit);
		assertEquals(currentClient.getBalance(), sumAll);

	}

	@Test
	public void canMakeWithdrawalTest() {
		
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);

		assertFalse(bankService.canMakeWithdrawal(currentClient));

		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		bankService.deposit(currentClient, firstDeposit);
		assertTrue(bankService.canMakeWithdrawal(currentClient));
		
		bankService.deposit(currentClient, secondeDeposit);
		bankService.deposit(currentClient, thirdDeposit);
		
		//Withdrawal all deposit
		bankService.makeWithdrawal(currentClient, sumDeposit);
		
		assertFalse(bankService.canMakeWithdrawal(currentClient));

	}

	@Test
	public void makeWithdrawalWithNoBalanceTest() {
		
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;

		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;

		//Test without deposit
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(currentClient, sumDeposit);
		});
		
		//Add first deposit to balance
		bankService.deposit(firstDeposit);
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(currentClient, sumDeposit);
		});
		
		//Add seconde deposit to balance
		bankService.deposit(secondeDeposit);
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(currentClient, sumDeposit);
		});
		
		//Add third deposit to balance and withdrawal first one
		bankService.deposit(currentClient, thirdDeposit);
		bankService.makeWithdrawal(currentClient, firstDeposit);
		
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(currentClient, sumDeposit);
		});
	}

	@Test
	public void makeWithdrawalSameDepositeTest() {
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);
		
		float firstDeposit = 15.25f;
		
		//add and withdrawal the same deposit
		bankService.deposit(currentClient, firstDeposit);
		bankService.makeWithdrawal(currentClient, firstDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

	@Test
	public void makeWithdrawalRandomValueTest() {
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float withdrawal = 22.14;
		float rest = (firstDeposit + secondeDeposit) - withdrawal;
		
		//add and withdrawal the seconde deposit
		bankService.deposit(currentClient, firstDeposit);
		bankService.deposit(currentClient, secondeDeposit);
		bankService.makeWithdrawal(currentClient, withdrawal);
		assertEquals(currentClient.getBalance(), rest);
	}

	@Test
	public void makeWithdrawalAllTest() {
		BankService bankService = new BankServiceImp();
		Client client = new Client(1, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(1);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		//Add multiple deposit and withdraw all
		bankService.deposit(currentClient, firstDeposit);
		bankService.deposit(currentClient, secondeDeposit);
		bankService.deposit(currentClient, thirdDeposit);
		bankService.makeWithdrawal(currentClient, sumDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

}
