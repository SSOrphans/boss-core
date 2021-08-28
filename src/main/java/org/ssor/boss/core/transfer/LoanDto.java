package org.ssor.boss.core.transfer;

import lombok.Data;
import org.ssor.boss.core.entity.AccountEntity;
import org.ssor.boss.core.entity.LoanEntity;
import org.ssor.boss.core.entity.LoanType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Derrian Harris
 */
@Data
public class LoanDto implements Serializable
{
  private long id;
  private String loanNumber;
  private LoanType type;
  private int userId;
  private int branchId;
  private float totalAmount;
  private float amountDue;
  private float interestRate;
  private LocalDateTime takenAt;
  private LocalDate dueBy;
}
