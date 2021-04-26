package org.ssor.boss.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Derrian Harris
 *
 */
public enum LoanType {
	LOAN_STUDENT("Student"),
	LOAN_PERSONAL("Personal");

	private String name;

	LoanType(String name) {
		this.name = name;
	}

	public int index()
	{
		return ordinal();
	}

	@Override
	public String toString() {
		return this.name;
	}
}
