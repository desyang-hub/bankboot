package com.bankboot.dao;

import com.bankboot.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class AccountDaoTest {
    @Autowired
    AccountDao dao;

    @Test
    void login() throws SQLException {
        log.info(dao.login(new Account()
                .setAccount("10003")
                .setPassword("1234")) + "");
    }

    @Test
    void selectAll() throws SQLException {
        dao.selectAll().forEach(System.out::println);
    }

    @Test
    void insert() throws SQLException {
        dao.insert(new Account()
                .setAccount("10009")
                .setBalance(20000)
                .setFreeze(0)
                .setPassword("1234")
                .setPhoneNumber("18779750679")
                .setUserId("1001"));
    }

    @Test
    void inMoney() throws SQLException {
        dao.inMoney("10001", 200);
    }

    @Test
    void outMoney() throws SQLException {
        dao.outMoney("10001", 200);
    }

    @Test
    void selectBalance() throws SQLException {
        double balance = dao.selectBalance("10001");
        log.info(String.format("balance: %f", balance));
    }

    @Test
    void getStatus() throws SQLException {
        Boolean status = dao.getStatus("1234");
        log.info(String.valueOf(status));
    }

    @Test
    void selectByAccount() throws SQLException {
        Account account = dao.selectByAccount("10000");
        if(account == null) {
            log.info("空对象");
        }
        else {
            log.info(account.toString());
        }
    }
}
