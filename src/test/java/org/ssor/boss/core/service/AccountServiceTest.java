package org.ssor.boss.core.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ssor.boss.core.entity.RewardType;
import org.ssor.boss.core.entity.RewardsProgram;
import org.ssor.boss.core.entity.Transaction;
import org.ssor.boss.core.exception.AccountCreationException;
import org.ssor.boss.core.exception.AccountTypeNotFoundException;
import org.ssor.boss.core.exception.NoAccountsFoundException;
import org.ssor.boss.core.exception.UserNotFoundException;
import org.ssor.boss.core.repository.AccountRepository;
import org.ssor.boss.core.entity.Account;
import org.ssor.boss.core.entity.AccountType;
import org.ssor.boss.core.entity.User;
import org.ssor.boss.core.repository.RewardsProgramRepository;
import org.ssor.boss.core.repository.TransactionRepository;
import org.ssor.boss.core.repository.UserRepository;
import org.ssor.boss.core.transfer.AccountToCreateTransfer;
import org.ssor.boss.core.transfer.AccountTransfer;
import org.ssor.boss.core.transfer.TransactionInput;
import org.ssor.boss.core.transfer.TransactionTransfer;
import org.ssor.boss.core.transfer.UserAccountsTransfer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AccountServiceTest
{
  @MockBean
  AccountRepository accountRepository;
  @MockBean
  UserRepository userRepository;
  @MockBean
  RewardsProgramRepository rewardsProgramRepository;
  @MockBean
  TransactionRepository transactionRepository;

  @InjectMocks
  AccountService accountService;

  private static User stubbedUser;
  private static AccountToCreateTransfer stubbedAccountDto;
  private static List<Account> stubbedAccountEntities;
  private static Account stubbedAccount;
  private static RewardsProgram stubbedRewards;
  private static Account stubbedRewardAccount;

  @BeforeAll
  static void setUp()
  {

    User user = new User();

    user.setId(2);
    user.setEnabled(true);
    user.setCreated(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    user.setEmail("testB@email.com");
    user.setUsername("testB");
    user.setPassword("TestPassword");

    AccountToCreateTransfer accountDto = new AccountToCreateTransfer();
    accountDto.setUserId(2);
    accountDto.setAccountType(2);
    accountDto.setBranchId(3);

    Account ae1 = new Account();
    ae1.setId(1L);
    ae1.setName("Test1");
    ae1.setBalance(12.34f);
    ae1.setPendingBalance(44.50f);
    ae1.setAccountType(AccountType.ACCOUNT_CHECKING);
    Account ae2 = new Account();
    ae2.setId(2L);
    ae2.setName("Test2");
    ae2.setBalance(56.78f);
    ae2.setPendingBalance(50.00f);
    ae2.setAccountType(AccountType.ACCOUNT_SAVING);

    Account ae3 = new Account();
    ae3.setId(1L);
    ae3.setName("Test3");
    ae3.setBalance(100f);
    ae3.setPendingBalance(100f);
    ae3.setAccountType(AccountType.ACCOUNT_CHECKING);

    List<Account> accountList = new ArrayList<>();

    accountList.add(ae1);
    accountList.add(ae2);

    RewardsProgram cashbackReward = new RewardsProgram();
    cashbackReward.setId(1);
    cashbackReward.setName("Bank of Smoothstack");
    cashbackReward.setOrganization("BOSS");
    cashbackReward.setType(RewardType.REWARD_CASHBACK);
    cashbackReward.setCashbackRate(0.03f);

    stubbedUser = user;
    stubbedAccountDto = accountDto;
    stubbedAccountEntities = accountList;
    stubbedAccount = ae1;
    stubbedRewardAccount = ae3;
    stubbedRewards = cashbackReward;
  }

  @Test
  void test_canGetAccounts() throws NoAccountsFoundException
  {
    Mockito.doReturn(stubbedAccountEntities).when(accountRepository).findAccountsByUser(Mockito.anyInt());
    UserAccountsTransfer expectedUserAccountsTransfer = new UserAccountsTransfer();
    expectedUserAccountsTransfer.setAccountsFromEntity(stubbedAccountEntities);

    UserAccountsTransfer actualUserAccountsTransfer = accountService.getAccounts(1);

    assertEquals(expectedUserAccountsTransfer, actualUserAccountsTransfer);
  }

  @Test
  void test_canCreateAccount() throws UserNotFoundException, AccountCreationException, AccountTypeNotFoundException
  {
    Account expectedAccount  = new Account();
    expectedAccount.setId(1L);
    Mockito.doReturn(Optional.of(stubbedUser)).when(userRepository).findById(Mockito.anyInt());
    Mockito.doReturn(expectedAccount).when(accountRepository).save(Mockito.any(Account.class));


    ResponseEntity<String> rs = new ResponseEntity(expectedAccount,HttpStatus.CREATED);
    ResponseEntity<Account> actualAccount = accountService.createAccount(stubbedAccountDto);
    assertEquals(rs, actualAccount);
  }

  @Test
  void test_willThrowUserNotFoundException() throws UserNotFoundException, AccountCreationException
  {
    Mockito.doReturn(Optional.empty()).when(userRepository).findById(Mockito.anyInt());

    UserNotFoundException exception =
        assertThrows(UserNotFoundException.class, () ->
            accountService.createAccount(stubbedAccountDto)
        );

    assertEquals(UserNotFoundException.MESSAGE, exception.getMessage());
    assertEquals(404, UserNotFoundException.ERROR_CODE);
  }

  @Test
  void test_willThrowAccountCreationException()
  {
    Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
    Mockito.doThrow(new DataIntegrityViolationException("")).when(accountRepository)
           .save(Mockito.any(Account.class));

    AccountCreationException exception =
        assertThrows(AccountCreationException.class, () ->
            accountService.createAccount(stubbedAccountDto)
        );

    assertEquals(AccountCreationException.MESSAGE, exception.getMessage());
    assertEquals(400, AccountCreationException.ERROR_CODE);
  }

  @Test
  void test_willThrowAccountNotFoundException() throws NoAccountsFoundException
  {
    Mockito.doReturn(new ArrayList<>()).when(accountRepository)
           .findAccountsByUser(Mockito.anyInt());

    assertThrows(NoAccountsFoundException.class, () ->
        accountService.getAccounts(0));

  }

  @Test
  void test_canGetAccount() throws NoAccountsFoundException {
    Mockito.doReturn(Optional.of(stubbedAccount))
           .when(accountRepository)
           .findAccountByIdAndUserId(Mockito.anyInt(), Mockito.anyLong());

    var expected = new AccountTransfer(stubbedAccount);
    assertEquals(expected, accountService.getAccount(1,1L));
  }

  @Test
  void test_canThrowAccountNotFoundInCanGetAccount() {
    Mockito.doReturn(Optional.empty())
           .when(accountRepository)
           .findAccountByIdAndUserId(Mockito.anyInt(), Mockito.anyLong());

    NoAccountsFoundException exception =
        assertThrows(NoAccountsFoundException.class, () -> {
          accountService.getAccount(1,1L);
        });

    assertEquals(NoAccountsFoundException.MESSAGE, exception.getMessage());
    assertEquals(404, NoAccountsFoundException.ERROR_CODE);
  }

  @Test
  void test_canCreateAccountPaymentWithCashback() {
    TransactionInput testInput = new TransactionInput();
    testInput.setAccountId(1L);
    testInput.setAmount(-100f);
    testInput.setDate(LocalDateTime.now());
    testInput.setMerchant("Bank of Smoothstack");
    testInput.setType("TRANSACTION_PAYMENT");
    Mockito.doReturn(Optional.of(stubbedRewardAccount))
           .when(accountRepository)
           .findById(Mockito.anyLong());
    Mockito.doReturn(Optional.of(stubbedRewards))
           .when(rewardsProgramRepository)
           .findByNameAndType(Mockito.anyString(), Mockito.any(RewardType.class));
    Mockito.when(transactionRepository.save(Mockito.any()))
        .thenAnswer(args ->  {  Transaction transaction = args.getArgument(0);
                                transaction.setId(1);
                                return transaction;});
    Mockito.doReturn(stubbedRewardAccount)
           .when(accountRepository)
           .save(Mockito.any());

    var expected = new TransactionTransfer();
    expected.setId(1);
    expected.setAmount(-97f);
    expected.setDate(testInput.getDate());
    expected.setMerchant(testInput.getMerchant());
    expected.setBalance(3f);
    expected.setType(testInput.getType());
    expected.setPending(true);
    TransactionTransfer actual = accountService.createAccountPayment(testInput);
    assertEquals(expected, actual);
  }

  @Test
  void test_canCreateAccountPaymentWithoutCashback() {
    TransactionInput testInput = new TransactionInput();
    testInput.setAccountId(1L);
    testInput.setAmount(-100f);
    testInput.setDate(LocalDateTime.now());
    testInput.setMerchant("Test Merchant");
    testInput.setType("TRANSACTION_PAYMENT");
    Mockito.doReturn(Optional.of(stubbedRewardAccount))
           .when(accountRepository)
           .findById(Mockito.anyLong());
    Mockito.doReturn(Optional.empty())
           .when(rewardsProgramRepository)
           .findByNameAndType(Mockito.anyString(), Mockito.any(RewardType.class));
    Mockito.when(transactionRepository.save(Mockito.any()))
           .thenAnswer(args ->  {  Transaction transaction = args.getArgument(0);
             transaction.setId(1);
             return transaction;});
    Mockito.doReturn(stubbedRewardAccount)
           .when(accountRepository)
           .save(Mockito.any());

    var expected = new TransactionTransfer();
    expected.setId(1);
    expected.setAmount(-100f);
    expected.setDate(testInput.getDate());
    expected.setMerchant(testInput.getMerchant());
    expected.setBalance(0f);
    expected.setType(testInput.getType());
    expected.setPending(true);
    TransactionTransfer actual = accountService.createAccountPayment(testInput);
    assertEquals(expected, actual);
  }
}
