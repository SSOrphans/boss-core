package org.ssor.boss.core.transfer;

import org.junit.jupiter.api.Test;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.entity.LoanType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class LoanDtoTest
{
  @Test
  void test_CanCreateEmptyLoanDto()
  {
    LoanDto loanDto = new LoanDto();
    assertThat(loanDto).isNotNull();
  }

  @Test
  void test_CanEvalEqual()
  {
    LoanDto loanDtoA = new LoanDto();
    LoanDto loanDtoB = new LoanDto();
    LoanDto loanDtoC = new LoanDto();
    LoanType loanType = LoanType.LOAN_STUDENT;
    loanDtoA.setId(1);
    loanDtoA.setLoanNumber("1");
    loanDtoA.setUserId(1);
    loanDtoA.setBranchId(1);
    loanDtoA.setAmount(1f);
    loanDtoA.setAmountDue(1f);
    loanDtoA.setInterestRate(1f);
    loanDtoA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanDtoA.setDueBy(LocalDate.of(2022, 1, 1));
    loanDtoA.setType(loanType);

    loanDtoB.setId(1);
    loanDtoB.setLoanNumber("1");
    loanDtoB.setUserId(1);
    loanDtoB.setBranchId(1);
    loanDtoB.setAmount(1f);
    loanDtoB.setAmountDue(1f);
    loanDtoB.setInterestRate(1f);
    loanDtoB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanDtoB.setDueBy(LocalDate.of(2022, 1, 1));
    loanDtoB.setType(loanType);

    loanDtoC.setId(2);
    loanDtoC.setLoanNumber("2");
    loanDtoC.setUserId(2);
    loanDtoC.setBranchId(2);
    loanDtoC.setAmount(2f);
    loanDtoC.setAmountDue(2f);
    loanDtoC.setInterestRate(2f);
    loanDtoC.setTakenAt(LocalDateTime.of(2022, 1, 1, 0, 0));
    loanDtoC.setDueBy(LocalDate.of(2023, 1, 1));
    loanDtoC.setType(LoanType.LOAN_PERSONAL);
    assertThat(loanDtoA).isEqualTo(loanDtoB);
    assertThat(loanDtoA).isNotEqualTo(loanDtoC);
  }

  @Test
  void test_CanConvertToLoan()
  {
    LoanDto loanDtoA = new LoanDto();
    LoanType loanType = LoanType.LOAN_STUDENT;
    loanDtoA.setId(1);
    loanDtoA.setLoanNumber("1");
    loanDtoA.setUserId(1);
    loanDtoA.setBranchId(1);
    loanDtoA.setAmount(1f);
    loanDtoA.setAmountDue(1f);
    loanDtoA.setInterestRate(1f);
    loanDtoA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanDtoA.setDueBy(LocalDate.of(2022, 1, 1));
    loanDtoA.setType(loanType);

    Loan loanB = new Loan();
    loanB.setId(1);
    loanB.setLoanNumber("1");
    loanB.setUserId(1);
    loanB.setBranchId(1);
    loanB.setAmount(1f);
    loanB.setAmountDue(1f);
    loanB.setInterestRate(1f);
    loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanB.setDueBy(LocalDate.of(2022, 1, 1));
    loanB.setType(loanType);

    assertThat(loanDtoA.convertToLoanEntity()).isEqualTo(loanB);
  }
}
