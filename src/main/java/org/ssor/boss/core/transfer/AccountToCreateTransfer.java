package org.ssor.boss.core.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class AccountToCreateTransfer
{
  @JsonProperty("account_type")
  @NotNull
  private Integer accountType;
  @JsonProperty("user_id")
  @NotNull
  private Integer userId;
  @JsonProperty("branch_id")
  private Integer branchId = 0;
}
