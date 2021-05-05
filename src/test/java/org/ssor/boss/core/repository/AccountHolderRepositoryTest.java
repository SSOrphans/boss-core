package org.ssor.boss.core.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ssor.boss.core.entity.AccountHolder;

@DataJpaTest
public class AccountHolderRepositoryTest
{
	@Autowired
	public AccountHolderRepository accountHolderRepository;

	@Test
	public void test_CanFindById()
	{
		Optional<AccountHolder> result = accountHolderRepository.findById(1);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get().getFullName()).isNotNull();
		assertThat(result.get().getDob()).isNotNull();
		assertThat(result.get().getAddress()).isNotNull();
		assertThat(result.get().getCity()).isNotNull();
		assertThat(result.get().getState()).isNotNull();
		assertThat(result.get().getZip()).isNotNull();
		assertThat(result.get().getPhone()).isNotNull();
	}
}
