package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents the personal user information.
 *
 * <p>
 * Defines all the user personal information, including things like their personal address and their personal cellular
 * number. All of this information is momentarily nullable, within the database, as to streamline the signup process for
 * new users. The data should be updated by the user before they are allowed to create an actual bank account that can
 * hold monetary tender.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class UserInfo implements Serializable {
	@Length(max = 255)
	private String fullName;

	@Length(max = 64)
	private String identification;
	private LocalDate dateOfBirth;

	@Embedded
	private UserAddress address;

	@Embedded
	private UserCellular phone;

	/**
	 * Default constructs the user information.
	 */
	public UserInfo() {
		fullName = "";
		identification = "";
		dateOfBirth = null;
		address = null;
		phone = null;
	}

	/**
	 * Creates the user information from all the required user information.
	 *
	 * @param fullName       The full name of the user in their native language.
	 * @param identification The identification number of the user.
	 * @param dateOfBirth    The birthdate of the user.
	 * @param address        The address information of the user.
	 * @param phone          The cellular contact information of the user.
	 */
	public UserInfo(String fullName, String identification, LocalDate dateOfBirth, UserAddress address,
	                UserCellular phone)
			throws NullPointerException {
		this.fullName = fullName;
		this.identification = identification;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phone = phone;
	}
}
