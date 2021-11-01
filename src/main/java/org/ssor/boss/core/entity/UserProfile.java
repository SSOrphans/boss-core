package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * Describes a user with user details.
 *
 * <p>
 * Users are a construct designed to represent a person using an external API or the open web portal. Users, however,
 * could also be an Administrator with extra permissions, or a Vendor with different types of permissions from default
 * users.
 *
 * <p>
 * Default users have the ability to access the public portal of the website and make limited requests from the
 * website. These requests include: creating a checking or saving account, obtaining a debit card or signing up for a
 * credit card, asking for a loan of various types, updating their profile, deactivating a card, closing an account,
 * paying off cards or loans, and many more things.
 *
 * <p>
 * Administrators are capable of doing everything the users can, to any user they wish and more. Administrators can
 * promote default users to administrators, in the event a user becomes an employee, or demote other admins to default
 * users if they get fired. Admins are also able to do management of accounts, cards, and loans for a branch in case
 * something went wrong or there are mistakes. Admins are able to do things for users who may be locked out of their
 * account for a reason as well.
 *
 * <p>
 * Vendors are a special type of user. They are users who can create vendor applications. These applications are pre-
 * authorized with the gateway. This allows the vendor applications to make requests to various parts of the external
 * APIs. They do not have full reign of the API, but are given access to part of the API that normal users are not.
 * Vendor applications, specifically, are capable of issuing transactions through our external APIs.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class UserProfile implements Serializable {
	@Length(min = 4, max = 16)
	private String username;

	@Length(max = 128)
	private String email;

	@Length(min = 64, max = 64)
	private String password;

	private long created;
	private Long deleted;
	private boolean confirmed;
	private boolean enabled;
	private boolean locked;

	@Embedded
	private UserSettings settings;

	/**
	 * Default constructs the user.
	 */
	public UserProfile() {
		username = "";
		email = "";
		password = "";
		created = 0;
		deleted = null;
		confirmed = false;
		enabled = false;
		locked = false;
		settings = null;
	}

	/**
	 * Creates a user from the necessary information.
	 *
	 * @param username The username of the user.
	 * @param email    The email of the user.
	 * @param password The hashed password of the user.
	 * @param created  The time, in milliseconds, the user was created.
	 * @param settings The settings of the user.
	 */
	public UserProfile(@NonNull String username, @NonNull String email, @NonNull String password, long created,
	                   UserSettings settings) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.created = created;
		this.deleted = null;
		this.confirmed = false;
		this.enabled = true;
		this.locked = false;
		this.settings = settings;
	}
}
