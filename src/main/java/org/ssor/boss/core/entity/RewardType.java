package org.ssor.boss.core.entity;

public enum RewardType
{
  REWARD_INVALID,
  REWARD_CASHBACK;

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
      case REWARD_CASHBACK:
        result = "REWARD_CASHBACK"; break;
      default:
        result = "REWARD_INVALID"; break;
    }
    return result;
  }
}
