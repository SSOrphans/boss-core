package org.ssor.boss.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account
{

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "balance")
  private Float balance;
  @Column(name = "opened")
  private LocalDateTime opened;
  @Column(name = "closed")
  private LocalDateTime closed;
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
  @JoinTable(name = "account_users",
             joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;


  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return id.equals(account.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id);
  }
}
