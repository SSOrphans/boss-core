package org.ssor.boss.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.ssor.boss.core.entity.UserEntity;
import java.util.Optional;

/**
 * Defines a repository capable of performing actions on a database containing {@link UserEntity}s of a given schema.
 *
 * @author John Christman
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
	/**
	 * Gets a user by their username.
	 *
	 * @param username The username of the user.
	 * @return The obtained user inside the optional, else an empty optional.
	 */
	Optional<UserEntity> findByProfileUsername(String username);

	/**
	 * Gets a user by their email.
	 *
	 * @param email The email of the user.
	 * @return The obtained user inside the optional, else an empty optional.
	 */
	Optional<UserEntity> findByProfileEmail(String email);

	/**
	 * Checks if a user exists by a username alone.
	 *
	 * @param username The username of the user.
	 * @return True if the user exists, false otherwise.
	 */
	boolean existsByProfileUsername(String username);

	/**
	 * Checks if a user exists by an email alone.
	 *
	 * @param email The email of the user.
	 * @return True if the user exists, false otherwise.
	 */
	boolean existsUserByProfileEmail(String email);
}
