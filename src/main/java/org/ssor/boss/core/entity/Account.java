package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
  private Float balance;
  private LocalDateTime opened;
  private LocalDateTime closed;
  private Boolean confirmed;
  private Boolean active;
  @Column(name = "branch_id")
  private Integer branchId;

  @OneToMany
  @JoinTable(name = "account_users", joinColumns = @JoinColumn(name = "account_id"),
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
