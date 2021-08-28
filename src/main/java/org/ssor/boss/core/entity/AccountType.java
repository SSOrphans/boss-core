package org.ssor.boss.core.entity;

/**
 * Describes the different types of accounts.
 *
 * @author Bermond Jon Batistiana
 * @author John Christman
 */
public enum AccountType {
	UNDEFINED,
	SAVINGS,
	CHECKING,
	CREDIT,
	LOAN;

	@Override
	public String toString() {
		String result;
		switch (this) {
			case SAVINGS:
				result = "Savings";
				break;
			case CHECKING:
				result = "Checking";
				break;
			case CREDIT:
				result = "Credit";
				break;
			case LOAN:
				result = "Loan";
				break;
			default:
				result = "Undefined";
				break;
		}

		return result;
	}
}
