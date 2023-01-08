package com.bankboot.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class TransactDaoTest {
    @Autowired
    TransactDao transactDao;

    @Test
    void selectAll() throws SQLException {
        transactDao.selectAll().forEach(System.out::println);
    }

    @Test
    void selectOne() throws SQLException {
        transactDao.selectOne("10001").forEach(System.out::println);
    }
}
