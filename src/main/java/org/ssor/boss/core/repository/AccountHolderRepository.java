package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.AccountHolder;

/**
 * Defines a repository capable of performing actions on a database containing {@link AccountHolder}s of a given schema.
 */
@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer>
{ }
