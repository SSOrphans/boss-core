package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Card;

/**
 * Repository capable of managing card data.
 *
 * @author Derrian Harris
 * @author John Christman
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer>
{

}
