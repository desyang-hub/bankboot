package com.bankboot.dao;

import com.bankboot.domain.ATM;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class ATMDaoTest {
    @Autowired
    ATMDao dao;

    @Test
    void selectOne() throws SQLException {
        ATM atm = dao.selectOne("1");
        log.info(atm.toString());
    }

    @Test
    void getBalance() throws SQLException {
        Integer balance = dao.getBalance("1");
        log.info(balance + "");
    }

    @Test
    void login() throws SQLException {
        String machine = dao.login(new ATM()
                .setMachine("1")
                .setPassword("1234"));
        log.info(machine);
    }

    @Test
    void inMoney() throws SQLException{
        dao.inMoney("1",100);
    }

    @Test
    void outMoney() throws SQLException{
        dao.outMoney("2",100);
    }
}
