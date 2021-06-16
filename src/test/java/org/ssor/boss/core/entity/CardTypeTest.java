package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.CardType.CARD_GOLD;
import static org.ssor.boss.core.entity.CardType.CARD_PLAIN;
import static org.ssor.boss.core.entity.CardType.CARD_PLATINUM;

/**
 * @author Derrian Harris
 */
public class CardTypeTest
{
  @Test
  void test_CanGetIndexOfUserType()
  {
    assertThat(CARD_PLAIN.index()).isEqualTo(0);
    assertThat(CARD_GOLD.index()).isEqualTo(1);
    assertThat(CARD_PLATINUM.index()).isEqualTo(2);
  }

  @Test
  void test_CanConvertEnumerationToString()
  {
    assertThat(CARD_PLAIN.toString()).isEqualTo("Plain");
    assertThat(CARD_GOLD.toString()).isEqualTo("Gold");
    assertThat(CARD_PLATINUM.toString()).isEqualTo("Platinum");
  }
}
