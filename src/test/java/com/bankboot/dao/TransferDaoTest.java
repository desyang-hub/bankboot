package com.bankboot.dao;

import com.bankboot.domain.Transfer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
@SpringBootTest
public class TransferDaoTest {

    @Autowired
    TransferDao dao;

    @Test
    void insert() throws SQLException {
        dao.insert(new Transfer()
                .setAccount("10001")
                .setTargetAccount("10002")
                .setBalance(100)
                .setTransferType(1)
                .setTradingTime(new Timestamp(System.currentTimeMillis())));
    }
}
