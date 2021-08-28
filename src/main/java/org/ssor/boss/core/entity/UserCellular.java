package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Represents a user's cellular contact, so the bank can reach of if needed.
 *
 * <p>
 * This entity is designed to store the user's cellular information for contacting them directly and is designed to
 * support foreign numbers by using a presentation style and storing the longest possible phone number.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class UserCellular implements Serializable {
	@Enumerated
	private PresentationType presentation;

	@Length(max = 15)
	private String number;

	/**
	 * Default constructs the user cellular contact.
	 */
	public UserCellular() {
		presentation = PresentationType.GENERIC;
		number = "";
	}

	/**
	 * Creates the user cellular contact with the provided number and presentation style.
	 *
	 * @param presentation The style of presentation to give the cellular number.
	 * @param number       The user cellular number.
	 */
	public UserCellular(PresentationType presentation, String number) {
		this.presentation = presentation;
		this.number = number;
	}
}
