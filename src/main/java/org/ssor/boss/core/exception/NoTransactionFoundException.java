package org.ssor.boss.exception;

import org.springframework.http.HttpStatus;

public class NoTransactionFoundException extends Exception
{
  public static final int ERROR_CODE = HttpStatus.NOT_FOUND.value();

  public static final String MESSAGE = "No Transaction Found";

  public NoTransactionFoundException()
  {
    super(MESSAGE);
  }

}