package org.ssor.boss.core.entity;

public enum AccountType
{
  ACCOUNT_INVALID,
  ACCOUNT_SAVING,
  ACCOUNT_CHECKING,
  ACCOUNT_CREDIT;

  public int index() { return ordinal();}

  @Override
  public String toString()
  {
    switch (this)
    {
      case ACCOUNT_SAVING: return "ACCOUNT_SAVING";
      case ACCOUNT_CHECKING: return "ACCOUNT_CHECKING";
      case ACCOUNT_CREDIT: return "ACCOUNT_CREDIT";
      default: return "ACCOUNT_INVALID";
    }
  }
}
