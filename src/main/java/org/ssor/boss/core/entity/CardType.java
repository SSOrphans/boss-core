package org.ssor.boss.core.entity;

/**
 * Describes the different types of debit cards a user can have on their account.
 *
 * @author Derrian Harris
 */
public enum CardType
{
  CARD_PLAIN,
  CARD_GOLD,
  CARD_PLATINUM;

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
      case CARD_PLAIN:
        result = "Plain"; break;
      case CARD_GOLD:
        result = "Gold"; break;
      case CARD_PLATINUM:
        result = "Platinum"; break;
      default:
        result = "Undefined"; break;
    }

    return result;
  }
}
