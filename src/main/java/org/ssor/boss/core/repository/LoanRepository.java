package org.ssor.boss.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Loan;
import org.ssor.boss.core.entity.LoanType;

/**
 * A repository capable of paging out loan data.
 *
 * @author Derrian Harris
 */
@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer>
{
  Loan findByUserIdAndId(Integer userId, Integer id);

  @Query(value = "SELECT l from Loan l WHERE l.userId = :userId AND l.loanNumber LIKE concat(:loanNumber,'%')  AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = l.type)")
  Page<Loan> findAllByUserIdAndLoanNumberStartsWithAndLoanTypeIs(Integer userId, String loanNumber, LoanType filter, Pageable pageable);

  @Query(value = "SELECT l from Loan l WHERE l.branchId = :branchId AND l.loanNumber LIKE concat(:loanNumber,'%') AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = l.type)")
  Page<Loan> findAllByBranchIdAndLoanNumberStartsWithAndLoanTypeIs(Integer branchId, String loanNumber, LoanType filter, Pageable pageable);

  @Query(value = "SELECT l from Loan l WHERE l.loanNumber LIKE concat(:loanNumber,'%') AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = l.type)")
  Page<Loan> findAllByLoanNumberStartsWithAndLoanTypeIs(String loanNumber, LoanType filter, Pageable pageable);
}
