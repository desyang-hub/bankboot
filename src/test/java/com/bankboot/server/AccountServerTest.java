package com.bankboot.server;

import com.bankboot.domain.Account;
import com.bankboot.domain.Transact;
import com.bankboot.domain.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class AccountServerTest {

    @Autowired
    AccountServer server;

    @Test
    void login() throws Exception {
        String account = server.login(new Account()
                .setAccount("10001")
                .setPassword("1234"));
        log.info(account);
    }

    @Test
    void transfer() throws SQLException {
        boolean status = server.transfer(new Transfer()
                .setAccount("10001")
                .setTargetAccount("10002")
                .setBalance(100)
                .setMachine("1"));
        log.info(String.valueOf(status));
    }

    @Test
    void getBalance() throws SQLException {
        double balance = server.getBalance("10001");
        log.info(balance + "");
    }

    @Test
    void saveMoney() throws SQLException {
        server.saveMoney(new Transact()
                .setAccount("10001")
                .setMachine("1")
                .setBalance(200));
    }
}
