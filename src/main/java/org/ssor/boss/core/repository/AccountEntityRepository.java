package org.ssor.boss.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.AccountEntity;
import org.ssor.boss.core.entity.AccountType;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {
  @Query("SELECT a FROM AccountEntity a " +
         "JOIN a.users u WHERE u.id = :userId AND a.info.closed IS NULL ORDER BY a.type")
  List<AccountEntity> findAccountsByUser(Long userId);

  @Query("SELECT a FROM AccountEntity a " +
         "JOIN a.users u WHERE u.id = :userId AND a.info.closed IS NULL AND a.id = :accountId")
  Optional<AccountEntity> findAccountByIdAndUserId(Long userId, Long accountId);

  @Query("SELECT a FROM AccountEntity a " +
         "WHERE concat(a.id,'') LIKE concat('%', :keyword, '%') " +
         "AND (:filter IS NULL OR :#{#filter.ordinal()} = 0  OR :filter = a.type) ")
  Page<AccountEntity> findAccountsWithOptions(String keyword, AccountType filter, Pageable pageable);
}
