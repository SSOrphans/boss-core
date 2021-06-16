package org.ssor.boss.core.transfer;

import org.junit.jupiter.api.Test;
import org.ssor.boss.core.entity.Card;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.CardType.CARD_GOLD;
import static org.ssor.boss.core.entity.CardType.CARD_PLAIN;

/**
 * @author Derrian Harris
 */
public class CardDtoTest
{
  @Test
  void test_CanCreateEmptyCardDto()
  {
    CardDto cardDto = new CardDto();
    assertThat(cardDto).isNotNull();
  }

  @Test
  void test_CanEvalEqual()
  {
    long currTime = Instant.now().toEpochMilli();
    long expiry = LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    CardDto cardDtoA = new CardDto();
    CardDto cardDtoB = new CardDto();
    CardDto cardDtoC = new CardDto();

    cardDtoA.setId(1);
    cardDtoA.setCardType(CARD_PLAIN);
    cardDtoA.setNumberHash("1234123412341234");
    cardDtoA.setAccountId(1);
    cardDtoA.setCreated(currTime);
    cardDtoA.setExpirationDate(expiry);
    cardDtoA.setPin("1111");
    cardDtoA.setCvv("111");
    cardDtoA.setActive(false);
    cardDtoA.setConfirmed(false);
    cardDtoA.setStolen(false);

    cardDtoB.setId(1);
    cardDtoB.setCardType(CARD_PLAIN);
    cardDtoB.setNumberHash("1234123412341234");
    cardDtoB.setAccountId(1);
    cardDtoB.setCreated(currTime);
    cardDtoB.setExpirationDate(expiry);
    cardDtoB.setPin("1111");
    cardDtoB.setCvv("111");
    cardDtoB.setActive(false);
    cardDtoB.setConfirmed(false);
    cardDtoB.setStolen(false);

    long otherCreated = LocalDateTime.of(2021, 1, 1, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli();
    long otherExpiry = LocalDate.of(2026, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    cardDtoC.setId(2);
    cardDtoC.setCardType(CARD_GOLD);
    cardDtoC.setNumberHash("1234123412341234");
    cardDtoC.setAccountId(2);
    cardDtoC.setCreated(otherCreated);
    cardDtoC.setExpirationDate(otherExpiry);
    cardDtoC.setPin("2222");
    cardDtoC.setCvv("222");
    cardDtoC.setActive(true);
    cardDtoC.setConfirmed(true);
    cardDtoC.setStolen(true);

    assertThat(cardDtoA).isEqualTo(cardDtoB);
    assertThat(cardDtoA).isNotEqualTo(cardDtoC);
  }

  @Test
  void test_CanConvertToCardEntity()
  {
    long currTime = Instant.now().toEpochMilli();
    long expiry = LocalDate.of(2025, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    CardDto cardDtoA = new CardDto();

    cardDtoA.setId(1);
    cardDtoA.setCardType(CARD_PLAIN);
    cardDtoA.setNumberHash("1234123412341234");
    cardDtoA.setAccountId(1);
    cardDtoA.setCreated(currTime);
    cardDtoA.setExpirationDate(expiry);
    cardDtoA.setPin("1111");
    cardDtoA.setCvv("111");
    cardDtoA.setActive(false);
    cardDtoA.setConfirmed(false);
    cardDtoA.setStolen(false);

    Card cardB = new Card();
    cardB.setId(1);
    cardB.setType(CARD_PLAIN);
    cardB.setNumberHash("1234123412341234");
    cardB.setAccountId(1);
    cardB.setCreated(currTime);
    cardB.setExpirationDate(expiry);
    cardB.setPin("1111");
    cardB.setCvv("111");
    cardB.setActive(false);
    cardB.setConfirmed(false);
    cardB.setStolen(false);

    assertThat(cardDtoA.convertToCardEntity()).isEqualTo(cardB);
  }
}
