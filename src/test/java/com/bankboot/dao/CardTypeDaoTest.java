package com.bankboot.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CardTypeDaoTest {
    @Autowired
    CardTypeDao cardTypeDao;

    @Test
    void test() {
        log.info("test...");
    }
}
