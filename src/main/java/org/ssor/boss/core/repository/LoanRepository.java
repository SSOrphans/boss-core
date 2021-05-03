package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Loan;

import java.util.List;

/**
 * @author Derrian Harris
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    Loan findByUserIdAndId(@Param("userId") Integer userId, @Param("id") Integer id);

    List<Loan> findByUserId(@Param("userId") Integer userId);

    List<Loan> findByBranchId(@Param("branchId") Integer branchId);
}
