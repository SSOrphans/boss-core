package org.ssor.boss.core.transfer;

import lombok.Data;
import org.ssor.boss.core.entity.Account;
import org.ssor.boss.core.entity.Loan;
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
  private Integer id;
  @NotNull
  private String loanNumber;
  private LoanType type;
  private int userId;
  private int branchId;
  private float totalAmount;
  private float interestRate;
  private LocalDateTime takenAt;
  private LocalDate dueBy;
  private Account account;

  public Loan convertToLoanEntity()
  {
    Loan loan = new Loan();
    loan.setId(id);
    loan.setType(type);
    loan.setLoanNumber(loanNumber);
    loan.setTotalAmount(totalAmount);
    loan.setUserId(userId);
    loan.setBranchId(branchId);
    loan.setInterestRate(interestRate);
    loan.setTakenAt(takenAt);
    loan.setAccount(account);
    loan.setDueBy(dueBy);
    return loan;
  }
}
