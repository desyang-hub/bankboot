package com.bankboot.server;

import com.bankboot.domain.ATM;

import java.sql.SQLException;

public interface ATMServer {
    /**
     * ATM启动服务
     * @param atm
     * @return
     */
    String login(ATM atm) throws SQLException;
}
