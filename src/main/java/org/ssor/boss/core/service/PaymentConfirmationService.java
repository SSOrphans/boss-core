package org.ssor.boss.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssor.boss.core.entity.Account;
import org.ssor.boss.core.entity.Transaction;
import org.ssor.boss.core.repository.AccountRepository;
import org.ssor.boss.core.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class PaymentConfirmationService
{

  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  AccountRepository accountRepository;

  @Scheduled(cron = "* * * ? * *")//"0 0 0 ? * MON,TUE,WED,THU,FRI *")
  @Transactional
  public void confirmPaymentTransaction(){
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    List<Transaction> pendingTransactions = transactionRepository.findPendingTransactionsByDate(startDate);
    for (Transaction t: pendingTransactions) {
      Long accountId = t.getAccountId();
      Optional<Account> accountOptional = accountRepository.findById(accountId);

      if(accountOptional.isEmpty())
        continue;

      Account account = accountOptional.get();

      account.setBalance(account.getBalance() + t.getAmount());
      t.setPending(false);

      transactionRepository.save(t);
      accountRepository.save(account);
    }
  }


}
