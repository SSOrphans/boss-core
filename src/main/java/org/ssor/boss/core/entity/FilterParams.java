package org.ssor.boss.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * A class to simplify the usage of filtering parameters into a single object instead of multiple function parameters.
 *
 * @author John Christman
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterParams
{
  @NonNull
  private String keyword = "";
  private int filter = 0;
  private int page = 0;
  private int limit = 5;
  @NonNull
  private String sortBy = "id";
  @NonNull
  private String sortDir = "asc";
}
