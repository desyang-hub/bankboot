package com.bankboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JWTUtilsTest {

    @Autowired
    JWTUtils utils;

    @Value("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEwMDAxIiwiZXhwIjoxNjcxNzAzMzczfQ.Y8cOWXqC8lTYmW1gUJ441A8WrfcNM-OQ0BnvFLPk5io")
    String token;

    @Test
    void getToken() {
        log.info(utils.getToken("1001"));
    }

    @Test
    void verify() {
        log.info(utils.verify(token) + "");
    }

    @Test
    void getId() {
        log.info(utils.getId(token));
    }

    @Test
    void verifyAccount() {
        boolean boo = utils.verifyAccount(token);
        log.info(boo + "");
    }

    @Test
    void verifySalesman() {
        boolean boo = utils.verifySalesman(token);
        log.info(boo + "");

    }
}
