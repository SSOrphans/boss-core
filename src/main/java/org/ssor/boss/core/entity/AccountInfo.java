package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Embeddable account entity information.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class AccountInfo implements Serializable {
	@Length(min = 4, max = 16)
	private String name;
	private float balance;
	private float pendingBalance;
	private LocalDateTime opened;
	private LocalDateTime closed;
	private boolean confirmed;
	private boolean enabled;
	private boolean locked;

	/**
	 * Default constructs the account info.
	 */
	public AccountInfo() {
		name = null;
		balance = 0f;
		pendingBalance = 0f;
		opened = LocalDateTime.now(ZoneOffset.UTC);
		closed = null;
		confirmed = false;
		enabled = false;
		locked = false;
	}

	/**
	 * Creates the account info with the provided name.
	 *
	 * @param name The name of the account.
	 */
	public AccountInfo(@NonNull String name) {
		this.name = name;
		balance = 0f;
		pendingBalance = 0f;
		opened = LocalDateTime.now(ZoneOffset.UTC);
		closed = null;
		confirmed = false;
		enabled = true;
		locked = false;
	}
}
