package com.bankboot.server;

import com.bankboot.domain.Account;
import com.bankboot.domain.Operation;
import com.bankboot.domain.Salesman;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class SalesmanServerTest {
    @Autowired
    SalesmanServer server;

    @Test
    void login() throws SQLException {
        String jobNo = server.login(new Salesman()
                .setJobNo("10001")
                .setPassword("1234"));
        log.info(jobNo + "");
    }

    @Test
    void inMoney() throws SQLException {
        server.inMoney(new Operation()
                .setMachine("1")
                .setJobNo("10001")
                .setBalance(200));
    }

    @Test
    void outMoney() throws SQLException {
        server.outMoney(new Operation()
                .setMachine("1")
                .setJobNo("10001")
                .setBalance(200));
    }

    @Test
    void getAllTransact() throws SQLException {
        server.getAllTransact().forEach(System.out::println);
    }

    @Test
    void getOneTransact() throws SQLException {
        server.getOneTransact("10001").forEach(System.out::println);
    }

    @Test
    void getAllTransfer() throws SQLException {
        server.getAllTransfer().forEach(System.out::println);
    }

    @Test
    void getOneTransfer() throws SQLException {
        server.getOneTransfer("10003").forEach(System.out::println);
    }

    @Test
    void getAllAccount() throws SQLException {
        server.getAllAccount().forEach(System.out::println);
    }

    @Test
    void getAccount() throws SQLException {
        Account account = server.getAccount("10001");
        log.info(account.toString());
    }

    @Test
    void getUserAccount() throws SQLException {
        server.getUserAccount("1002").forEach(System.out::println);
    }

    @Test
    void freezeAccount() throws SQLException {
        server.freezeAccount("10001");
    }
}
