package org.ssor.boss.core.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.time.Instant;

/**
 * Represents the response a service will give when an error occurs within the API layer of the application.
 *
 * <p>
 * An API error is considered to be the point of failure between the caller and callee of an HTTP request. Specifically,
 * when the caller of an HTTP request provides erroneous data to the callee or when the callee is unable to handle a
 * request due to some internal problem, this response will be given with a detailed description of what happened,
 * typically.
 *
 * <p>
 * There may be times when the service is unable to provide fully detailed descriptions of what happened on its end that
 * caused a problem. In these cases, most commonly occurring with 500 internal server error, an API error will still be
 * triggered, but an empty message may be sent or an impartial message may be given.
 *
 * @author John Christman
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ApiErrorResponse implements Serializable {
	private String timestamp;
	private int status;
	private String reason;
	private String message;
	private String path;

	/**
	 * No args constructor for JSON serialization.
	 */
	public ApiErrorResponse() throws NullPointerException {
		this("/");
	}

	/**
	 * Default constructs the error response.
	 *
	 * <p>
	 * The default construction of the error response sets the timestamp to the current time and the status/error to an
	 * internal server error. The message is left empty. The path is to be provided, non-null.
	 *
	 * @param path The path the error took place on.
	 * @throws NullPointerException If the path is null.
	 */
	public ApiErrorResponse(@NonNull String path) throws NullPointerException {
		this(HttpStatus.INTERNAL_SERVER_ERROR, "", path);
	}

	/**
	 * Constructs the error response from all the important parameters.
	 *
	 * @param status  The status of the error.
	 * @param message The message of the error.
	 * @param path    The path the error took place on.
	 * @throws NullPointerException If any of the parameters are null.
	 */
	public ApiErrorResponse(@NonNull HttpStatus status, @NonNull String message, @NonNull String path)
			throws NullPointerException {
		this(Instant.now().toString(), status.value(), status.getReasonPhrase(), message, path);
	}

	/**
	 * Constructs the error response from all the required parameters.
	 *
	 * @param timestamp The timestamp of the error.
	 * @param status    The status of the error.
	 * @param reason    The reason of the error.
	 * @param message   The detailed error message.
	 * @param path      the path the error took place on.
	 * @throws NullPointerException If any of the parameters are null.
	 */
	public ApiErrorResponse(@NonNull String timestamp, int status, @NonNull String reason, @NonNull String message,
	                        @NonNull String path)
			throws NullPointerException {
		this.timestamp = timestamp;
		this.status = status;
		this.reason = reason;
		this.message = message;
		this.path = path;
	}
}
