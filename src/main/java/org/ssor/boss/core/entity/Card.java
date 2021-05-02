package org.ssor.boss.core.entity;

import lombok.*;
import org.ssor.boss.core.transfer.CardDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Derrian Harris
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "card",schema = "boss")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "number_hash")
    private String numberHash;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "active_since")
    private LocalDate activeSince;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "pin")
    private Integer pin;

    @Column(name = "cvv")
    private Integer cvv;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "stolen")
    private Boolean stolen;

    @Enumerated
    @Column(name = "type_id")
    private CardType cardType;


    public CardDto convertToCardDto() {
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
        cardDto.setCardType(cardType);
        return cardDto;
    }
}
