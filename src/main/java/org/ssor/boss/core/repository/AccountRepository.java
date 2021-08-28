package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.AccountEntity;

// TODO: fix all of this.
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	//  @Query("SELECT a FROM AccountEntity a " +
	//         "JOIN a.users u WHERE u.id = :userId AND a.closed IS NULL ORDER BY a.accountType")
	//  List<AccountEntity> findAccountsByUser(Integer userId);
	//
	//  @Query("SELECT a FROM AccountEntity a " +
	//         "JOIN a.users u WHERE u.id = :userId AND a.closed IS NULL AND a.id = :accountId")
	//  Optional<AccountEntity> findAccountByIdAndUserId(Integer userId, Long accountId);
	//
	//  @Query("SELECT a FROM AccountEntity a " +
	//         "WHERE concat(a.id,'') LIKE concat('%', :keyword, '%') " +
	//         "AND (:filter IS NULL OR :#{#filter.index()} = 0  OR :filter = a.accountType) ")
	//  Page<AccountEntity> findAccountsWithOptions(String keyword, AccountType filter, Pageable pageable);
}
