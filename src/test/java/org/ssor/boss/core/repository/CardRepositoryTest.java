package org.ssor.boss.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ssor.boss.core.entity.Card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.CardType.CARD_PLAIN;

/**
 * @author Derrian Harris
 */
@DataJpaTest
public class CardRepositoryTest {
    @Autowired
    public CardRepository cardRepository;

    @Test
    public void test_CanFindById() {
        Card result = cardRepository.getOne(1);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getCardType()).isNotNull().isEqualTo(CARD_PLAIN);
        assertThat(result.getAccountId()).isNotNull().isEqualTo(1);
        assertThat(result.getNumberHash()).isNotNull();
        assertThat(result.getCreated()).isNotNull();
        assertThat(result.getActiveSince()).isNotNull();
        assertThat(result.getExpirationDate()).isNotNull();
        assertThat(result.getPin()).isNotNull().isEqualTo(1111);
        assertThat(result.getCvv()).isNotNull().isEqualTo(111);
        assertThat(result.getConfirmed()).isNotNull().isEqualTo(true);
        assertThat(result.getActive()).isNotNull().isEqualTo(true);
        assertThat(result.getStolen()).isNotNull().isEqualTo(false);
    }
}
