package org.ssor.boss.core.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Describes a user account holder with account holder details.
 * <p>
 *   Account holders are a construct designed to represent a user's information.
 * </p>
 * <p>
 *   Default users have the ability to access the public portal of the website and make limited requests from the
 *   website. These requests include: viewing, and updating their profile.
 * </p>
 * <p>
 *   Administrators are capable of doing everything the users can, to any user they wish and more. Administrators can
 *   modify the account holder's information.
 * </p>
 *
 * @author Christian Angeles
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_holder", schema = "boss", uniqueConstraints = {
		@UniqueConstraint(columnNames = "user_id"),
		@UniqueConstraint(columnNames = "ssn")
})
public class AccountHolder implements Serializable
{
	@Id
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "full_name")
	private String fullName;
	private LocalDate dob;
	private String ssn;
	private String address;
	private String city;
	private String state;
	private Integer zip;
	private String phone;

	@MapsId
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany
	@JoinTable(name = "user_cards", schema = "boss",
						 joinColumns = @JoinColumn(name = "user_id"),
						 inverseJoinColumns = @JoinColumn(name = "card_id"))
	private List<Card> cards;

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ssn.hashCode();
		result = prime * result + userId.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccountHolder holder = (AccountHolder) o;
		return Objects.equals(userId, holder.userId) && Objects.equals(ssn, holder.ssn);
	}

}
