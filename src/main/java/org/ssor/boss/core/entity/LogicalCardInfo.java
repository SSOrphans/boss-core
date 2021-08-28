package org.ssor.boss.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;

/**
 * Represents a card's logical information as an embeddable entity.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class LogicalCardInfo implements Serializable {
	@JsonIgnore
	@Length(min = 64, max = 64)
	private String pin; // hashed
	private Long since;
	private byte flags;

	/**
	 * Default constructs the logical card info.
	 */
	public LogicalCardInfo() {
		pin = null;
		since = null;
		flags = 0;
	}

	/**
	 * Creates the logical card info with the provided pin and the date it was active since.
	 *
	 * @param pin The hashed pin for the card.
	 */
	public LogicalCardInfo(@NonNull String pin) {
		this.pin = pin;

		final var now = Instant.now();
		since = now.toEpochMilli();
		flags = 0;
	}
}
