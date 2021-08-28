package org.ssor.boss.core.entity;

/**
 * Describes the different types of cards a user can have on their account.
 *
 * @author Derrian Harris
 * @author John Christman
 */
public enum CardType {
	UNDEFINED,
	DEBIT,
	CREDIT;

	@Override
	public String toString() {
		String result;
		switch (this) {
			case DEBIT:
				result = "Debit";
				break;
			case CREDIT:
				result = "Credit";
				break;
			default:
				result = "Undefined";
				break;
		}

		return result;
	}
}
