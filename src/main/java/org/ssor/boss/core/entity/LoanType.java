package org.ssor.boss.core.entity;

/**
 * @author Derrian Harris
 */
public enum LoanType {
    LOAN_STUDENT("Student"),
    LOAN_PERSONAL("Personal");

    private final String name;

    LoanType(String name) {
        this.name = name;
    }

    public int index() {
        return ordinal();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
