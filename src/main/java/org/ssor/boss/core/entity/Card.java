package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.transfer.CardDto;
import org.ssor.boss.core.transfer.SecureCardDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Describes information on a physical debit card.
 *
 * @author Derrian Harris
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card", schema = "boss", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id"),
    @UniqueConstraint(columnNames = "number_hash")
})
public class Card implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  @Column(name = "type_id")
  private CardType type;
  @Column(name = "number_hash")
  private String numberHash;
  @Column(name = "last_four")
  private String lastFour;
  @Column(name = "user_id")
  private int userId;
  @Column(name = "account_id")
  private int accountId;
  private long created;
  @Column(name = "active_since")
  private long activeSince;
  @Column(name = "expiration_date")
  private long expirationDate;
  private String pin;
  private String cvv;
  private boolean confirmed;
  private boolean active;
  private boolean stolen;

  /**
   * Converts a card entity to an unsecure card transfer object.
   *
   * @deprecated because this class exposes sensitive information to a public portal when using {@link CardDto}.
   *             It should be replaced with {@link SecureCardDetails#SecureCardDetails(Card)}.
   * @return An unsecured card transfer object.
   */
  @Deprecated(since = "1.2.0", forRemoval = true)
  public CardDto convertToCardDto()
  {
    CardDto cardDto = new CardDto();

    cardDto.setId(id);
    cardDto.setNumberHash(numberHash);
    cardDto.setPin(pin);
    cardDto.setCvv(cvv);
    cardDto.setAccountId(accountId);
    cardDto.setCreated(created);
    cardDto.setActiveSince(activeSince);
    cardDto.setExpirationDate(expirationDate);
    cardDto.setConfirmed(confirmed);
    cardDto.setActive(active);
    cardDto.setStolen(stolen);
    cardDto.setCardType(type);
    return cardDto;
  }
}
