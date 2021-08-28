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

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime opened;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime closed;
	private byte flags;

	/**
	 * Default constructs the account info.
	 */
	public AccountInfo() {
		name = null;
		balance = 0;
		pendingBalance = 0;
		opened = LocalDateTime.now();
		closed = null;
		flags = 0;
	}

	/**
	 * Creates the account info with the provided name.
	 *
	 * @param name The name of the account.
	 */
	public AccountInfo(@NonNull String name) {
		this.name = name;
		balance = 0;
		pendingBalance = 0;
		opened = LocalDateTime.now();
		closed = null;
		flags = 0;
	}
}
