package org.ssor.boss.core.transfer;

import lombok.*;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class TransactionInput
{
  @NonNull
  private Long accountId;

  @Min(0)
  private Float amount;
  private String merchant;
  private LocalDateTime date;
  private String type;

}
