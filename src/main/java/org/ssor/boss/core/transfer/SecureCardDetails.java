package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.Card;
import org.ssor.boss.core.entity.CardType;

/**
 * Provides details about a a user's card in a safe manner.
 * <p>
 *   Because vendors can get information about a user, we must provide a secure way of getting information about a card.
 *   The information is generally limited. The results of getting a secure card is mostly for checking that a card
 *   exists, more so than getting that user's information.
 * </p>
 *
 * @author John Christman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecureCardDetails
{
  private int id;
  private CardType type;
  private short lastFour;
  private long created;
  private long activatedSince;
  private long expirationDate;
  private boolean confirmed;
  private boolean active;
  private boolean stolen;

  /**
   * Create {@link SecureCardDetails} from a {@link Card}.
   *
   * @param card The card to create from.
   */
  public SecureCardDetails(Card card)
  {
    id = card.getId();
    type = card.getType();
    lastFour = card.getLastFour();
    created = card.getCreated();
    activatedSince = card.getActiveSince();
    expirationDate = card.getExpirationDate();
    confirmed = card.isConfirmed();
    active = card.isActive();
    stolen = card.isStolen();
  }
}
