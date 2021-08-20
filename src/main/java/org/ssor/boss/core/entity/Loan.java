package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.transfer.LoanDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  private float totalAmount;
  @Column(name = "interest_rate")
  private float interestRate;
  @Column(name = "taken_at")
  private LocalDateTime takenAt;
  @Column(name = "due_by")
  private LocalDate dueBy;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Loan loan = (Loan) o;
    return id == loan.id && loanNumber.equals(loan.loanNumber) && type == loan.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, loanNumber, type);
  }

  public LoanDto convertToLoanDto()
  {
    LoanDto loanDto = new LoanDto();
    loanDto.setId(id);
    loanDto.setLoanNumber(loanNumber);
    loanDto.setType(type);
    loanDto.setUserId(userId);
    loanDto.setBranchId(branchId);
    loanDto.setTotalAmount(totalAmount);
    loanDto.setInterestRate(interestRate);
    loanDto.setTakenAt(takenAt);
    loanDto.setDueBy(dueBy);
    loanDto.setAmountDue(account.getBalance());
    return loanDto;
  }
}
