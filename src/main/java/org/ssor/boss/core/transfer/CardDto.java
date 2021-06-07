package org.ssor.boss.core.transfer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.Card;
import org.ssor.boss.core.entity.CardType;
import javax.validation.constraints.NotNull;

/**
 * @author Derrian Harris
 */
@Data
@NoArgsConstructor
@Deprecated(since = "1.2.0", forRemoval = true)
public class CardDto
{
  private int id;
  @NotNull
  private String numberHash;
  private int pin;
  private int cvv;
  private int accountId;
  private CardType cardType;
  private long created;
  private long activeSince;
  private long expirationDate;
  private boolean confirmed;
  private boolean active;
  private boolean stolen;

  public Card convertToCardEntity()
  {
    Card card = new Card();
    card.setId(id);
    card.setNumberHash(numberHash);
    card.setPin(pin);
    card.setCvv(cvv);
    card.setAccountId(accountId);
    card.setCreated(created);
    card.setActiveSince(activeSince);
    card.setExpirationDate(expirationDate);
    card.setConfirmed(confirmed);
    card.setActive(active);
    card.setStolen(stolen);
    card.setType(cardType);
    return card;
  }
}
