package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents a single user address.
 *
 * <p>
 * We attempt to store the user addresses as close as possible to being international tolerant as possible. We do this
 * by not imposing a specific format for the postal code or the region, and allowing the premise to be nullable.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class UserAddress implements Serializable {
	@Length(max = 2)
	private String country;

	@Length(max = 10)
	private String postal;

	@Length(max = 64)
	private String region;

	@Length(max = 64)
	private String city;

	@Length(max = 64)
	private String street;

	@Length(max = 64)
	private String premise;

	/**
	 * Default constructs the user address.
	 */
	public UserAddress() {
		country = "";
		postal = "";
		region = "";
		city = "";
		street = "";
		premise = "";
	}

	/**
	 * Creates the user address from the necessary information.
	 *
	 * @param country An ISO 3166-2 standard country code.
	 * @param postal  The postal code for the address.
	 * @param region  The state/province/region of the address.
	 * @param city    The city of the address.
	 * @param street  The street of the address.
	 * @param premise The apartment building, suite, or P.O. box of the address.
	 */
	public UserAddress(String country, String postal, String region, String city, String street, String premise) {
		this.country = country;
		this.postal = postal;
		this.region = region;
		this.city = city;
		this.street = street;
		this.premise = premise;
	}
}
