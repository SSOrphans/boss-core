package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizeConfirmationInput
{
  private String confirmationCode;
}
