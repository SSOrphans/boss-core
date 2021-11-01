package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
			@AttributeOverride(name = "confirmed", column = @Column(name = "confirmed", nullable = false)),
			@AttributeOverride(name = "enabled", column = @Column(name = "enabled", nullable = false)),
			@AttributeOverride(name = "locked", column = @Column(name = "locked", nullable = false))
	})
	private AccountInfo info;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(schema = "boss", name = "user_accounts", joinColumns = @JoinColumn(name = "account"),
	           inverseJoinColumns = @JoinColumn(name = "user"))
	private List<UserEntity> users;

	/**
	 * Default constructs the account entity.
	 */
	public AccountEntity() {
		id = null;
		type = AccountType.UNDEFINED;
		branch = 0;
		info = null;
		users = null;
	}

	/**
	 * Creates the account from the provided information.
	 *
	 * @param id     The identity descriptor of the account.
	 * @param type   The type of the account.
	 * @param branch The branch the account belongs to.
	 * @param info   The account information.
	 * @throws NullPointerException If any of the non-nullable fields are null.
	 */
	public AccountEntity(long id, @NonNull AccountType type, int branch, @NonNull AccountInfo info)
			throws NullPointerException {
		this.id = id;
		this.type = type;
		this.branch = branch;
		this.info = info;
		this.users = new ArrayList<>();
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
