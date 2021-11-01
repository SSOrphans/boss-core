package org.ssor.boss.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.ssor.boss.core.entity.AccountEntity;
import org.ssor.boss.core.entity.AccountInfo;
import org.ssor.boss.core.entity.AccountType;
import org.ssor.boss.core.exception.AccountCreationException;
import org.ssor.boss.core.exception.NoAccountsFoundException;
import org.ssor.boss.core.exception.UserNotFoundException;
import org.ssor.boss.core.repository.AccountEntityRepository;
import org.ssor.boss.core.repository.UserEntityRepository;
import org.ssor.boss.core.transfer.AccountCreateRequest;
import org.ssor.boss.core.transfer.SecureAccountDetails;
import org.ssor.boss.core.transfer.ServiceResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * A service designed for implementing the bulk of functionality for its respective controller.
 *
 * @author John Christman
 * @author Bermond Jon Batistiana
 */
@Service
@RequiredArgsConstructor
public class AccountService {
	private final AccountEntityRepository accountRepository;
	private final UserEntityRepository userRepository;

	/**
	 * Creates a new account given the account creation parameters.
	 *
	 * @param accountParams The parameters necessary to create an account.
	 * @return The service response carrying the account details of the created account.
	 * @throws UserNotFoundException    If the user to create the account for doesn't exist.
	 * @throws AccountCreationException If the account could not be created correctly.
	 */
	public ServiceResponse<SecureAccountDetails> createAccount(AccountCreateRequest accountParams)
			throws UserNotFoundException, AccountCreationException {
		var id = Math.abs(UUID.randomUUID().getLeastSignificantBits() % 10000000000000000L);
		var user = userRepository.findById(accountParams.getUserIds()[0]).orElseThrow(UserNotFoundException::new);
		var accountType = AccountType.values()[accountParams.getAccountType()];
		var accountInfo = new AccountInfo(accountType.name());
		var accountEntity = new AccountEntity(id, accountType, 1, accountInfo);
		accountEntity.getUsers().add(user);

		try {
			accountRepository.save(accountEntity);
		}
		catch (DataIntegrityViolationException e) {
			throw new AccountCreationException();
		}

		return ServiceResponse.success(new SecureAccountDetails(accountEntity));
	}

	/**
	 * Gets all the accounts of a user with the provided ID.
	 *
	 * @param userId The ID of the user to get the accounts for.
	 * @return The service response carrying the list of account details for the user.
	 * @throws NoAccountsFoundException If no accounts where found for the user.
	 */
	public ServiceResponse<List<SecureAccountDetails>> getAccounts(long userId)
			throws NoAccountsFoundException {
		var accountEntities = accountRepository.findAccountsByUser(userId);
		if (accountEntities.isEmpty())
			throw new NoAccountsFoundException();

		var converted = accountEntities.stream().map(SecureAccountDetails::new).collect(Collectors.toList());
		return ServiceResponse.success(converted);
	}

	/**
	 * Gets a single account for a user.
	 *
	 * @param userId    The ID of the user to fetch the account of.
	 * @param accountId The ID of the account to fetch.
	 * @return The service response carrying the account details for the found account.
	 * @throws NoAccountsFoundException If no account was found for the user.
	 */
	public ServiceResponse<SecureAccountDetails> getAccount(long userId, Long accountId)
			throws NoAccountsFoundException {
		var accountEntity = accountRepository.findAccountByIdAndUserId(userId, accountId)
		                                     .orElseThrow(NoAccountsFoundException::new);
		return ServiceResponse.success(new SecureAccountDetails(accountEntity));
	}
}
