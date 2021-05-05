/**
 * 
 */
package org.ssor.boss.core.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_holder", schema = "boss")
public class AccountHolder
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
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountHolder other = (AccountHolder) obj;
		if (ssn == null)
		{
			if (other.ssn != null)
				return false;
		} else if (!ssn.equals(other.ssn))
			return false;
		if (userId == null)
		{
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
