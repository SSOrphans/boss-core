package org.ssor.boss.core.service;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ssor.boss.core.entity.Confirmation;
import org.ssor.boss.core.entity.ConfirmationType;
import org.ssor.boss.core.exception.InvalidConfirmationException;
import org.ssor.boss.core.repository.ConfirmationRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.ssor.boss.core.entity.ConfirmationType.USER_CONFIRMATION;

@ExtendWith(MockitoExtension.class)
public class ConfirmationServiceTests
{
  @Mock
  static ConfirmationRepository confirmationRepository;
  static ConfirmationService confirmationService;
  static List<Confirmation> mockConfirmations;
  static ConfirmationType TYPE_ID = USER_CONFIRMATION;
  static String HASH1 = UUID.randomUUID().toString();
  static String HASH2 = UUID.randomUUID().toString();
  static long GOOD_UNTIL = Instant.now().plusSeconds(60 * 60 * 24).toEpochMilli();
  static long EXPIRED = Instant.now().minusSeconds(60 * 60 * 24).toEpochMilli();

  @BeforeEach
  void setUp()
  {
    final var confirmation1 = new Confirmation(1, TYPE_ID, 165432476, HASH1, GOOD_UNTIL);
    final var confirmation2 = new Confirmation(2, TYPE_ID, 789843544, HASH2, EXPIRED);
    mockConfirmations = Lists.newArrayList(confirmation1, confirmation2);
    confirmationService = new ConfirmationService(confirmationRepository);
    assertThat(confirmationService).isNotNull();
  }

  @AfterEach
  void tearDown()
  {
    mockConfirmations = null;
    confirmationService = null;
  }

  @Test
  void test_GenerateConfirmation_GeneratesAConfirmationEntrySuccessfully()
  {
    // Just mock the result.
    final var hash = UUID.randomUUID().toString();
    final var generationResult = new Confirmation(3, USER_CONFIRMATION, 238343, hash, 0);
    doReturn(generationResult).when(confirmationRepository).save(any());

    final var result = confirmationService.generateConfirmation(USER_CONFIRMATION, 238343);
    verify(confirmationRepository, atMostOnce()).save(any());
    assertThat(result.getId()).isEqualTo(3);
    assertThat(result.getConfirmationHash()).isNotBlank();
    assertThat(result.getGoodUntil()).isNotNull();
  }

  @Test
  void test_Confirm_ConfirmsEntrySuccessfully()
  {
    final var confirmation = mockConfirmations.get(0);
    doReturn(Optional.of(confirmation)).when(confirmationRepository).findByConfirmationHash(eq(HASH1));
    doAnswer(invocationOnMock -> mockConfirmations.remove(confirmation)).when(confirmationRepository).delete(any());

    final var now = Instant.now().toEpochMilli();
    assertThat(confirmation.getGoodUntil()).isGreaterThan(now);

    final var result = confirmationService.confirm(HASH1);
    verify(confirmationRepository, atMostOnce()).findByConfirmationHash(HASH1);
    verify(confirmationRepository, atMostOnce()).delete(any());
    assertThat(result).isEqualTo(165432476); // User id.
  }

  @Test
  void test_ConfirmProducesInvalidConfirmationException_WhenConfirmationCodeInvalid()
  {
    doReturn(Optional.empty()).when(confirmationRepository).findByConfirmationHash(any());
    assertThatThrownBy(() -> confirmationService.confirm("asdf")).isInstanceOf(InvalidConfirmationException.class)
                                                                 .hasMessage("Confirmation doesn't exist");

    verify(confirmationRepository, atMostOnce()).findByConfirmationHash(any());
    verify(confirmationRepository, never()).delete(any());
  }

  @Test
  void test_ConfirmProducesInvalidConfirmationException_WhenConfirmationExpired()
  {
    final var confirmation = mockConfirmations.get(1);
    doReturn(Optional.of(confirmation)).when(confirmationRepository).findByConfirmationHash(eq(HASH2));
    assertThatThrownBy(() -> confirmationService.confirm(HASH2)).isInstanceOf(InvalidConfirmationException.class)
                                                                .hasMessage("Confirmation is expired");

    verify(confirmationRepository, atMostOnce()).findByConfirmationHash(HASH2);
    verify(confirmationRepository, never()).delete(any());
  }
}
