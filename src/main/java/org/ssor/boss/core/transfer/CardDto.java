package org.ssor.boss.core.transfer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.Card;
import org.ssor.boss.core.entity.CardType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Derrian Harris
 */
@Data
@NoArgsConstructor
public class CardDto {
    private Integer id;
    @NotNull
    private String numberHash;
    @NotNull
    private Integer pin;
    @NotNull
    private Integer cvv;
    @NotNull
    private Integer accountId;
    @NotNull
    private CardType cardType;
    private LocalDateTime created;
    private LocalDate activeSince;
    private LocalDate expirationDate;
    private Boolean confirmed;
    private Boolean active;
    private Boolean stolen;



    public Card convertToCardEntity() {
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
        card.setCardType(cardType);
        return card;
    }
}
