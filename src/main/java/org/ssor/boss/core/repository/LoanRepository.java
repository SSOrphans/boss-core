package org.ssor.boss.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Loan;

import java.util.List;

/**
 * @author Derrian Harris
 */
@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {
    Loan findByUserIdAndId(@Param("userId") Integer userId, @Param("id") Integer id);

    List<Loan> findByUserId(@Param("userId") Integer userId, Pageable pageable);

    List<Loan> findByBranchId(@Param("branchId") Integer branchId, Pageable pageable);
}
