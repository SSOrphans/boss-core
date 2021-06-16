package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.transfer.LoanDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Describes the information of a loan.
 *
 * @author Derrian Harris
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan", schema = "boss", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id"),
    @UniqueConstraint(columnNames = "loan_number")
})
public class Loan implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "loan_number")
  private String loanNumber;
  @Enumerated
  @Column(name = "type_id")
  private LoanType type;
  @Column(name = "user_id")
  private int userId;
  @Column(name = "branch_id")
  private int branchId;
  private float amount;
  @Column(name = "interest_rate")
  private float interestRate;
  @Column(name = "taken_at")
  private LocalDateTime takenAt;
  @Column(name = "due_by")
  private LocalDate dueBy;
  @Column(name = "amount_due")
  private float amountDue;

  public LoanDto convertToLoanDto()
  {
    LoanDto loanDto = new LoanDto();
    loanDto.setId(id);
    loanDto.setLoanNumber(loanNumber);
    loanDto.setType(type);
    loanDto.setUserId(userId);
    loanDto.setBranchId(branchId);
    loanDto.setAmount(amount);
    loanDto.setInterestRate(interestRate);
    loanDto.setTakenAt(takenAt);
    loanDto.setDueBy(dueBy);
    loanDto.setAmountDue(amountDue);
    return loanDto;
  }
}
