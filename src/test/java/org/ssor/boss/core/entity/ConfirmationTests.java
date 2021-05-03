package org.ssor.boss.core.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.ConfirmationType.USER_CONFIRMATION;

public class ConfirmationTests
{
  static final ConfirmationType TYPE_ID = USER_CONFIRMATION;
  static final String HASH = UUID.randomUUID().toString();
  static final long GOOD_UNTIL = Instant.now().toEpochMilli();

  static Confirmation confirmation1;
  static Confirmation confirmation2;
  static Confirmation confirmation3;

  @AfterEach
  void tearDown()
  {
    confirmation1 = null;
    confirmation2 = null;
    confirmation3 = null;
  }

  @Test
  void test_CanConstructWithoutParameters()
  {
    confirmation1 = new Confirmation();
    assertThat(confirmation1).isNotNull();
  }

  @Test
  void test_CanConstructWithAllParameters()
  {
    confirmation1 = new Confirmation(1, TYPE_ID, 1, HASH, GOOD_UNTIL);
    assertThat(confirmation1).isNotNull();
  }

  @Test
  void test_CanConstructWithBuilder()
  {
    confirmation1 = Confirmation.builder().id(1).type(USER_CONFIRMATION).confirmableId(1).confirmationHash(HASH)
                                .goodUntil(GOOD_UNTIL).build();
    assertThat(confirmation1).isNotNull();
    assertThat(confirmation1.getId()).isEqualTo(1);
    assertThat(confirmation1.getType()).isEqualTo(TYPE_ID);
    assertThat(confirmation1.getConfirmableId()).isEqualTo(1);
    assertThat(confirmation1.getConfirmationHash()).isEqualTo(HASH);
    assertThat(confirmation1.getGoodUntil()).isEqualTo(GOOD_UNTIL);
  }

  @Test
  void test_CanAssignWithSettersAndRetrieveWithGetters()
  {
    confirmation1 = new Confirmation();
    assertThat(confirmation1).isNotNull();

    confirmation1.setId(1);
    confirmation1.setType(TYPE_ID);
    confirmation1.setConfirmableId(1);
    confirmation1.setConfirmationHash(HASH);
    confirmation1.setGoodUntil(GOOD_UNTIL);

    assertThat(confirmation1.getId()).isEqualTo(1);
    assertThat(confirmation1.getType()).isEqualTo(TYPE_ID);
    assertThat(confirmation1.getConfirmableId()).isEqualTo(1);
    assertThat(confirmation1.getConfirmationHash()).isEqualTo(HASH);
    assertThat(confirmation1.getGoodUntil()).isEqualTo(GOOD_UNTIL);
  }

  @Test
  void test_CanCompareWithEquals()
  {
    confirmation1 = new Confirmation(1, TYPE_ID, 1, HASH, GOOD_UNTIL);
    confirmation2 = new Confirmation(2, TYPE_ID, 15646, HASH, GOOD_UNTIL);
    confirmation3 = new Confirmation(1, TYPE_ID, 1, HASH, GOOD_UNTIL);
    assertThat(confirmation2).isNotEqualTo(confirmation1);
    assertThat(confirmation3).isEqualTo(confirmation1);
  }

  @Test
  void test_CanCompareWithHashCode()
  {
    confirmation1 = new Confirmation(1, TYPE_ID, 1, HASH, GOOD_UNTIL);
    confirmation2 = new Confirmation(2, TYPE_ID, 15646, HASH, GOOD_UNTIL);
    confirmation3 = new Confirmation(1, TYPE_ID, 1, HASH, GOOD_UNTIL);
    assertThat(confirmation2.hashCode()).isNotEqualTo(confirmation1.hashCode());
    assertThat(confirmation3.hashCode()).isEqualTo(confirmation1.hashCode());
  }
}
