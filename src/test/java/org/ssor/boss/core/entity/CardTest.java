package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import org.ssor.boss.core.transfer.CardDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.CardType.CARD_GOLD;
import static org.ssor.boss.core.entity.CardType.CARD_PLAIN;

/**
 * @author Derrian Harris
 */
public class CardTest {
    @Test
    void test_CanCreateEmptyCardEntity() {
        Card card = new Card();
        assertThat(card).isNotNull();
    }

    @Test
    void test_CanEvalEqual() {
        LocalDateTime created = LocalDateTime.now();
        LocalDate expirationDate = LocalDate.of(2025, 1, 1);
        LocalDate activeSince = created.toLocalDate();
        Card cardA = new Card();
        Card cardB = new Card();
        Card cardC = new Card();

        cardA.setId(1);
        cardA.setCardType(CARD_PLAIN);
        cardA.setNumberHash("1234123412341234");
        cardA.setAccountId(1);
        cardA.setCreated(created);
        cardA.setActiveSince(activeSince);
        cardA.setExpirationDate(expirationDate);
        cardA.setPin(1111);
        cardA.setCvv(111);
        cardA.setActive(false);
        cardA.setConfirmed(false);
        cardA.setStolen(false);

        cardB.setId(1);
        cardB.setCardType(CARD_PLAIN);
        cardB.setNumberHash("1234123412341234");
        cardB.setAccountId(1);
        cardB.setCreated(created);
        cardB.setActiveSince(activeSince);
        cardB.setExpirationDate(expirationDate);
        cardB.setPin(1111);
        cardB.setCvv(111);
        cardB.setActive(false);
        cardB.setConfirmed(false);
        cardB.setStolen(false);

        cardC.setId(2);
        cardC.setCardType(CARD_GOLD);
        cardC.setNumberHash("1234123412341234");
        cardC.setAccountId(2);
        cardC.setCreated(LocalDateTime.of(2021, 1, 1, 0, 0));
        cardC.setActiveSince(cardC.getCreated().toLocalDate());
        cardC.setExpirationDate(LocalDate.of(2026, 1, 1));
        cardC.setPin(2222);
        cardC.setCvv(222);
        cardC.setActive(true);
        cardC.setConfirmed(true);
        cardC.setStolen(true);

        assertThat(cardA).isEqualTo(cardB);
        assertThat(cardA).isNotEqualTo(cardC);
    }

    @Test
    void test_CanConvertToCardDto() {
        LocalDateTime created = LocalDateTime.now();
        LocalDate expirationDate = LocalDate.of(2025, 1, 1);
        LocalDate activeSince = created.toLocalDate();
        Card cardA = new Card();
        cardA.setId(1);
        cardA.setCardType(CARD_PLAIN);
        cardA.setNumberHash("1234123412341234");
        cardA.setAccountId(1);
        cardA.setCreated(created);
        cardA.setActiveSince(activeSince);
        cardA.setExpirationDate(expirationDate);
        cardA.setPin(1111);
        cardA.setCvv(111);
        cardA.setActive(false);
        cardA.setConfirmed(false);
        cardA.setStolen(false);

        CardDto cardDtoB = new CardDto();
        cardDtoB.setId(1);
        cardDtoB.setCardType(CARD_PLAIN);
        cardDtoB.setNumberHash("1234123412341234");
        cardDtoB.setAccountId(1);
        cardDtoB.setCreated(created);
        cardDtoB.setActiveSince(activeSince);
        cardDtoB.setExpirationDate(expirationDate);
        cardDtoB.setPin(1111);
        cardDtoB.setCvv(111);
        cardDtoB.setActive(false);
        cardDtoB.setConfirmed(false);
        cardDtoB.setStolen(false);

        assertThat(cardA.convertToCardDto()).isEqualTo(cardDtoB);
    }
}
