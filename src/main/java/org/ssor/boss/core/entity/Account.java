package org.ssor.boss.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
import java.io.Serializable;

/**
 * Describes the information of an account entity.
 *
 * @author Bermond Jon Batistiana
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "Account")
@Table(schema = "boss", name = "account")
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true)
public class Account implements Serializable
{
  @Id
  @EqualsAndHashCode.Include
  @Column(nullable = false, unique = true, updatable = false)
  private Long id;

  @Enumerated
  @Column(name = "type_id", nullable = false)
  private AccountType accountType;

  @Column(name = "branch_id", nullable = false)
  private Integer branchId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Float balance;

  @Column(nullable = false)
  private LocalDate opened;

  @Column(insertable = false)
  private LocalDate closed;

  @Column(nullable = false, updatable = false)
  private Boolean confirmed;

  @Column(nullable = false)
  private Boolean active;

  @OneToOne
  @JoinTable(schema = "boss", name = "account_miles_programs", joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "miles_program_id"))
  private MilesProgramEntity milesProgram;

  @OneToMany
  @JoinTable(name = "account_users", schema = "boss", joinColumns = @JoinColumn(name = "account_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
}
