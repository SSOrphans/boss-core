package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

/**
 * A data transfer object for the result of generating a confirmation code.
 *
 * @author John Christman
 */
@Data
@AllArgsConstructor
public class GenerateConfirmationOutput implements Serializable
{
  private final int id;
  private final String confirmationHash;
  private final long goodUntil;
}
