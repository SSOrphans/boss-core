package org.ssor.boss.core.entity;

/**
 * Describes the different types of transactions.
 *
 * @author Bermond Jon Batistiana
 */
public enum TransactionType
{
  TRANSACTION_INVALID,
  TRANSACTION_DEPOSIT,
  TRANSACTION_WITHDRAW,
  TRANSACTION_TRANSFER,
  TRANSACTION_PAYMENT,
  TRANSACTION_CHECK,
  TRANSACTION_CHARGE,
  TRANSACTION_ATM;

  public int index() { return ordinal();}

  @Override
  public String toString()
  {
    String result = "TRANSACTION_INVALID";
    switch (this)
    {
      case TRANSACTION_DEPOSIT:
        result = "TRANSACTION_DEPOSIT"; break;
      case TRANSACTION_WITHDRAW:
        result = "TRANSACTION_WITHDRAW"; break;
      case TRANSACTION_TRANSFER:
        result = "TRANSACTION_TRANSFER"; break;
      case TRANSACTION_PAYMENT:
        result = "TRANSACTION_PAYMENT"; break;
      case TRANSACTION_CHECK:
        result = "TRANSACTION_CHECK"; break;
      case TRANSACTION_CHARGE:
        result = "TRANSACTION_CHARGE"; break;
      case TRANSACTION_ATM:
        result = "TRANSACTION_ATM"; break;
    }

    return result;
  }
}
