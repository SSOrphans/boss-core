package org.ssor.boss.core.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Exception
{

  public static final int ERROR_CODE = HttpStatus.NOT_FOUND.value();
  public static final String MESSAGE = "User not found";

  public UserNotFoundException()
  {
    super(MESSAGE);
  }
}
