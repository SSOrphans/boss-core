package org.ssor.boss.core.transfer;

import lombok.Data;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.entity.LoanType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Derrian Harris
 */
@Data
public class LoanDto {
    private Integer id;
    @NotNull
    private Float amount;
    @NotNull
    private Float interestRate;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer branchId;
    @NotNull
    private LoanType loanType;
    @NotNull
    private LocalDate dueBy;
    private LocalDateTime takenAt;
    private Float amountDue;


    public Loan convertToLoanEntity() {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setAmount(amount);
        loan.setUserId(userId);
        loan.setBranchId(branchId);
        loan.setInterestRate(interestRate);
        loan.setTakenAt(takenAt);
        loan.setLoanType(loanType);
        loan.setAmountDue(amountDue);
        loan.setDueBy(dueBy);
        return loan;
    }
}
