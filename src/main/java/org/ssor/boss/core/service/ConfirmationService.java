package org.ssor.boss.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssor.boss.core.entity.Confirmation;
import org.ssor.boss.core.entity.ConfirmationType;
import org.ssor.boss.core.exception.InvalidConfirmationException;
import org.ssor.boss.core.repository.ConfirmationRepository;
import org.ssor.boss.core.transfer.GenerateConfirmationOutput;
import java.time.Instant;
import java.util.UUID;

/**
 * A service for handling the generation and completion of confirmation entries within the confirmation table.
 * <p>
 *   Confirmation entries are generated from a small set of data, consisting of: the type of confirmation to generate,
 *   the id of the object to confirm, and the expiration time of the confirmation in milliseconds.
 * </p>
 *
 * @author John Christman
 */
@Service
@AllArgsConstructor
public class ConfirmationService
{
  // Measured in seconds.
  private static final int EXPIRATION_OFFSET = 60 * 60 * 24;
  private final ConfirmationRepository confirmationRepository;

  /**
   * Generates a confirmation entry given the input.
   *
   * @param confirmationType The type of the confirmation entry to insert into the confirmation table.
   * @param confirmableId The id of the object to confirm later.
   * @return The results of generating a confirmation, contains the confirmation id and the confirmation hash.
   */
  public GenerateConfirmationOutput generateConfirmation(ConfirmationType confirmationType, int confirmableId)
  {
    final var confirmationCode = UUID.randomUUID().toString();
    final var goodUntil = Instant.now().plusSeconds(EXPIRATION_OFFSET).toEpochMilli();
    final var confirmation = new Confirmation(null, confirmationType, confirmableId, confirmationCode, goodUntil);
    final var result = confirmationRepository.save(confirmation);
    return new GenerateConfirmationOutput(result.getId(), confirmationCode, goodUntil);
  }

  /**
   * Deletes a confirmation entry, as it is being confirmed.
   *
   * @param confirmationCode The hash of the confirmation entry to confirm.
   * @return The confirmable id to finalize confirmation of.
   */
  public int confirm(String confirmationCode) throws InvalidConfirmationException
  {
    final var optionalConfirmation = confirmationRepository.findByConfirmationHash(confirmationCode);
    if (optionalConfirmation.isEmpty())
      throw new InvalidConfirmationException("Confirmation doesn't exist");

    final var confirmation = optionalConfirmation.get();
    final var currentTime = Instant.now().toEpochMilli();
    if (confirmation.getGoodUntil() < currentTime)
      throw new InvalidConfirmationException("Confirmation is expired");

    confirmationRepository.delete(confirmation);
    return confirmation.getConfirmableId();
  }
}
