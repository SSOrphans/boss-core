package org.ssor.boss.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.ssor.boss.core.entity.Loan;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ssor.boss.core.entity.LoanTypeEnum.LOAN_STUDENT;
import static org.ssor.boss.core.entity.LoanTypeEnum.LOAN_UNKNOWN;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    public LoanRepository loanRepository;

    @Test
    public void test_CanFindById() {
        Optional<Loan> resultOpt = loanRepository.findById(1);
        assertThat(resultOpt.isPresent()).isTrue();
        Loan result = resultOpt.get();
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanNumber()).isNotNull().isEqualTo("1234567890");
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LOAN_STUDENT);
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
        assertThat(result.getLoanNumber()).isNotNull().isEqualTo("1234567890");
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LOAN_STUDENT);
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
        Pageable page = PageRequest.of(0, 2);
        List<Loan> results = loanRepository.findAllByUserIdAndLoanNumberStartsWithAndLoanTypeIs(1, "", LOAN_UNKNOWN, page).getContent();
        assertThat(results).isNotNull().isNotEmpty();
        Loan result = results.get(0);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanNumber()).isNotNull().isEqualTo("1234567890");
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LOAN_STUDENT);
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
        Pageable page = PageRequest.of(0, 2);
        List<Loan> results = loanRepository.findAllByBranchIdAndLoanNumberStartsWithAndLoanTypeIs(1, "", LOAN_UNKNOWN, page).getContent();
        assertThat(results).isNotNull().isNotEmpty();
        Loan result = results.get(0);
        assertThat(result.getId()).isNotNull().isEqualTo(1);
        assertThat(result.getLoanNumber()).isNotNull().isEqualTo("1234567890");
        assertThat(result.getLoanType()).isNotNull().isEqualTo(LOAN_STUDENT);
        assertThat(result.getUserId()).isNotNull().isEqualTo(1);
        assertThat(result.getBranchId()).isNotNull().isEqualTo(1);
        assertThat(result.getAmount()).isNotNull().isEqualTo(50000);
        assertThat(result.getAmountDue()).isNotNull().isEqualTo(25000);
        assertThat(result.getDueBy()).isNotNull();
        assertThat(result.getTakenAt()).isNotNull();
        assertThat(result.getInterestRate()).isNotNull().isEqualTo(0.02f);
    }
}
