package org.ssor.boss.core.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * Represents a response from a service.
 *
 * <p>
 * A service response is one of either a data object, or an API error. Data is sent on a successful request process. In
 * other words, when the status is 200, a data object is likely to be sent as the response. When the status is 4xx or
 * 5xx, an API error is likely to be sent as the response. The service response object encapsulates both.
 *
 * <p>
 * It allows either result to be the response by allowing both to be nullable. This way it is easy for the caller to
 * determine what result they got. However, it is recommended that the status of the request be checked first as the
 * result may not be a service response if something couldn't be handled properly, or the service experienced an
 * exception that doesn't normally trigger a service response.
 *
 * @author John Christman
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ServiceResponse<TDataType> implements Serializable {
  private TDataType data;
  private ApiErrorResponse error;

  /**
   * Default constructs the response.
   */
  public ServiceResponse() {
    this(null, null);
  }

  /**
   * Constructs the response from the provided information.
   *
   * @param data  The data to return to the caller.
   * @param error An error to return if one occurred.
   */
  public ServiceResponse(TDataType data, ApiErrorResponse error) {
    this.data = data;
    this.error = error;
  }

  /**
   * Creates a successful response containing the data from the success.
   *
   * @param data The data from a successfully processed request.
   * @param <U>  The type of the data to return.
   * @return A service response containing the data acquired.
   */
  public static <U> ServiceResponse<U> success(U data) {
    return new ServiceResponse<>(data, null);
  }

  /**
   * Creates a failed response containing the error from the failure.
   *
   * @param error The error from an erroneously processed request.
   * @param <U>   The type of the data to return.
   * @return A service response containing the error acquired.
   */
  public static <U> ServiceResponse<U> failure(ApiErrorResponse error) {
    return new ServiceResponse<>(null, error);
  }
}
