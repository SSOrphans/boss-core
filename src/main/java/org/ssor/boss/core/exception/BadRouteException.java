package org.ssor.boss.exception;

import org.springframework.http.HttpStatus;

public class BadRouteException extends Exception
{
  public static final int ERROR_CODE = HttpStatus.NOT_FOUND.value();
  public static final String MESSAGE = "Route not found";

  public BadRouteException()
  {
    super(MESSAGE);
  }


}
