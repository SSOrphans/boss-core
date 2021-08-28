package org.ssor.boss.core.entity;

/**
 * Describes the different types of transactions.
 *
 * @author Bermond Jon Batistiana
 * @author John Christman
 */
public enum TransactionType {
	UNDEFINED,
	DEPOSIT,
	WITHDRAW,
	TRANSFER,
	PAYMENT,
	CHECK,
	CHARGE,
	ATM;

	@Override
	public String toString() {
		String result;
		switch (this) {
			case DEPOSIT:
				result = "Deposit";
				break;
			case WITHDRAW:
				result = "Withdraw";
				break;
			case TRANSFER:
				result = "Transfer";
				break;
			case PAYMENT:
				result = "Payment";
				break;
			case CHECK:
				result = "Check";
				break;
			case CHARGE:
				result = "Charge";
				break;
			case ATM:
				result = "Atm";
				break;
			default:
				result = "Undefined";
				break;
		}

		return result;
	}
}
