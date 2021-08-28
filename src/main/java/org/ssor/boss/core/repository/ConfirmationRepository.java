package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.ConfirmationEntity;
import java.util.Optional;

/**
 * A repository for managing confirmations.
 *
 * @author John Christman
 */
@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Integer>
{
  /**
   * Finds a confirmation by a confirmation's hash.
   *
   * @param hash The hash of the confirmation to find.
   * @return A possible confirmation entry.
   */
  Optional<ConfirmationEntity> findByHash(String hash);
}
