package org.ssor.boss.core.entity;

/**
 * Describes the type of a confirmation entry.
 * <p>
 *   <code>ConfirmationType</code> is used to determine what the type of a confirmation's <code>confirmable_id</code> is.
 *   Without this it would be impossible to know what was being confirmed through the confirmation table.
 * </p>
 *
 * @author John Christman
 */
public enum ConfirmationType
{
  UNKNOWN_CONFIRMATION,
  USER_CONFIRMATION,
  ACCOUNT_CONFIRMATION,
  CARD_CONFIRMATION,
  LOAN_CONFIRMATION;

  /**
   * Simplified and more intuitive naming scheme for the enumeration value.
   * <p>
   *   Wraps the {@link #ordinal()} function, because Java thought it was a good meme to name the function something
   *   that most people will not understand, and then provide documentation about the function that only vaguely makes
   *   sense.
   * </p>
   *
   * @return The ordinal number of the enumeration.
   */
  public int index()
  {
    return ordinal();
  }

  /**
   * Gets the appropriate name for the different enumerations.
   * <p>
   *   Each enumeration represents an entry within the <code>confirmation_type</code> table of the <i>boss</i> database.
   *   The produced name directly reflects the name of the type of the confirmation. This is important for checking and
   *   using confirmation codes and confirmable ids.
   * </p>
   *
   * @return The generated string for the confirmation type.
   */
  @Override
  public String toString()
  {
    String result = "";
    switch (this)
    {
      case UNKNOWN_CONFIRMATION:
        result = "UNKNOWN_CONFIRMATION"; break;
      case USER_CONFIRMATION:
        result = "USER_CONFIRMATION"; break;
      case ACCOUNT_CONFIRMATION:
        result = "ACCOUNT_CONFIRMATION"; break;
      case CARD_CONFIRMATION:
        result = "CARD_CONFIRMATION"; break;
      case LOAN_CONFIRMATION:
        result = "LOAN_CONFIRMATION"; break;
    }

    return result;
  }
}
