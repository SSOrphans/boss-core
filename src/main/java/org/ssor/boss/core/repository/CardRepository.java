package org.ssor.boss.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Card;

/**
 * @author Derrian Harris
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

}
