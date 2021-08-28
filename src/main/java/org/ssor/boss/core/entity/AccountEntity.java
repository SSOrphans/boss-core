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
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Describes the information of an account entity.
 *
 * @author Bermond Jon Batistiana
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "AccountEntity")
@Table(schema = "boss", name = "account")
public class AccountEntity implements Serializable {
	@Id
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;

	@Enumerated
	@Column(nullable = false)
	private AccountType type;

	@Column(nullable = false)
	private int branch; // TODO: create branch entity.

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "name")),
			@AttributeOverride(name = "balance", column = @Column(name = "balance", nullable = false)),
			@AttributeOverride(name = "pendingBalance", column = @Column(name = "pending_balance", nullable = false)),
			@AttributeOverride(name = "opened", column = @Column(name = "opened", nullable = false)),
			@AttributeOverride(name = "closed", column = @Column(name = "closed")),
			@AttributeOverride(name = "flags", column = @Column(name = "flags", nullable = false))
	})
	private AccountInfo info;

	/**
	 * Default constructs the account entity.
	 */
	public AccountEntity() {
		id = null;
		type = AccountType.UNDEFINED;
		branch = 0;
		info = null;
	}

	/**
	 * Creates the account from the provided information.
	 *
	 * @param type   The type of the account.
	 * @param branch The branch the account belongs to.
	 * @param info   The account information.
	 * @throws NullPointerException If any of the non-nullable fields are null.
	 */
	public AccountEntity(@NonNull AccountType type, int branch, @NonNull AccountInfo info) throws NullPointerException {
		this.id = null;
		this.type = type;
		this.branch = branch;
		this.info = info;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (AccountEntity) obj;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
