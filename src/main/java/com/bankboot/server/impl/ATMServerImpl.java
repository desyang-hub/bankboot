package com.bankboot.server.impl;

import com.bankboot.dao.ATMDao;
import com.bankboot.domain.ATM;
import com.bankboot.server.ATMServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ATMServerImpl implements ATMServer {

    ATMDao atmDao;

    @Autowired
    public ATMServerImpl(ATMDao atmDao) {
        this.atmDao = atmDao;
    }

    @Override
    public String login(ATM atm) throws SQLException {

        String machine = atmDao.login(atm);
        return machine;
    }
}
