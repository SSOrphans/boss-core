package org.ssor.boss.core.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

// TODO: fix all of this.
@Service
@EnableScheduling
public class PaymentConfirmationService {
//  @Autowired
//  TransactionRepository transactionRepository;
//  @Autowired
//  AccountRepository accountRepository;
//
//  @Scheduled(cron = "* * * ? * *")//"0 0 0 ? * MON,TUE,WED,THU,FRI *")
//  @Transactional
//  public void confirmPaymentTransaction(){
//    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
//    List<TransactionEntity> pendingTransactionEntities = transactionRepository.findPendingTransactionsByDate(startDate);
//    for (TransactionEntity t: pendingTransactionEntities) {
//      Long accountId = t.getAccountId();
//      Optional<AccountEntity> accountOptional = accountRepository.findById(accountId);
//
//      if(accountOptional.isEmpty())
//        continue;
//
//      AccountEntity accountEntity = accountOptional.get();
//
//      accountEntity.setBalance(accountEntity.getBalance() + t.getAmount());
//      t.setPending(false);
//
//      transactionRepository.save(t);
//      accountRepository.save(accountEntity);
//    }
//  }
}
