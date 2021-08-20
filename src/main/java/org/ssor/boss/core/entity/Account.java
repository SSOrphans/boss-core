package org.ssor.boss.core.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
import java.io.Serializable;

/**
 * Describes the information of an account entity.
 *
 * @author Bermond Jon Batistiana
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account", schema = "boss", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Account implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  @Enumerated
  @Column(name = "type_id")
  private AccountType accountType;
  @Column(name = "name")
  private String name;
  @Column(name = "balance")
  private Float balance;
  @Column(name = "pending_balance")
  private Float pendingBalance;
  @Column(name = "opened")
  private LocalDate opened;
  @Column(name = "closed")
  private LocalDate closed;
  @Column(name = "confirmed")
  private Boolean confirmed;
  private Boolean active;
  @Column(name = "branch_id")
  private Integer branchId;

  @OneToMany
  @JoinTable(name = "account_users", schema = "boss", joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;

  @OneToOne(mappedBy = "account")
  private Loan loan;

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
