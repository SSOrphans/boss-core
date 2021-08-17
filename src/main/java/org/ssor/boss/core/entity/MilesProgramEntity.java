package org.ssor.boss.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a single frequent flyer miles program managed by a given group.
 *
 * <p>
 * This entity merely describes the program as managed by a specific group and the name of the program. This is for our
 * systems to check a frequent flyer program (FFP) against a given account entity (noting that credit cards, which FFPs
 * are applied, are themselves accounts). The check makes sure we can accept a transaction for an account, backed by a
 * credit card, with FFP miles provided by the specific group. If the name of the program, or the group are incorrect
 * for the account, the transaction will be declined.
 *
 * @author John Christman
 */
@Getter
@Setter
@Entity(name = "MilesProgram")
@Table(schema = "boss", name = "miles_program")
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true)
public class MilesProgramEntity implements Serializable
{
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, unique = true, updatable = false)
  private Integer id;

  @Column(nullable = false, length = 32)
  private String name;

  @Column(nullable = false, unique = true, length = 32)
  private String org;

  /**
   * Default constructs the miles program entity.
   */
  public MilesProgramEntity()
  {
    id = null;
    name = "";
    org = "";
  }

  /**
   * Constructs the miles program entity, as a new entity.
   *
   * @param name  The name of the miles program to create.
   * @param org The name of the group creating the miles program.
   */
  public MilesProgramEntity(@NonNull String name, @NonNull String org)
  {
    this.id = null;
    this.name = name;
    this.org = org;
  }

  /**
   * Fully constructs the miles program entity, as an existing entity.
   *
   * @param id    The ID of the miles program.
   * @param name  The name of the miles program.
   * @param org The name of the group of the miles program.
   */
  public MilesProgramEntity(int id, @NonNull String name, @NonNull String org)
  {
    this.id = id;
    this.name = name;
    this.org = org;
  }
}
