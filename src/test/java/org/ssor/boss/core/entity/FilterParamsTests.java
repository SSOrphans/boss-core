package org.ssor.boss.core.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterParamsTests
{
  static FilterParams params1;
  static FilterParams params2;
  static FilterParams params3;

  @AfterEach
  void tearDown()
  {
    params1 = null;
    params2 = null;
    params3 = null;
  }

  @Test
  void test_CanConstruct_WithoutParameters()
  {
    params1 = new FilterParams();
    assertThat(params1).isNotNull();
  }

  @Test
  void test_CanConstruct_WithAllParameters()
  {
    params1 = new FilterParams("keyword", "", 5, 10, "id", "ASC");
    assertThat(params1).isNotNull();
  }

  @Test
  void test_CanAssignWithSetters_AndRetrieveWithGetters()
  {
    params1 = new FilterParams();
    assertThat(params1).isNotNull();

    params1.setKeyword("keyword");
    params1.setFilter("");
    params1.setPage(5);
    params1.setLimit(10);
    params1.setSortBy("id");
    params1.setSortDir("ASC");

    confirmIntegrity(params1);
  }

  @Test
  void test_CanConstruct_WithBuilder()
  {
    params1 = FilterParams.builder().keyword("keyword").filter("").page(5).limit(10).sortBy("id").sortDir("ASC").build();
    assertThat(params1).isNotNull();
    confirmIntegrity(params1);
  }

  @Test
  void test_CanCompare_WithEquals()
  {
    params1 = new FilterParams("keyword", "", 5, 10, "id", "ASC");
    params2 = new FilterParams("keyword", "", 5, 10, "id", "ASC");
    params3 = new FilterParams("wordkey", "", 0, 5, "id", "DESC");
    assertThat(params1).isEqualTo(params2);
    assertThat(params1).isNotEqualTo(params3);
  }

  @Test
  void test_CanCompare_WithHashcode()
  {
    params1 = new FilterParams("keyword", "", 5, 10, "id", "ASC");
    params2 = new FilterParams("keyword", "", 5, 10, "id", "ASC");
    params3 = new FilterParams("wordkey", "", 0, 5, "id", "DESC");
    assertThat(params1.hashCode()).isEqualTo(params2.hashCode());
    assertThat(params1.hashCode()).isNotEqualTo(params3.hashCode());
  }

  private void confirmIntegrity(FilterParams param) {
    assertThat(param.getKeyword()).isEqualTo("keyword");
    assertThat(param.getFilter()).isEqualTo("");
    assertThat(param.getPage()).isEqualTo(5);
    assertThat(param.getLimit()).isEqualTo(10);
    assertThat(param.getSortBy()).isEqualTo("id");
    assertThat(param.getSortDir()).isEqualTo("ASC");
  }
}
