package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a single frequent flyer miles program managed by a given group.
 *
 * <p>
 * This entity merely describes the program as managed by a specific group and the name of the program. This is for our
 * systems to check a frequent flyer program (FFP) against a given account entity (noting that credit cards, which FFPs
 * are applied, are themselves accounts). The check makes sure we can accept a transaction for an account, backed by a
 * credit card, with FFP miles provided by the specific group. If the name of the program, or the group are incorrect
 * for the account, the transaction will be declined.
 *
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "RewardsProgramEntity")
@Table(schema = "boss", name = "rewards_program")
public class RewardsProgramEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Enumerated
	@Column(nullable = false)
	private RewardsType type;

	@Length(max = 16)
	@Column(nullable = false)
	private String name;

	@Length(max = 16)
	@Column(nullable = false)
	private String distributor;

	/**
	 * Default constructs the miles program entity.
	 */
	public RewardsProgramEntity() {
		id = null;
		type = RewardsType.UNDEFINED;
		name = "";
		distributor = "";
	}

	/**
	 * Constructs the miles program entity, as a new entity.
	 *
	 * @param type        The type of the rewards program.
	 * @param name        The name of the miles program to create.
	 * @param distributor The name of the group creating the miles program.
	 */
	public RewardsProgramEntity(@NonNull RewardsType type, @NonNull String name, @NonNull String distributor) {
		this.id = null;
		this.type = type;
		this.name = name;
		this.distributor = distributor;
	}
}
