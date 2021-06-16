package org.ssor.boss.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "account", schema = "boss")
public class Account
{

  @Id
  @EqualsAndHashCode.Include
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "balance")
  private Float balance;
  @Column(name = "opened")
  private LocalDate opened;
  @Column(name = "closed")
  private LocalDate closed;
  @Column(name = "confirmed")
  private Boolean confirmed;
  @Column(name = "active")
  private Boolean active;

  @Column(name = "branch_id")
  private Integer branchId;

  @Enumerated
  @Column(name = "type_id")
  private AccountType accountType;

  @OneToMany
  @JoinTable(name = "account_users", schema = "boss",
             joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
  
}
