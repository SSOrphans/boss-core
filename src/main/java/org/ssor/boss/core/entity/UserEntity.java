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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Describes a user entity.
 *
 * <p>
 * Users encapsulate the most important information required to represent persons and their personal information. The
 * information is used for communication and assurance checks against things that the user wants. That is, if a user
 * wants to open a checking account, they must have specified their personal information in order to do so. This
 * personal information may also be used to represent non account holding users, such as administrators.
 *
 * @author Christian Angeles
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "UserEntity")
@Table(schema = "boss", name = "user")
public class UserEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private UserType type;

	@Column(nullable = false)
	private long branch; // TODO: create branch entity.

	@Column(nullable = false)
	private byte flags;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "username", column = @Column(name = "username", unique = true, nullable = false)),
			@AttributeOverride(name = "email", column = @Column(name = "email", unique = true, nullable = false)),
			@AttributeOverride(name = "password", column = @Column(name = "password", nullable = false)),
			@AttributeOverride(name = "created", column = @Column(name = "created", nullable = false, updatable = false)),
			@AttributeOverride(name = "deleted", column = @Column(name = "deleted", insertable = false)),
			@AttributeOverride(name = "settings.transactionAlerts", column = @Column(name = "transaction_alerts")),
			@AttributeOverride(name = "settings.balanceAlerts", column = @Column(name = "balance_alerts"))
	})
	private UserProfile profile;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "fullName", column = @Column(name = "full_name")),
			@AttributeOverride(name = "dateOfBirth", column = @Column(name = "dob")),
			@AttributeOverride(name = "address.country", column = @Column(name = "country")),
			@AttributeOverride(name = "address.postal", column = @Column(name = "postal")),
			@AttributeOverride(name = "address.region", column = @Column(name = "region")),
			@AttributeOverride(name = "address.city", column = @Column(name = "city")),
			@AttributeOverride(name = "address.street", column = @Column(name = "street")),
			@AttributeOverride(name = "address.premise", column = @Column(name = "premise")),
			@AttributeOverride(name = "phone.presentation", column = @Column(name = "presentation")),
			@AttributeOverride(name = "phone.number", column = @Column(name = "number"))
	})
	private UserInfo userInfo;

	/**
	 * Default constructs the account holder.
	 */
	public UserEntity() {
		id = null;
		type = UserType.USER_UNKNOWN;
		branch = -1L;
		flags = 0;
		profile = null;
		userInfo = null;
	}

	/**
	 * Creates the account holder information from the determined type, branch, profile, and user info.
	 *
	 * @param type    The type of the account holder.
	 * @param branch  The branch id of the user.
	 * @param profile The user profile information.
	 */
	public UserEntity(@NonNull UserType type, long branch, @NonNull UserProfile profile) {
		this.id = null;
		this.type = type;
		this.branch = branch;
		this.flags = 0;
		this.profile = profile;
		this.userInfo = null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (UserEntity) obj;
		return Objects.equals(id, that.id) &&
		       Objects.equals(profile.getUsername(), that.profile.getUsername()) &&
		       Objects.equals(profile.getEmail(), that.profile.getEmail()) &&
		       Objects.equals(userInfo.getIdentification(), that.userInfo.getIdentification());
	}

	@Override
	public int hashCode() {
		var result = 1;
		result = 31 * result + id.hashCode();
		result = 31 * result + profile.getUsername().hashCode();
		result = 31 * result + profile.getEmail().hashCode();
		result = 31 * result + userInfo.getIdentification().hashCode();
		return result;
	}
}
