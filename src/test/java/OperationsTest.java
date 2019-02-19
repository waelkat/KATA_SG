import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sg.bankaccount.model.Operation;

class OperationsTest {

	@Test
	public void createWithdrawalOperationTest() {
		Operation operation = new Operation();
		
		operation.setType(Operation.OPERATION_TYPE_WITHDRAWAL);
		operation.setAmount(15);
		operation.setClientBalance(15);

		assertEquals(operation.getAmount(), 15);
		assertEquals(operation.getClientBalance(), 15);
		assertEquals(operation.getType(), Operation.OPERATION_TYPE_WITHDRAWAL);

	}

	@Test
	public void createDepositeOperationTest() {
		Operation operation = new Operation();

		operation.setType(Operation.OPERATION_TYPE_DEPOSIT);
		operation.setAmount(15);
		operation.setClientBalance(15);
		
		assertEquals(operation.getAmount(), 15);
		assertEquals(operation.getClientBalance(), 15);
		assertEquals(operation.getType(), Operation.OPERATION_TYPE_DEPOSIT);

	}

}
