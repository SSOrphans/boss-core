package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the card that a user has.
 *
 * <p>
 * Cards are distributed by the bank. They may be created, to start with, without a holding party for it. They may later
 * give the card out to a user to be the holding party for it. They also may create a card and give it out immediately.
 * The latter example is typical for debit cards that are distributed at a branch. The former example is likely for
 * credit cards.
 *
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "CardEntity")
@Table(schema = "boss", name = "card")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public class CardEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, updatable = false)
	protected Long id;

	@Enumerated
	@Column(nullable = false)
	protected CardType type;

	@ManyToOne
	@JoinColumn(name = "account", referencedColumnName = "id")
	protected AccountEntity account;

	@ManyToOne
	@JoinColumn(name = "holder", referencedColumnName = "id")
	protected UserEntity holder;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "number", column = @Column(name = "number", unique = true, nullable = false)),
			@AttributeOverride(name = "cvv", column = @Column(name = "cvv", nullable = false)),
			@AttributeOverride(name = "created", column = @Column(name = "created", nullable = false)),
			@AttributeOverride(name = "expiry", column = @Column(name = "expiry", nullable = false))
	})
	protected PhysicalCardInfo physicalInfo;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "pin", column = @Column(name = "pin")),
			@AttributeOverride(name = "since", column = @Column(name = "since")),
			@AttributeOverride(name = "flags", column = @Column(name = "flags", nullable = false))
	})
	protected LogicalCardInfo logicalInfo;

	/**
	 * Default constructs the card representation.
	 */
	public CardEntity() {
		id = null;
		type = CardType.UNDEFINED;
		account = null;
		holder = null;
		physicalInfo = null;
		logicalInfo = null;
	}

	/**
	 * Creates the card without a holder or backing account.
	 *
	 * @param type         The type of the card, debit or credit.
	 * @param physicalInfo The physical information of the card.
	 * @param logicalInfo  The logical information of the card.
	 * @throws NullPointerException If any of the non-nullable fields are null.
	 */
	public CardEntity(@NonNull CardType type, @NonNull PhysicalCardInfo physicalInfo,
	                  @NonNull LogicalCardInfo logicalInfo)
			throws NullPointerException {
		this.id = null;
		this.type = type;
		this.account = null;
		this.holder = null;
		this.physicalInfo = physicalInfo;
		this.logicalInfo = logicalInfo;
	}

	/**
	 * Creates the card representation with the relevant information.
	 *
	 * @param type         The type of the card, debit or credit.
	 * @param account      The account for the card.
	 * @param holder       The account holder for the card.
	 * @param physicalInfo The physical card information.
	 * @param logicalInfo  The logical card information.
	 * @throws NullPointerException If any of the non-nullable fields are null.
	 */
	public CardEntity(@NonNull CardType type, @NonNull AccountEntity account, @NonNull UserEntity holder,
	                  @NonNull PhysicalCardInfo physicalInfo, @NonNull LogicalCardInfo logicalInfo)
			throws NullPointerException {
		this.id = null;
		this.type = type;
		this.account = account;
		this.holder = holder;
		this.physicalInfo = physicalInfo;
		this.logicalInfo = logicalInfo;
	}

	protected CardEntity(@NonNull CardEntity base) throws NullPointerException {
		this.id = base.id;
		this.type = base.type;
		this.account = base.account;
		this.holder = base.holder;
		this.physicalInfo = base.physicalInfo;
		this.logicalInfo = base.logicalInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (CardEntity) obj;
		return Objects.equals(id, that.id) && Objects.equals(physicalInfo.getNumber(), that.physicalInfo.getNumber());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, physicalInfo.getNumber());
	}
}
