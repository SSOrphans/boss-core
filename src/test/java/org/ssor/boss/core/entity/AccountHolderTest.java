package org.ssor.boss.core.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class AccountHolderTest
{
	static final Integer userId = 1;
	static final String fullName = "Test Sample";
	static final LocalDate dob = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	static final String ssn = "123-45-6789";
	static final String address = "address";
	static final String city = "city";
	static final String state = "state";
	static final Integer zip = 12345;
	static final String phone = "+12345678900";

	static AccountHolder accountHolder1, accountHolder2, accountHolder3;

	@AfterEach
	void teardown()
	{
		accountHolder1 = null;
		accountHolder2 = null;
		accountHolder3 = null;
	}

	@Test
	void test_CanConstructWithoutParameters()
	{
		accountHolder1 = new AccountHolder();
		assertThat(accountHolder1).isNotNull();
	}

	@Test
	void test_CanConstructWithAllParameters()
	{
		accountHolder1 = new AccountHolder(userId, fullName, dob, ssn, address, city, state, zip, phone, null, null);
		assertThat(accountHolder1).isNotNull();
	}

	@Test
	void test_CanAssignWithSettersAndRetrieveWithGetters()
	{
		accountHolder1 = new AccountHolder();
		// Set.
		accountHolder1.setUserId(userId);
		accountHolder1.setFullName(fullName);
		accountHolder1.setDob(dob);
		accountHolder1.setSsn(ssn);
		accountHolder1.setAddress(address);
		accountHolder1.setCity(city);
		accountHolder1.setState(state);
		accountHolder1.setZip(zip);
		accountHolder1.setPhone(phone);

		// Get.
		assertThat(accountHolder1.getUserId()).isEqualTo(userId);
		assertThat(accountHolder1.getFullName()).isEqualTo(fullName);
		assertThat(accountHolder1.getDob()).isEqualTo(dob);
		assertThat(accountHolder1.getSsn()).isEqualTo(ssn);
		assertThat(accountHolder1.getAddress()).isEqualTo(address);
		assertThat(accountHolder1.getCity()).isEqualTo(city);
		assertThat(accountHolder1.getState()).isEqualTo(state);
		assertThat(accountHolder1.getZip()).isEqualTo(zip);
		assertThat(accountHolder1.getPhone()).isEqualTo(phone);
	}

	@Test
	void test_CanCreateWithBuilder()
	{
		accountHolder1 = AccountHolder.builder().userId(userId).fullName(fullName).dob(dob).ssn(ssn).address(address)
				.city(city).state(state).zip(zip).phone(phone).build();
		assertThat(accountHolder1.getUserId()).isEqualTo(userId);
		assertThat(accountHolder1.getFullName()).isEqualTo(fullName);
		assertThat(accountHolder1.getDob()).isEqualTo(dob);
		assertThat(accountHolder1.getSsn()).isEqualTo(ssn);
		assertThat(accountHolder1.getAddress()).isEqualTo(address);
		assertThat(accountHolder1.getCity()).isEqualTo(city);
		assertThat(accountHolder1.getState()).isEqualTo(state);
		assertThat(accountHolder1.getZip()).isEqualTo(zip);
		assertThat(accountHolder1.getPhone()).isEqualTo(phone);
	}

	@Test
	void test_CanCompareWithEquals()
	{
		// Note, they have different IDs and SSN.
		accountHolder1 = new AccountHolder(userId, fullName, dob, ssn, address, city, state, zip, phone, null, null);
		accountHolder2 = new AccountHolder(2, fullName, dob, "234-56-7890", address, city, state, zip, phone, null, null);
		accountHolder3 = new AccountHolder(userId, fullName, dob, ssn, address, city, state, zip, phone, null, null);
		assertThat(accountHolder1).isNotEqualTo(accountHolder2);
		assertThat(accountHolder1).isEqualTo(accountHolder3);
	}

	@Test
	void test_CanCompareWithHashCode()
	{
		// Note, they have different IDs and SSN.
		accountHolder1 = new AccountHolder(userId, fullName, dob, ssn, address, city, state, zip, phone, null, null);
		accountHolder2 = new AccountHolder(2, fullName, dob, "234-56-7890", address, city, state, zip, phone, null, null);
		accountHolder3 = new AccountHolder(userId, fullName, dob, ssn, address, city, state, zip, phone, null, null);
		assertThat(accountHolder1.hashCode()).isNotEqualTo(accountHolder2.hashCode());
		assertThat(accountHolder1.hashCode()).isEqualTo(accountHolder3.hashCode());
	}
}
