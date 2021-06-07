package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data transfer object for finalizing the confirmation of an entity.
 *
 * @author John Christman
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizeConfirmationInput
{
  private String confirmationCode;
}
