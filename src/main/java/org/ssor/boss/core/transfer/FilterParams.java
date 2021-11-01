package org.ssor.boss.core.transfer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Stores parameters for filtering and sorting of repository entities.
 *
 * @author John Christman
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FilterParams {
	private String keyword;
	private String filter;
	private String sortBy;
	private String sortDir;
	private int page;
	private int count;

	/**
	 * Default constructs the filtering params.
	 */
	public FilterParams() {
		keyword = "";
		filter = "";
		sortBy = "";
		sortDir = "";
		page = 0;
		count = 0;
	}

	/**
	 * Creates the filter params from the provided information.
	 *
	 * @param keyword A keyword for searching the repository.
	 * @param filter  A filter for obtaining only relevant entities.
	 * @param sortBy  What to sort the entities by.
	 * @param sortDir The direction to sort the entities, typically ASC or DESC.
	 * @param page    The page of entities to get.
	 * @param count   The number of entities to get per page.
	 */
	public FilterParams(String keyword, String filter, String sortBy, String sortDir, Integer page, Integer count)
	{
		this.keyword = keyword;
		this.filter = filter;
		this.sortBy = sortBy;
		this.sortDir = sortDir;
		this.page = page;
		this.count = count;
	}
}
