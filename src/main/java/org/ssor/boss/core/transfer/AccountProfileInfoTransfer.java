package org.ssor.boss.core.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssor.boss.core.entity.AccountHolder;
import org.ssor.boss.core.entity.Settings;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfileInfoTransfer implements Serializable{
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
    private Settings settings;


    public AccountProfileInfoTransfer(AccountHolder accountHolder){
        this.username = accountHolder.getUser().getUsername();
        this.email = accountHolder.getUser().getEmail();
        this.created = accountHolder.getUser().getCreated();
        this.fullName = accountHolder.getFullName();
        this.dob = accountHolder.getDob();
        this.address = accountHolder.getAddress();
        this.city = accountHolder.getCity();
        this.state = accountHolder.getState();
        this.zip = accountHolder.getZip();
        this.phone = accountHolder.getPhone();
        this.settings = accountHolder.getUserSettings();
    }
}
