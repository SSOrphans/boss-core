package org.ssor.boss.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ssor.boss.core.exception.AccountCreationException;
import org.ssor.boss.core.exception.NoAccountsFoundException;
import org.ssor.boss.core.exception.UserNotFoundException;
import org.ssor.boss.core.repository.AccountRepository;
import org.ssor.boss.core.entity.Account;
import org.ssor.boss.core.entity.AccountType;
import org.ssor.boss.core.entity.Transaction;
import org.ssor.boss.core.entity.TransactionType;
import org.ssor.boss.core.repository.TransactionRepository;
import org.ssor.boss.core.repository.UserRepository;
import org.ssor.boss.core.transfer.AccountToCreateTransfer;
import org.ssor.boss.core.transfer.AccountTransfer;
import org.ssor.boss.core.transfer.TransactionInput;
import org.ssor.boss.core.transfer.UserAccountsTransfer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService
{
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  UserRepository userRepository;

  public UserAccountsTransfer getAccounts(Integer userId) throws NoAccountsFoundException
  {
    List<Account> accountEntities = accountRepository.findAccountsByUser(userId);
    if (accountEntities.isEmpty())
      throw new NoAccountsFoundException();

    var userAccountsDTO = new UserAccountsTransfer();
    userAccountsDTO.setAccountsFromEntity(accountEntities);

    return userAccountsDTO;
  }

  public AccountTransfer getAccount(Integer userId, Long accountId) throws NoAccountsFoundException
  {
    Optional<Account> account = accountRepository.findAccountByIdAndUserId(userId, accountId);
    if (account.isEmpty())
      throw new NoAccountsFoundException();

    return new AccountTransfer(account.get());

  }

  public ResponseEntity<String> createAccount(AccountToCreateTransfer accountParams) throws
          UserNotFoundException, AccountCreationException
  {

    Long id =Math.abs(UUID.randomUUID().getLeastSignificantBits() % 10000000000000000L);
    var user = userRepository.findById(accountParams.getUserId()).orElseThrow(UserNotFoundException::new);
    var accountType = AccountType.values()[accountParams.getAccountType()];

    var accountEntity = new Account();
    accountEntity.setId(id);
    accountEntity.setAccountType(accountType);
    accountEntity.setUsers(List.of(user));
    accountEntity.setBranchId(accountParams.getBranchId());
    accountEntity.setName(accountType.name().substring(8));
    accountEntity.setBalance(0f);
    accountEntity.setOpened(LocalDate.now());
    accountEntity.setConfirmed(false);
    accountEntity.setActive(false);

    try
    {
      accountRepository.save(accountEntity);
    }
    catch (DataIntegrityViolationException e)
    {
      throw new AccountCreationException();
    }

    return new ResponseEntity<>("New account created.",HttpStatus.CREATED);
  }

  public ResponseEntity<String> createAccountPayment(TransactionInput transactionInput) {

    Optional<Account> accountOpt = accountRepository.findById(transactionInput.getAccountId());

    if(accountOpt.isEmpty())
      return new ResponseEntity<>("No account found: " + transactionInput.getAccountId(),HttpStatus.NOT_FOUND);

    Account account = accountOpt.get();

    account.setBalance(account.getBalance() + transactionInput.getAmount());

    var transaction = new Transaction();
    transaction.setAccountId(transactionInput.getAccountId());
    transaction.setAmount(transactionInput.getAmount());
    transaction.setDate(LocalDateTime.now());
    transaction.setMerchantName(transactionInput.getMerchant());
    transaction.setType(TransactionType.TRANSACTION_PAYMENT);
    transaction.setNewBalance(account.getBalance());

    transaction.setPending(true);
    transaction.setSucceeded(false);
    transactionRepository.save(transaction);
    accountRepository.save(account);

    return new ResponseEntity<>("Payment created.",HttpStatus.CREATED);
  }
}
