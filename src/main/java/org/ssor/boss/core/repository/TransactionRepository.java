package org.ssor.boss.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Transaction;
import org.ssor.boss.core.entity.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
  @Query(value = "SELECT t FROM Transaction t WHERE t.accountId = :accountId AND t.id = :id")
  Transaction findTransactionById(Integer id, Long accountId);

  @Query(value = "SELECT t FROM Transaction t WHERE t.pending = true AND t.succeeded = false and t.date <= :start  order by t.date desc")
  List<Transaction> findPendingTransactionsByDate(LocalDateTime start);



  @Query(value = "SELECT t FROM Transaction t " +
                 "WHERE t.accountId = :accountId " +
                 "AND lower(t.merchantName) LIKE lower(concat('%',:keyword,'%')) " +
                 "AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = t.type) ")
  Page<Transaction> findTransactionsByAccountIdWithOptions(Long accountId, String keyword, TransactionType filter, Pageable pageable);
}
