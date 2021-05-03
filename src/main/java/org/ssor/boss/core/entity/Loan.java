package org.ssor.boss.core.entity;

import lombok.*;
import org.ssor.boss.core.transfer.LoanDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Derrian Harris
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Loan.findByUserIdAndId", query = "SELECT l FROM Loan l WHERE l.userId = :userId AND l.id = :id")
@NamedQuery(name = "Loan.findByUserId", query = "SELECT l FROM Loan l WHERE l.userId = :userId")
@NamedQuery(name = "Loan.findByBranchId", query = "SELECT l FROM Loan l WHERE l.branchId = :branchId")
@Table(name = "loan",schema = "boss")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "interest_rate")
    private Float interestRate;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;

    @Column(name = "due_by")
    private LocalDate dueBy;

    @Column(name = "amount_due")
    private Float amountDue;

    @Enumerated
    @Column(name = "type_id")
    private LoanType loanType;

    public LoanDto convertToLoanDto() {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(id);
        loanDto.setAmount(amount);
        loanDto.setLoanType(loanType);
        loanDto.setUserId(userId);
        loanDto.setBranchId(branchId);
        loanDto.setInterestRate(interestRate);
        loanDto.setTakenAt(takenAt);
        loanDto.setDueBy(dueBy);
        loanDto.setAmountDue(amountDue);
        return loanDto;
    }
}
