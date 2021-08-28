package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * An embeddable entity, storing the user preferences.
 *
 * @author Bermond Jon Batistiana
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class UserSettings implements Serializable {
	private int transactionAlerts;
	private int balanceAlerts;

	/**
	 * Default constructs the user settings.
	 */
	public UserSettings() {
		transactionAlerts = 0;
		balanceAlerts = 0;
	}

	/**
	 * Creates the user settings from the given parameters.
	 */
	public UserSettings(int transactionAlerts, int balanceAlerts) {
		this.transactionAlerts = transactionAlerts;
		this.balanceAlerts = balanceAlerts;
	}
}
