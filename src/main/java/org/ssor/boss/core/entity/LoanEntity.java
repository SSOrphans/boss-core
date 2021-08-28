package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Describes the information of a loan.
 *
 * @author Derrian Harris
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "LoanEntity")
@Table(schema = "boss", name = "loan")
public class LoanEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;

	@Enumerated
	@Column(nullable = false)
	private LoanType type;

	@Length(max = 64)
	@Column(unique = true, nullable = false)
	private String number;

	@OneToOne
	@JoinColumn(name = "user", referencedColumnName = "id")
	private UserEntity user;

	@OneToOne
	@JoinColumn(name = "account", referencedColumnName = "id")
	private AccountEntity account;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total", nullable = false)),
			@AttributeOverride(name = "interest", column = @Column(name = "interest", nullable = false)),
			@AttributeOverride(name = "takenAt", column = @Column(name = "taken_at", nullable = false)),
			@AttributeOverride(name = "dueBy", column = @Column(name = "due_by", nullable = false))
	})
	private LoanInfo info;

	/**
	 * Default constructs the loan entity.
	 */
	public LoanEntity() {
		id = null;
		type = LoanType.LOAN_UNKNOWN;
		number = "";
		user = null;
		account = null;
		info = null;
	}

	/**
	 * Creates the loan from the necessary information.
	 *
	 * @param type    The type of the loan.
	 * @param number  The loan number.
	 * @param user    The user the loan is being taken out for.
	 * @param account The account the loan will be bound to.
	 * @param info    The information about the loan.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public LoanEntity(@NonNull LoanType type, @NonNull String number, @NonNull UserEntity user,
	                  @NonNull AccountEntity account, @NonNull LoanInfo info)
			throws NullPointerException {
		this.id = null;
		this.type = type;
		this.number = number;
		this.user = user;
		this.account = account;
		this.info = info;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (LoanEntity) obj;
		return Objects.equals(id, that.id) && Objects.equals(number, that.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, number);
	}
}
