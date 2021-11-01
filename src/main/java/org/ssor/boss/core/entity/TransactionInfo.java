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
 * Embeddable transaction entity information.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class TransactionInfo implements Serializable {
	@Length(max = 32)
	private String merchant;
	private float amount;
	private float newBalance;
	private LocalDateTime date;
	private byte flags; // TODO: write transaction flags utility.

	/**
	 * Default constructs the transaction information.
	 */
	public TransactionInfo() {
		merchant = "";
		amount = 0;
		newBalance = 0;
		date = LocalDateTime.now();
		flags = 0;
	}

	/**
	 * Creates the transaction information from the provided.
	 *
	 * @param merchant   The merchant name that made the transaction.
	 * @param amount     The amount of the transaction.
	 * @param newBalance The new balance of the account after the transaction.
	 * @param date       The date the transaction took place.
	 * @param flags      The status flags of the transaction.
	 * @throws NullPointerException If any of the non-nullable parameters are null.
	 */
	public TransactionInfo(@NonNull String merchant, float amount, float newBalance, @NonNull LocalDateTime date,
	                       byte flags)
			throws NullPointerException {
		this.merchant = merchant;
		this.amount = amount;
		this.newBalance = newBalance;
		this.date = date;
		this.flags = flags;
	}
}
