package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Confirmation;
import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Integer>
{
  /**
   * Finds a confirmation by a confirmation's hash.
   *
   * @param hash The hash of the confirmation to find.
   * @return A possible confirmation entry.
   */
  Optional<Confirmation> findByConfirmationHash(String hash);
}
