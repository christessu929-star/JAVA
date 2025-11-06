package com.example.service;

import com.example.entity.Account;
import com.example.entity.TransactionRecord;
import com.example.exception.InsufficientFundsException;
import com.example.repository.AccountRepository;
import com.example.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account from = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("From account not found: " + fromAccountId));

        Account to = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("To account not found: " + toAccountId));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in account " + from.getAccountNumber());
        }

        // perform transfer
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        TransactionRecord tr = new TransactionRecord(from.getId(), to.getId(), amount);
        transactionRepository.save(tr);
    }
}
