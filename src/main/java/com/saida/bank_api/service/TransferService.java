package com.saida.bank_api.service;

import com.saida.bank_api.dto.request.TransferRequest;
import com.saida.bank_api.entity.Account;
import com.saida.bank_api.entity.Transaction;
import com.saida.bank_api.entity.TransactionType;
import com.saida.bank_api.repository.AccountRepository;
import com.saida.bank_api.repository.TransactionRepository;
import com.saida.bank_api.exception.AccountNotFoundException;
import com.saida.bank_api.exception.InsufficientFundsException;
import com.saida.bank_api.exception.SameAccountTransferException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferService {
        private final AccountRepository accountRepository;
        private final TransactionRepository transactionRepository;

        @Transactional
        public void transfer(TransferRequest request) {
                if (request.getFromAccountId().equals(request.getToAccountId())) {
                        throw new SameAccountTransferException("Cannot transfer to the same account.");
                }

                Account fromAccount = accountRepository.findById(request.getFromAccountId())
                                .orElseThrow(() -> new AccountNotFoundException(
                                                "Source account not found with id: " + request.getFromAccountId()));

                Account toAccount = accountRepository.findById(request.getToAccountId())
                                .orElseThrow(() -> new AccountNotFoundException(
                                                "Destination account not found with id: " + request.getToAccountId()));

                if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
                        throw new InsufficientFundsException(
                                        "Insufficient funds. Available: " + fromAccount.getBalance()
                                                        + ", Requested: " + request.getAmount());
                }

                fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
                toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

                accountRepository.save(fromAccount);
                accountRepository.save(toAccount);

                Transaction debitRecord = Transaction.builder()
                                .account(fromAccount)
                                .transactionType(TransactionType.TRANSFER)
                                .amount(request.getAmount())
                                .description(request.getDescription())
                                .build();
                Transaction creditRecord = Transaction.builder()
                                .account(toAccount)
                                .transactionType(TransactionType.TRANSFER)
                                .amount(request.getAmount())
                                .description(request.getDescription())
                                .build();

                transactionRepository.save(debitRecord);
                transactionRepository.save(creditRecord);
        }
}
