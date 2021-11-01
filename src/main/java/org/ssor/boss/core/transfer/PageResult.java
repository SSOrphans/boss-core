package org.ssor.boss.core.transfer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Allows transfer of paging results as the response in a {@link ServiceResponse}.
 *
 * @param <TDataType> The type of data that was obtained in the service response.
 * @author John Christman
 */
@Data
@RequiredArgsConstructor
public class PageResult<TDataType> {
	private final List<TDataType> data;
	private final int page;
	private final int pages;
	private final int count;
}
