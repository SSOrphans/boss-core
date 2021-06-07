package org.ssor.boss.core.transfer;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FinalizeConfirmationInputTests
{
  @Test
  void test_CanCreate_WithoutParameters()
  {
    final var input = new FinalizeConfirmationInput();
    assertThat(input).isNotNull();
  }

  @Test
  void test_CanCreate_WithAllParameters()
  {
    final var input = new FinalizeConfirmationInput("Some Confirmation Code");
    assertThat(input).isNotNull();
    assertThat(input.getConfirmationCode()).isEqualTo("Some Confirmation Code");
  }

  @Test
  void test_CanAssignWithSettersAndRetrieveWithGetters()
  {
    final var input = new FinalizeConfirmationInput();
    assertThat(input).isNotNull();

    input.setConfirmationCode("Some Confirmation Code");
    assertThat(input.getConfirmationCode()).isEqualTo("Some Confirmation Code");
  }

  @Test
  void test_CanCompare_WithEquals()
  {
    final var input1 = new FinalizeConfirmationInput("First Confirmation Code");
    final var input2 = new FinalizeConfirmationInput("Second Confirmation Code");
    final var input3 = new FinalizeConfirmationInput("First Confirmation Code");
    assertThat(input2).isNotEqualTo(input1);
    assertThat(input3).isEqualTo(input1);
  }

  @Test
  void test_CanCompare_WithHashCode()
  {
    final var input1 = new FinalizeConfirmationInput("First Confirmation Code");
    final var input2 = new FinalizeConfirmationInput("Second Confirmation Code");
    final var input3 = new FinalizeConfirmationInput("First Confirmation Code");
    assertThat(input2.hashCode()).isNotEqualTo(input1.hashCode());
    assertThat(input3.hashCode()).isEqualTo(input1.hashCode());
  }
}
