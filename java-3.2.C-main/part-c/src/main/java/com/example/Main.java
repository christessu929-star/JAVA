package com.example;

import com.example.entity.Account;
import com.example.exception.InsufficientFundsException;
import com.example.repository.AccountRepository;
import com.example.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner demo(AccountRepository accountRepository, BankService bankService) {
        return args -> {
            Account a1 = new Account(null, "A100", new BigDecimal("1000.00"));
            Account a2 = new Account(null, "A200", new BigDecimal("200.00"));
            accountRepository.save(a1);
            accountRepository.save(a2);

            System.out.println("Before transfers:");
            accountRepository.findAll().forEach(System.out::println);

            // Successful transfer
            bankService.transfer(a1.getId(), a2.getId(), new BigDecimal("150.00"));
            System.out.println("After successful transfer:");
            accountRepository.findAll().forEach(System.out::println);

            // Demonstrate rollback: attempt to transfer more than available
            try {
                bankService.transfer(a2.getId(), a1.getId(), new BigDecimal("100000.00"));
            } catch (InsufficientFundsException ex) {
                System.out.println("Expected failure: " + ex.getMessage());
            }

            System.out.println("After failed transfer (should be unchanged):");
            accountRepository.findAll().forEach(System.out::println);
        };
    }
}