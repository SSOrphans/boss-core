package org.ssor.boss.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.TransactionEntity;
import org.ssor.boss.core.entity.TransactionType;

import java.time.LocalDateTime;
import java.util.List;

// TODO: fix all of this.
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
//  @Query(value = "SELECT t FROM TransactionEntity t WHERE t.accountId = :accountId AND t.id = :id")
//  TransactionEntity findTransactionById(Integer id, Long accountId);
//
//  @Query(value = "SELECT t FROM TransactionEntity t WHERE t.pending = true AND t.succeeded = false and t.date <= :start  order by t.date desc")
//  List<TransactionEntity> findPendingTransactionsByDate(LocalDateTime start);
//
//
//
//  @Query(value = "SELECT t FROM TransactionEntity t " +
//                 "WHERE t.accountId = :accountId " +
//                 "AND lower(t.merchantName) LIKE lower(concat('%',:keyword,'%')) " +
//                 "AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = t.type) ")
//  Page<TransactionEntity> findTransactionsByAccountIdWithOptions(Long accountId, String keyword, TransactionType filter, Pageable pageable);
}
