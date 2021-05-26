package org.ssor.boss.core.entity;

/**
 * @author Derrian Harris
 */
public enum LoanTypeEnum {
    LOAN_UNKNOWN(""),
    LOAN_STUDENT("Student"),
    LOAN_PERSONAL("Personal");

    private final String name;

    LoanTypeEnum(String name) {
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
