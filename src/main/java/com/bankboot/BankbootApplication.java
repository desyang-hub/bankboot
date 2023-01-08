package com.bankboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BankbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankbootApplication.class, args);
    }
}
