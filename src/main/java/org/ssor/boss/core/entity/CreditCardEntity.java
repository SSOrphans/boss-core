package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

/**
 * Provides the representation of a credit card.
 *
 * @author John Christman
 */
@Getter
@Setter
@DiscriminatorValue(value = "2")
@Entity(name = "CreditCardEntity")
@PrimaryKeyJoinColumn(name = "backing")
@Table(schema = "boss", name = "credit_card")
public class CreditCardEntity extends CardEntity {
	@OneToOne
	@JoinColumn(name = "level", referencedColumnName = "id")
	private CardLevelEntity level;

	@Column(nullable = false)
	private float apr;

	@Column(nullable = false)
	private float ceiling;

	@OneToMany
	@JoinTable(schema = "boss", name = "card_rewards_programs",
	           joinColumns = @JoinColumn(name = "card", nullable = false),
	           inverseJoinColumns = @JoinColumn(name = "program", nullable = false))
	private List<RewardsProgramEntity> rewardsProgram;

	@ElementCollection
	@Column(name = "points", nullable = false)
	@CollectionTable(schema = "boss", name = "card_rewards_programs", joinColumns = @JoinColumn(name = "card"))
	private List<Integer> rewardsPoints;

	/**
	 * Default constructs the credit card entity.
	 */
	public CreditCardEntity() {
		super();
		level = null;
		apr = 0;
		ceiling = 0;
		rewardsProgram = null;
		rewardsPoints = null;
	}

	/**
	 * Creates a credit card from the provided base card.
	 *
	 * @param backing The backing representation of the credit card.
	 * @param level   The level of the credit card.
	 * @param apr     The credit card apr interest.
	 * @param ceiling The maximum spending allowed on the credit card.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public CreditCardEntity(@NonNull CardEntity backing, @NonNull CardLevelEntity level, float apr, float ceiling)
			throws NullPointerException {
		super(backing);
		this.level = level;
		this.apr = apr;
		this.ceiling = ceiling;
	}

	/**
	 * Creates an account-less and account-holder-less credit card.
	 *
	 * @param physicalCardInfo The physical card information.
	 * @param logicalCardInfo  The logical card information.
	 * @param level            The level of the credit card.
	 * @param apr              The credit card apr interest.
	 * @param ceiling          The maximum spending allowed on the credit card.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public CreditCardEntity(@NonNull PhysicalCardInfo physicalCardInfo, @NonNull LogicalCardInfo logicalCardInfo,
	                        @NonNull CardLevelEntity level, float apr, float ceiling)
			throws NullPointerException {
		super(CardType.CREDIT, physicalCardInfo, logicalCardInfo);
		this.level = level;
		this.apr = apr;
		this.ceiling = ceiling;
	}

	/**
	 * Creates the credit card from all its necessary information.
	 *
	 * @param account      The account the card is backed by.
	 * @param holder       The holder the card is bound to.
	 * @param physicalInfo The physical card information.
	 * @param logicalInfo  The logical card information.
	 * @param level        The level of the credit card.
	 * @param apr          The credit card apr interest.
	 * @param ceiling      The maximum spending allowed on the credit card.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public CreditCardEntity(@NonNull AccountEntity account, @NonNull UserEntity holder,
	                        @NonNull PhysicalCardInfo physicalInfo, @NonNull LogicalCardInfo logicalInfo,
	                        @NonNull CardLevelEntity level, float apr, float ceiling)
			throws NullPointerException {
		super(CardType.CREDIT, account, holder, physicalInfo, logicalInfo);
		this.level = level;
		this.apr = apr;
		this.ceiling = ceiling;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (CreditCardEntity) obj;
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
