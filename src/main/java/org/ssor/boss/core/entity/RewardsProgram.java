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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "rewards_program", schema = "boss", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class RewardsProgram
{
  @Id
  @Column(name = "id")
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated
  @Column(name = "type")
  private RewardType type;
  @Column(name = "name")
  private String name;
  @Column(name = "organization")
  private String organization;
  @Column(name = "cashback_rate")
  private Float cashbackRate;
}
