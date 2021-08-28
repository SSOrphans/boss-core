package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Describes the information of a transaction.
 *
 * @author Bermond Jon Batistiana
 */
@Getter
@Setter
@Entity(name = "TransactionEntity")
@Table(schema = "boss", name = "transaction")
public class TransactionEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;

	@Enumerated
	@Column(nullable = false)
	private TransactionType type;

	@ManyToOne
	@JoinColumn(name = "account", referencedColumnName = "id")
	private AccountEntity account;

	@Column
	private Long overdraft; // TODO: create overdraft entity.

	@Column
	private Long atm; // TODO: create atm entity.

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "merchant", column = @Column(name = "merchant", nullable = false)),
			@AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false)),
			@AttributeOverride(name = "newBalance", column = @Column(name = "new_balance", nullable = false)),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false)),
			@AttributeOverride(name = "flags", column = @Column(name = "flags", nullable = false))
	})
	private TransactionInfo info;

	/**
	 * Default constructs the transaction entity.
	 */
	public TransactionEntity() {
		id = null;
		type = TransactionType.UNDEFINED;
		account = null;
		overdraft = null;
		atm = null;
		info = null;
	}

	/**
	 * Creates a transaction entity from the provided information.
	 *
	 * @param type      The type of the transaction.
	 * @param account   The account the transaction is taking place in.
	 * @param overdraft The overdraft associated with the transaction.
	 * @param atm       The atm associated with the transaction.
	 * @param info      The information of the transaction.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public TransactionEntity(@NonNull TransactionType type, @NonNull AccountEntity account, Long overdraft, Long atm,
	                         @NonNull TransactionInfo info)
			throws NullPointerException {
		this.id = null;
		this.type = type;
		this.account = account;
		this.overdraft = overdraft;
		this.atm = atm;
		this.info = info;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (TransactionEntity) obj;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
