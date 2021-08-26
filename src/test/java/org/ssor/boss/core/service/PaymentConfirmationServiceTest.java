package org.ssor.boss.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.ssor.boss.core.entity.Account;
import org.ssor.boss.core.entity.AccountType;
import org.ssor.boss.core.entity.Transaction;
import org.ssor.boss.core.entity.TransactionType;
import org.ssor.boss.core.repository.AccountRepository;
import org.ssor.boss.core.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentConfirmationServiceTest {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    PaymentConfirmationService paymentConfirmationService;

    List<Transaction> transactionList;
    Account stubbedAccount;

    @BeforeEach
    public void setup(){
        stubbedAccount = new Account();
        stubbedAccount.setBalance(100f);
        stubbedAccount.setPendingBalance(90f);
        stubbedAccount.setActive(true);
        stubbedAccount.setBranchId(1);
        stubbedAccount.setConfirmed(true);
        stubbedAccount.setId(1L);
        stubbedAccount.setName("Test Name");
        stubbedAccount.setAccountType(AccountType.ACCOUNT_CHECKING);


        transactionList = new ArrayList<>();
        Transaction transactionA = new Transaction();

        transactionA.setAccountId(1L);
        transactionA.setSucceeded(false);
        transactionA.setAtmTransactionId(1);
        transactionA.setPending(true);
        transactionA.setDate(LocalDateTime.now().minusDays(5));
        transactionA.setOverdraftId(1);
        transactionA.setAmount(-20f);
        transactionA.setId(1);
        transactionA.setMerchantName("Stubbed Merchant");
        transactionA.setNewBalance(80f);
        transactionA.setType(TransactionType.TRANSACTION_PAYMENT);

        Transaction transactionB = new Transaction();

        transactionB.setAccountId(1L);
        transactionB.setSucceeded(false);
        transactionB.setAtmTransactionId(1);
        transactionB.setPending(true);
        transactionB.setDate(LocalDateTime.now().minusDays(2));
        transactionB.setOverdraftId(1);
        transactionB.setAmount(10f);
        transactionB.setId(2);
        transactionB.setMerchantName("Stubbed Another");
        transactionB.setNewBalance(90f);
        transactionB.setType(TransactionType.TRANSACTION_DEPOSIT);

        transactionList.add(transactionA);
        transactionList.add(transactionB);
    }

    @Test
    public void test_confirmPaymentTransaction(){
        when(transactionRepository.findPendingTransactionsByDate(any(LocalDateTime.class))).thenReturn(transactionList);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(stubbedAccount));
        paymentConfirmationService.confirmPaymentTransaction();
        assertThat(stubbedAccount.getBalance()).isEqualTo(90f);
        assertThat(transactionList).allMatch((t)->t.getPending()==false);
    }
}
