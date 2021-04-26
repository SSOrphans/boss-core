package org.ssor.boss.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.entity.LoanType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    public LoanRepository loanRepository;

    @Test
    public void test_CanFindById() {
        Loan result = loanRepository.getOne(1);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LoanType.LOAN_STUDENT);
        assertThat(result.getUserId()).isNotNull().isEqualTo(1);
        assertThat(result.getBranchId()).isNotNull().isEqualTo(1);
        assertThat(result.getAmount()).isNotNull().isEqualTo(50000);
        assertThat(result.getAmountDue()).isNotNull().isEqualTo(25000);
        assertThat(result.getDueBy()).isNotNull();
        assertThat(result.getTakenAt()).isNotNull();
        assertThat(result.getInterestRate()).isNotNull().isEqualTo(0.02f);
    }

    @Test
    public void test_CanFindByUserIdAndId() {
        Loan result = loanRepository.findByUserIdAndId(1, 1);

        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LoanType.LOAN_STUDENT);
        assertThat(result.getUserId()).isNotNull().isEqualTo(1);
        assertThat(result.getBranchId()).isNotNull().isEqualTo(1);
        assertThat(result.getAmount()).isNotNull().isEqualTo(50000);
        assertThat(result.getAmountDue()).isNotNull().isEqualTo(25000);
        assertThat(result.getDueBy()).isNotNull();
        assertThat(result.getTakenAt()).isNotNull();
        assertThat(result.getInterestRate()).isNotNull().isEqualTo(0.02f);
    }

    @Test
    public void test_CanFindByUserId() {
        List<Loan> results = loanRepository.findByUserId(1);
        assertThat(results).isNotNull().isNotEmpty();
        Loan result = results.get(0);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LoanType.LOAN_STUDENT);
        assertThat(result.getUserId()).isNotNull().isEqualTo(1);
        assertThat(result.getBranchId()).isNotNull().isEqualTo(1);
        assertThat(result.getAmount()).isNotNull().isEqualTo(50000);
        assertThat(result.getAmountDue()).isNotNull().isEqualTo(25000);
        assertThat(result.getDueBy()).isNotNull();
        assertThat(result.getTakenAt()).isNotNull();
        assertThat(result.getInterestRate()).isNotNull().isEqualTo(0.02f);
    }

    @Test
    public void test_CanFindByBranchId() {
        List<Loan> results = loanRepository.findByBranchId(1);
        assertThat(results).isNotNull().isNotEmpty();
        Loan result = results.get(0);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LoanType.LOAN_STUDENT);
        assertThat(result.getUserId()).isNotNull().isEqualTo(1);
        assertThat(result.getBranchId()).isNotNull().isEqualTo(1);
        assertThat(result.getAmount()).isNotNull().isEqualTo(50000);
        assertThat(result.getAmountDue()).isNotNull().isEqualTo(25000);
        assertThat(result.getDueBy()).isNotNull();
        assertThat(result.getTakenAt()).isNotNull();
        assertThat(result.getInterestRate()).isNotNull().isEqualTo(0.02f);
    }
}
