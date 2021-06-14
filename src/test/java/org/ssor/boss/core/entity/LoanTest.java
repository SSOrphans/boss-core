package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import org.ssor.boss.core.transfer.LoanDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

<<<<<<< HEAD
public class LoanTest
{
  @Test
  void test_CanCreateEmptyLoan()
  {
    Loan loan = new Loan();
    assertThat(loan).isNotNull();
  }

  @Test
  void test_CanCreateAllArgsLoan()
  {
    LoanType loanType = LoanType.LOAN_STUDENT;
    Loan loan = new Loan(1, "1", loanType, 1, 1, 1f, 1f, LocalDateTime.of(2021, 1, 1, 0, 0), LocalDate.of(2022, 1, 1), 1f);
    assertThat(loan).isNotNull();
  }

  @Test
  void test_CanEvalEqual()
  {
    LoanType loanType = LoanType.LOAN_STUDENT;
    Loan loanA = new Loan();
    Loan loanB = new Loan();
    Loan loanC = new Loan();
    loanA.setId(1);
    loanA.setUserId(1);
    loanA.setBranchId(1);
    loanA.setAmount(1f);
    loanA.setAmountDue(1f);
    loanA.setInterestRate(1f);
    loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanA.setDueBy(LocalDate.of(2022, 1, 1));
    loanA.setType(loanType);

    loanB.setId(1);
    loanB.setUserId(1);
    loanB.setBranchId(1);
    loanB.setAmount(1f);
    loanB.setAmountDue(1f);
    loanB.setInterestRate(1f);
    loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanB.setDueBy(LocalDate.of(2022, 1, 1));
    loanB.setType(loanType);

    loanC.setId(2);
    loanC.setUserId(2);
    loanC.setBranchId(2);
    loanC.setAmount(2f);
    loanC.setAmountDue(2f);
    loanC.setInterestRate(2f);
    loanC.setTakenAt(LocalDateTime.of(2022, 1, 1, 0, 0));
    loanC.setDueBy(LocalDate.of(2023, 1, 1));
    loanC.setType(LoanType.LOAN_PERSONAL);

    assertThat(loanA).isEqualTo(loanB);
    assertThat(loanA).isNotEqualTo(loanC);
  }

  @Test
  void test_CanConvertToLoanDto()
  {
    LoanType loanType = LoanType.LOAN_STUDENT;

    Loan loanA = new Loan();
    loanA.setId(1);
    loanA.setLoanNumber("1");
    loanA.setUserId(1);
    loanA.setBranchId(1);
    loanA.setAmount(1f);
    loanA.setAmountDue(1f);
    loanA.setInterestRate(1f);
    loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
    loanA.setDueBy(LocalDate.of(2022, 1, 1));
    loanA.setType(loanType);

    LoanDto loanDtoB = new LoanDto();
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

    assertThat(loanA.convertToLoanDto()).isEqualTo(loanDtoB);
  }
=======
public class LoanTest {
    @Test
    void test_CanCreateEmptyLoan() {
        Loan loan = new Loan();
        assertThat(loan).isNotNull();
    }


    @Test
    void test_CanCreateAllArgsLoan() {
        LoanTypeEnum loanTypeEnum = LoanTypeEnum.LOAN_STUDENT;
        Loan loan = new Loan(1, 1, 1, "1", 1f, 1f, LocalDateTime.of(2021, 1, 1, 0, 0), LocalDate.of(2022, 1, 1), 1f, loanTypeEnum);
        assertThat(loan).isNotNull();
    }

    @Test
    void test_CanEvalEqual() {
        LoanTypeEnum loanTypeEnum = LoanTypeEnum.LOAN_STUDENT;
        Loan loanA = new Loan();
        Loan loanB = new Loan();
        Loan loanC = new Loan();
        loanA.setId(1);
        loanA.setUserId(1);
        loanA.setLoanNumber("1");
        loanA.setBranchId(1);
        loanA.setAmount(1f);
        loanA.setAmountDue(1f);
        loanA.setInterestRate(1f);
        loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanA.setDueBy(LocalDate.of(2022, 1, 1));
        loanA.setLoanType(loanTypeEnum);


        loanB.setId(1);
        loanB.setUserId(1);
        loanB.setLoanNumber("1");
        loanB.setBranchId(1);
        loanB.setAmount(1f);
        loanB.setAmountDue(1f);
        loanB.setInterestRate(1f);
        loanB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanB.setDueBy(LocalDate.of(2022, 1, 1));
        loanB.setLoanType(loanTypeEnum);

        loanC.setId(2);
        loanC.setUserId(2);
        loanC.setLoanNumber("2");
        loanC.setBranchId(2);
        loanC.setAmount(2f);
        loanC.setAmountDue(2f);
        loanC.setInterestRate(2f);
        loanC.setTakenAt(LocalDateTime.of(2022, 1, 1, 0, 0));
        loanC.setDueBy(LocalDate.of(2023, 1, 1));
        loanC.setLoanType(LoanTypeEnum.LOAN_PERSONAL);

        assertThat(loanA).isEqualTo(loanB);
        assertThat(loanA).isNotEqualTo(loanC);
    }

    @Test
    void test_CanConvertToLoanDto() {
        LoanTypeEnum loanTypeEnum = LoanTypeEnum.LOAN_STUDENT;

        Loan loanA = new Loan();
        loanA.setId(1);
        loanA.setLoanNumber("1");
        loanA.setUserId(1);
        loanA.setBranchId(1);
        loanA.setAmount(1f);
        loanA.setAmountDue(1f);
        loanA.setInterestRate(1f);
        loanA.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanA.setDueBy(LocalDate.of(2022, 1, 1));
        loanA.setLoanType(loanTypeEnum);

        LoanDto loanDtoB = new LoanDto();
        loanDtoB.setId(1);
        loanDtoB.setLoanNumber("1");
        loanDtoB.setUserId(1);
        loanDtoB.setBranchId(1);
        loanDtoB.setAmount(1f);
        loanDtoB.setAmountDue(1f);
        loanDtoB.setInterestRate(1f);
        loanDtoB.setTakenAt(LocalDateTime.of(2021, 1, 1, 0, 0));
        loanDtoB.setDueBy(LocalDate.of(2022, 1, 1));
        loanDtoB.setLoanType(loanTypeEnum);

        assertThat(loanA.convertToLoanDto()).isEqualTo(loanDtoB);
    }
>>>>>>> 9c6c262547c1c3abb877a15b08c1c6908e7e6df7
}
