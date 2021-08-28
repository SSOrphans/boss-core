package org.ssor.boss.core.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Represents a debit card,
 *
 * @author John Christman
 */
@DiscriminatorValue(value = "1")
@Entity(name = "DebitCardEntity")
@PrimaryKeyJoinColumn(name = "backing")
@Table(schema = "boss", name = "debit_card")
public class DebitCardEntity extends CardEntity {
}
