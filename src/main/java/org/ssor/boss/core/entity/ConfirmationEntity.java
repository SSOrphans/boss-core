package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Describes a confirmation entry.
 *
 * <p>
 * Responsible for providing confirmation codes to a user, via their email, so that we can confirm the transaction that
 * will take place, be it creating an account, activating a card, or otherwise.
 *
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "ConfirmationEntity")
@Table(schema = "boss", name = "confirmation")
public class ConfirmationEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, updatable = false)
	private Integer id;

	@Enumerated
	@Column(nullable = false)
	private ConfirmationType type;

	@Column(nullable = false, updatable = false)
	private long confirmable;

	@Length(min = 36, max = 36)
	@Column(unique = true, nullable = false, updatable = false)
	private String hash;

	@Column(nullable = false, updatable = false)
	private long until;

	/**
	 * Default constructs the confirmation.
	 */
	public ConfirmationEntity() {
		id = null;
		type = ConfirmationType.UNDEFINED;
		confirmable = 0;
		hash = "";
		until = 0;
	}

	/**
	 * Creates the confirmation object from provided information.
	 *
	 * @param type        The type of object the confirmation will be confirming.
	 * @param confirmable The ID of the object that will be confirmed.
	 * @param until       How long the confirmation code will last.
	 */
	public ConfirmationEntity(@NonNull ConfirmationType type, long confirmable, long until) {
		this.id = null;
		this.type = type;
		this.confirmable = confirmable;
		this.hash = UUID.randomUUID().toString();
		this.until = until;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
			return false;

		final var that = (ConfirmationEntity) obj;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
