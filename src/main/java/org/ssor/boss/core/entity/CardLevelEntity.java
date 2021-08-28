package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents the different levels of credit cards.
 *
 * <p>
 * Card distributors can set up different types of credit cards. The most common existing ones are Gold and Platinum
 * cards, but others do exist. We want to make sure that we track this information, so that the rest of our information
 * is valid.
 *
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "CardLevelEntity")
@Table(schema = "boss", name = "card_level")
public class CardLevelEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, updatable = false)
	private Short id;

	@Length(max = 16)
	@Column(nullable = false)
	private String name;

	@Length(max = 16)
	@Column(nullable = false)
	private String distributor;

	/**
	 * Default constructs the card level.
	 */
	public CardLevelEntity() {
		id = null;
		name = "";
		distributor = "";
	}

	/**
	 * Creates a new card level with the given name and distributor.
	 *
	 * @param name        The name of the card level.
	 * @param distributor The name of the distributor.
	 */
	public CardLevelEntity(@NonNull String name, @NonNull String distributor) {
		this.id = null;
		this.name = name;
		this.distributor = distributor;
	}
}
