package org.ssor.boss.core.entity;

/**
 * Describes the different types of loans.
 *
 * @author Derrian Harris
 */
public enum LoanType
{
  LOAN_UNKNOWN,
  LOAN_STUDENT,
  LOAN_PERSONAL;

  public int index()
  {
    return ordinal();
  }

  @Override
  public String toString()
  {
    String result = "Unknown";
    if (this == LOAN_STUDENT)
      result = "Student";
    else if (this == LOAN_PERSONAL)
      result = "Personal";

    return result;
  }
}
