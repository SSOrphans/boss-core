package org.ssor.boss.core.transfer;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class GenerateConfirmationOutputTests
{
  @Test
  void test_CanCreateGenerateConfirmationOutput_WithAllParameters()
  {
    final var hash = UUID.randomUUID().toString();
    final var output = new GenerateConfirmationOutput(1, hash, 0);
    assertThat(output).isNotNull();
  }

  @Test
  void test_CanCompare_WithEquals()
  {
    final var hash = UUID.randomUUID().toString();
    final var output1 = new GenerateConfirmationOutput(1, hash, 0);
    final var output2 = new GenerateConfirmationOutput(2, UUID.randomUUID().toString(), 0);
    final var output3 = new GenerateConfirmationOutput(1, hash, 0);
    assertThat(output2).isNotEqualTo(output1);
    assertThat(output3).isEqualTo(output1);
  }

  @Test
  void test_CanCompare_WithHashCode()
  {
    final var hash = UUID.randomUUID().toString();
    final var output1 = new GenerateConfirmationOutput(1, hash, 0);
    final var output2 = new GenerateConfirmationOutput(2, UUID.randomUUID().toString(), 0);
    final var output3 = new GenerateConfirmationOutput(1, hash, 0);
    assertThat(output2.hashCode()).isNotEqualTo(output1.hashCode());
    assertThat(output3.hashCode()).isEqualTo(output1.hashCode());
  }
}
