package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Derrian Harris
 */
public class LoanTypeTest
{
  @Test
  void test_CanGetIndexOfUserType()
  {
    assertThat(LoanType.LOAN_STUDENT.index()).isEqualTo(0);
    assertThat(LoanType.LOAN_PERSONAL.index()).isEqualTo(1);
  }

  @Test
  void test_CanConvertEnumerationToString()
  {
    assertThat(LoanType.LOAN_STUDENT.toString()).isEqualTo("Student");
    assertThat(LoanType.LOAN_PERSONAL.toString()).isEqualTo("Personal");
  }
}
