package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.Card;
import org.ssor.boss.core.entity.RewardType;
import org.ssor.boss.core.entity.RewardsProgram;
import java.util.Optional;

/**
 * Repository capable of managing reward program data.
 *
 * @author Derrian Harris
 */
@Repository
public interface RewardsProgramRepository extends JpaRepository<RewardsProgram, Integer>
{
	Optional<RewardsProgram> findByName(String name);

	Optional<RewardsProgram> findByNameAndType(String name, RewardType type);
}
