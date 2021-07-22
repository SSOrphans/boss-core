package org.ssor.boss.core.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

/**
 * A class to simplify the usage of filtering parameters into a single object instead of multiple function parameters.
 *
 * @author John Christman
 */
@Data
@Builder
@RequiredArgsConstructor
public class FilterParams
{
  private final String keyword;
  private final String filter;
  private final int page;
  private final int limit;
  private final String sortBy;
  private final String sortDir;

  public FilterParams(Optional<String> keyword, Optional<String> filter, Optional<Integer> page,
                      Optional<Integer> limit, Optional<String> sortBy, Optional<String> sortDir)
  {
    this.keyword = keyword.orElse("");
    this.filter = filter.orElse("");
    this.page = page.orElse(0);
    this.limit = limit.orElse(5);
    this.sortBy = sortBy.orElse("id");
    this.sortDir = sortDir.orElse("asc");
  }
}
