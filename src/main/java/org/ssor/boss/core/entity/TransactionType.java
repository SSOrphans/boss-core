package org.ssor.boss.core.entity;

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
    switch (this)
    {
      case TRANSACTION_DEPOSIT:
        return "TRANSACTION_DEPOSIT";
      case TRANSACTION_WITHDRAW:
        return "TRANSACTION_WITHDRAW";
      case TRANSACTION_TRANSFER:
        return "TRANSACTION_TRANSFER";
      case TRANSACTION_PAYMENT:
        return "TRANSACTION_PAYMENT";
      case TRANSACTION_CHECK:
        return "TRANSACTION_CHECK";
      case TRANSACTION_CHARGE:
        return "TRANSACTION_CHARGE";
      case TRANSACTION_ATM:
        return "TRANSACTION_ATM";
      default:
        return "TRANSACTION_INVALID";
    }
  }
}
