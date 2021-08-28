package org.ssor.boss.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Represents a card's physical information as an embeddable entity.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class PhysicalCardInfo implements Serializable {
	@Length(max = 19)
	private String number;

	@JsonIgnore
	@Length(min = 64, max = 64)
	private String cvv; // hashed
	private long created;
	private long expiry;

	/**
	 * Default constructor for physical card info.
	 */
	public PhysicalCardInfo() {
		number = "";
		cvv = "";
		created = 0;
		expiry = 0;
	}

	/**
	 * Creates the physical card info from the provided card number, cvv hash, and lifespan.
	 *
	 * @param number   The card number.
	 * @param cvv      The hashed cvv.
	 * @param lifespan How long the card should last in years.
	 */
	public PhysicalCardInfo(@NonNull String number, @NonNull String cvv, int lifespan) {
		this.number = number;
		this.cvv = cvv;

		final var now = LocalDateTime.now().atZone(ZoneId.systemDefault());
		final var later = now.plusYears(lifespan);
		created = now.toInstant().toEpochMilli();
		expiry = later.toInstant().toEpochMilli();
	}

	/**
	 * Creates the physical card info from the provided card number and cvv hash.
	 *
	 * <p>
	 * The created card info will specify that the card has a lifespan of 3 years. This is the average lifespan of credit
	 * and debit cards of the modern age.
	 *
	 * @param number The card number.
	 * @param cvv    The hashed cvv.
	 */
	public PhysicalCardInfo(@NonNull String number, @NonNull String cvv) {
		this(number, cvv, 3);
	}
}
