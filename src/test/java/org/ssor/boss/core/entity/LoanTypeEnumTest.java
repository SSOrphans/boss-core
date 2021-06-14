package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Derrian Harris
 */
public class LoanTypeEnumTest {
    @Test
    void test_CanGetIndexOfLoanType() {
        assertThat(LoanTypeEnum.LOAN_UNKNOWN.index()).isEqualTo(0);
        assertThat(LoanTypeEnum.LOAN_STUDENT.index()).isEqualTo(1);
        assertThat(LoanTypeEnum.LOAN_PERSONAL.index()).isEqualTo(2);
    }

    @Test
    void test_CanConvertEnumerationToString() {
        assertThat(LoanTypeEnum.LOAN_UNKNOWN.toString()).isEqualTo("");
        assertThat(LoanTypeEnum.LOAN_STUDENT.toString()).isEqualTo("Student");
        assertThat(LoanTypeEnum.LOAN_PERSONAL.toString()).isEqualTo("Personal");
    }
}
