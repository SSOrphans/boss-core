package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * A data transfer object for finalizing the confirmation of an entity.
 *
 * @author John Christman
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizeConfirmationInput implements Serializable
{
  private String confirmationCode;
}
