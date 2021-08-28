package org.ssor.boss.core.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.ssor.boss.core.transfer.TransactionTransfer;

import java.util.List;

// TODO: move this to transfer.
@RequiredArgsConstructor
@Getter
public class TransactionListTransfer
{

  private final List<TransactionTransfer> transactions;
  private final Integer page;
  private final Integer pages;
  private final Integer limit;

}
