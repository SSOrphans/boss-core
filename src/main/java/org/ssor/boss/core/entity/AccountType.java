package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "account_type")
public class AccountType
{
  @Getter
  public enum AccountTypes
  {
    SAVING("saving"), CHECKING("checking"), CARD("card");

    private String type;

    AccountTypes(String type) {this.type = type;}
  }

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountType that = (AccountType) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id);
  }
}
