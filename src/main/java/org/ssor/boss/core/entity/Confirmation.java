package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Objects;

/**
 * Describes a confirmation entry.
 *
 * @author John Christman
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirmation", schema = "boss", uniqueConstraints = {
  @UniqueConstraint(columnNames = "id"),
  @UniqueConstraint(columnNames = "confirmation_hash")
})
public class Confirmation implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  @Column(name = "type_id")
  private ConfirmationType type;
  @Column(name = "confirmable_id")
  private int confirmableId;
  @Column(name = "confirmation_hash")
  private String confirmationHash;
  @Column(name = "good_until")
  private long goodUntil;

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Confirmation that = (Confirmation) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id);
  }
}
