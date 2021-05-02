package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateConfirmationOutput
{
  private final int id;
  private final String confirmationHash;
  private final long goodUntil;
}
