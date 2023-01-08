package com.bankboot.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class BankUserDaoTest {

    @Autowired
    BankUserDao bankUserDao;

    @Test
    void selectAll() throws SQLException {
        bankUserDao.selectAll().forEach(System.out::println);
    }
}
