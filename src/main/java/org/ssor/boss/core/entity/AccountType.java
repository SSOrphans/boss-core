package org.ssor.boss.core.entity;

/**
 * Describes the different types of accounts.
 *
 * @author Bermond Jon Batistiana
 */
public enum AccountType
{
  ACCOUNT_INVALID,
  ACCOUNT_SAVING,
  ACCOUNT_CHECKING,
  ACCOUNT_CREDIT;

  public int index()
  {
    return ordinal();
  }

  @Override
  public String toString()
  {
    String result;
    switch (this)
    {
      case ACCOUNT_SAVING:
        result = "ACCOUNT_SAVING"; break;
      case ACCOUNT_CHECKING:
        result = "ACCOUNT_CHECKING"; break;
      case ACCOUNT_CREDIT:
        result = "ACCOUNT_CREDIT"; break;
      default:
        result = "ACCOUNT_INVALID"; break;
    }

    return result;
  }
}
