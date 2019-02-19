import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sg.bankaccount.CantMakeWithdrawalException;
import com.sg.bankaccount.model.Client;
import com.sg.bankaccount.service.BankService;
import com.sg.bankaccount.service.BankServiceImp;

class BankServicesTest {

	@Test
	public void bankServiceCreationTest() {
		
		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);

		assertEquals(bankService.getClientWithID(clientID), client);

	}

	@Test
	public void makeDepositTest() {

		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);

		float firstDeposit = 15.25f;

		float secondeDeposit = 20f;
		float sumFirstSecond = firstDeposit + secondeDeposit;

		float thirdDeposit = 30.20f;
		float sumAll = sumFirstSecond + thirdDeposit;

		bankService.deposit(clientID, firstDeposit);
		assertEquals(currentClient.getBalance(), firstDeposit);

		bankService.deposit(clientID, secondeDeposit);
		assertEquals(currentClient.getBalance(), sumFirstSecond);

		bankService.deposit(clientID, thirdDeposit);
		assertEquals(currentClient.getBalance(), sumAll);

	}

	@Test
	public void canMakeWithdrawalTest() throws CantMakeWithdrawalException {

		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);

		assertFalse(bankService.canMakeWithdrawal(clientID, 15f));

		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		bankService.deposit(clientID, firstDeposit);
		assertTrue(bankService.canMakeWithdrawal(clientID, firstDeposit));
		
		bankService.deposit(clientID, secondeDeposit);
		bankService.deposit(clientID, thirdDeposit);
		
		//Withdrawal all deposit
		bankService.makeWithdrawal(clientID, sumDeposit);
		
		assertFalse(bankService.canMakeWithdrawal(clientID, sumDeposit));

	}

	@Test
	public void makeWithdrawalWithNoBalanceTest() throws CantMakeWithdrawalException {

		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;

		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;

		//Test without deposit
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(clientID, sumDeposit);
		});
		
		//Add first deposit to balance
		bankService.deposit(clientID, firstDeposit);
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(clientID, sumDeposit);
		});
		
		//Add seconde deposit to balance
		bankService.deposit(clientID, secondeDeposit);
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(clientID, sumDeposit);
		});
		
		//Add third deposit to balance and withdrawal first one
		bankService.deposit(clientID, thirdDeposit);
		bankService.makeWithdrawal(clientID, firstDeposit);
		
		assertThrows(CantMakeWithdrawalException.class, () -> {
			bankService.makeWithdrawal(clientID, sumDeposit);
		});
	}

	@Test
	public void makeWithdrawalSameDepositeTest() throws CantMakeWithdrawalException {
		
		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		
		//add and withdrawal the same deposit
		bankService.deposit(clientID, firstDeposit);
		bankService.makeWithdrawal(clientID, firstDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

	@Test
	public void makeWithdrawalRandomValueTest() throws CantMakeWithdrawalException {
		
		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float withdrawal = 22.14f;
		float rest = (firstDeposit + secondeDeposit) - withdrawal;
		
		//add and withdrawal the seconde deposit
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);
		bankService.makeWithdrawal(clientID, withdrawal);
		assertEquals(currentClient.getBalance(), rest);
	}

	@Test
	public void makeWithdrawalAllTest() throws CantMakeWithdrawalException {

		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		//Add multiple deposit and withdraw all
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);
		bankService.deposit(clientID, thirdDeposit);
		bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

	@Test
	public void showOperationsHistoryTest() throws CantMakeWithdrawalException {

		int clientID = 1;
		BankService bankService = new BankServiceImp();
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		
		float firstWithdrawal = 12f;
		float secondeWithdrawal = 15f;
		
		//Add multiple deposit and withdraw all
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);

		bankService.makeWithdrawal(clientID, firstWithdrawal);
		bankService.makeWithdrawal(clientID, secondeWithdrawal);
		
		System.out.println(bankService.getClientHistory(clientID));
	}

}
