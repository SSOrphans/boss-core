package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.UserEntity;
import org.ssor.boss.core.entity.UserSettings;
import java.io.Serializable;
import java.time.LocalDate;

// TODO: convert to use embedded user info.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfileInfoTransfer implements Serializable {
	private String username;
	private String email;
	private long created;
	private String fullName;
	private LocalDate dob;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String phone;
	private UserSettings userSettings;

	public AccountProfileInfoTransfer(UserEntity userEntity) {
//		this.username = userEntity.getUser().getUsername();
//		this.email = userEntity.getUser().getEmail();
//		this.created = userEntity.getUser().getCreated();
//		this.fullName = userEntity.getFullName();
//		this.dob = userEntity.getDob();
//		this.address = userEntity.getAddress();
//		this.city = userEntity.getCity();
//		this.state = userEntity.getState();
//		this.zip = userEntity.getZip();
//		this.phone = userEntity.getPhone();
//		this.userSettings = userEntity.getUserSettings();
	}
}
