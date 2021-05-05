package org.ssor.boss.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Loan;

/**
 * @author Derrian Harris
 */
@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
    Loan findByUserIdAndId(Integer userId, Integer id);

    Page<Loan> findAllByUserIdAndLoanNumberContains(Integer userId, String loanNumber, Pageable pageable);

    Page<Loan> findAllByBranchIdAndLoanNumberContains(Integer branchId, String loanNumber, Pageable pageable);

    Page<Loan> findAllByLoanNumberContains(String loanNumber, Pageable pageable);
}
