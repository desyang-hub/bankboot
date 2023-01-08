package com.bankboot.dao;

import com.bankboot.component.TimestampTool;
import com.bankboot.domain.Operation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class OperationDaoTest {
    @Autowired
    OperationDao operationDao;

    @Autowired
    TimestampTool tool;

    @Test
    void insert() throws SQLException {
        operationDao.insert(new Operation()
                .setOperationTime(tool.setTime(System.currentTimeMillis()))
                .setBalance(200)
                .setMachine("1")
                .setJobNo("10001")
                .setOpType(0));
    }
}
