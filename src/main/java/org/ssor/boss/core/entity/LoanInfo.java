package org.ssor.boss.core.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains the information of a loan.
 *
 * @author John Christman
 */
@Getter
@Setter
@Embeddable
public class LoanInfo implements Serializable {
	private float total;
	private float interest;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime takenAt;

	@Temporal(TemporalType.DATE)
	private LocalDate dueBy;

	/**
	 * Default constructs the loan info.
	 */
	public LoanInfo() {
		total = 0;
		interest = 0;
		takenAt = LocalDateTime.now();
		dueBy = LocalDate.now();
	}

	/**
	 * Creates the loan info from the given total and interest.
	 *
	 * @param total    The total amount taken out with the loan.
	 * @param interest The interest applied to the loan.
	 */
	public LoanInfo(float total, float interest) {
		this.total = total;
		this.interest = interest;
		this.takenAt = LocalDateTime.now();
		this.dueBy = LocalDate.now().plusMonths(1);
	}
}
