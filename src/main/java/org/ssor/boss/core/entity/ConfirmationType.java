package org.ssor.boss.core.entity;

/**
 * Describes the type of confirmation entry.
 *
 * <p>
 * <code>ConfirmationType</code> is used to determine what the type of confirmation's <code>confirmable</code> is.
 * Without this it would be impossible to know what was being confirmed through the confirmation table.
 *
 * @author John Christman
 */
public enum ConfirmationType {
	UNDEFINED,
	USER,
	ACCOUNT,
	CARD,
	LOAN;

	/**
	 * Gets the appropriate name for the different enumerations.
	 * <p>
	 * Each enumeration represents an entry within the <code>confirmation_type</code> table of the <i>boss</i> database.
	 * The produced name directly reflects the name of the type of the confirmation. This is important for checking and
	 * using confirmation codes and confirmable ids.
	 * </p>
	 *
	 * @return The generated string for the confirmation type.
	 */
	@Override
	public String toString() {
		String result;
		switch (this) {
			case USER:
				result = "User confirmation";
				break;
			case ACCOUNT:
				result = "Account confirmation";
				break;
			case CARD:
				result = "Card confirmation";
				break;
			case LOAN:
				result = "Loan confirmation";
				break;
			default:
				result = "Undefined";
				break;
		}

		return result;
	}
}
