package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Derrian Harris
 */
public class LoanTypeTest
{
  @Test
  void test_CanGetIndexOfLoanType()
  {
    assertThat(LoanType.LOAN_UNKNOWN.index()).isEqualTo(0);
    assertThat(LoanType.LOAN_STUDENT.index()).isEqualTo(1);
    assertThat(LoanType.LOAN_PERSONAL.index()).isEqualTo(2);
  }

  @Test
  void test_CanConvertEnumerationToString()
  {
    assertThat(LoanType.LOAN_UNKNOWN.toString()).isEqualTo("Unknown");
    assertThat(LoanType.LOAN_STUDENT.toString()).isEqualTo("Student");
    assertThat(LoanType.LOAN_PERSONAL.toString()).isEqualTo("Personal");
  }
}
