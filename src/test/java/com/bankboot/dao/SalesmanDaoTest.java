package com.bankboot.dao;

import com.bankboot.domain.Salesman;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class SalesmanDaoTest {

    @Autowired
    SalesmanDao salesmanDao;
    @Test
    void login() throws SQLException {
        String jobNo = salesmanDao.login(new Salesman()
                .setJobNo("11001")
                .setPassword("1234"));
        log.info(jobNo);
    }

    @Test
    void selectByJobSno() throws SQLException {
        Salesman salesman = salesmanDao.selectByJobNo("10001");
        if(salesman == null) {
            log.info("null");
        }
        else {
            log.info(salesman.toString());
        }
    }
}
