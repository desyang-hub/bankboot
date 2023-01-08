package com.bankboot.server;

import com.bankboot.domain.ATM;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@Slf4j
@SpringBootTest
public class ATMServerTest {

    @Autowired
    ATMServer server;

    @Test
    void login() throws SQLException {
        String machine = server.login(new ATM()
                .setMachine("1")
                .setPassword("1234"));
        log.info(machine);
    }
}
