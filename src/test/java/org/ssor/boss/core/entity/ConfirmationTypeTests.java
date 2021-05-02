package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.ConfirmationType.ACCOUNT_CONFIRMATION;
import static org.ssor.boss.core.entity.ConfirmationType.CARD_CONFIRMATION;
import static org.ssor.boss.core.entity.ConfirmationType.LOAN_CONFIRMATION;
import static org.ssor.boss.core.entity.ConfirmationType.UNKNOWN_CONFIRMATION;
import static org.ssor.boss.core.entity.ConfirmationType.USER_CONFIRMATION;

public class ConfirmationTypeTests
{
  @Test
  void test_CanGetIndexOfConfirmationType()
  {
    assertThat(UNKNOWN_CONFIRMATION.index()).isEqualTo(0);
    assertThat(USER_CONFIRMATION.index()).isEqualTo(1);
    assertThat(ACCOUNT_CONFIRMATION.index()).isEqualTo(2);
    assertThat(CARD_CONFIRMATION.index()).isEqualTo(3);
    assertThat(LOAN_CONFIRMATION.index()).isEqualTo(4);
  }

  @Test
  void test_CanConvertEnumerationToString()
  {
    assertThat(UNKNOWN_CONFIRMATION.toString()).isEqualTo("UNKNOWN_CONFIRMATION");
    assertThat(USER_CONFIRMATION.toString()).isEqualTo("USER_CONFIRMATION");
    assertThat(ACCOUNT_CONFIRMATION.toString()).isEqualTo("ACCOUNT_CONFIRMATION");
    assertThat(CARD_CONFIRMATION.toString()).isEqualTo("CARD_CONFIRMATION");
    assertThat(LOAN_CONFIRMATION.toString()).isEqualTo("LOAN_CONFIRMATION");
  }
}
