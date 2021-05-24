package org.ssor.boss.core.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTypeTest
{

  @Test
  void test_canGetValueOfType(){
    assertEquals("ACCOUNT_SAVING", AccountType.ACCOUNT_SAVING.toString());
    assertEquals("ACCOUNT_CHECKING", AccountType.ACCOUNT_CHECKING.toString());
    assertEquals("ACCOUNT_CREDIT", AccountType.ACCOUNT_CREDIT.toString());
    assertEquals("ACCOUNT_INVALID", AccountType.ACCOUNT_INVALID.toString());
  }
}
