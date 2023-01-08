package com.bankboot.server.impl;

import com.bankboot.component.TimestampTool;
import com.bankboot.dao.ATMDao;
import com.bankboot.dao.TransactDao;
import com.bankboot.dao.TransferDao;
import com.bankboot.domain.Account;
import com.bankboot.domain.Transact;
import com.bankboot.domain.Transfer;
import com.bankboot.server.AccountServer;
import com.bankboot.dao.AccountDao;
import com.bankboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class AccountServerImpl implements AccountServer {
    AccountDao dao;
    JWTUtils utils;
    TransferDao transferDao;
    TimestampTool tool;
    TransactDao transactDao;
    ATMDao atmDao;

    @Autowired
    public AccountServerImpl(AccountDao dao, JWTUtils utils, TransferDao transferDao, TimestampTool tool, TransactDao transactDao,
                             ATMDao atmDao) {
        this.dao = dao;
        this.utils = utils;
        this.transferDao = transferDao;
        this.tool = tool;
        this.transactDao = transactDao;
        this.atmDao = atmDao;
    }

    /**
     * 转账 transferType = 1
     * 开启事务
     * 面对业务过程可能发生故障，故障发生时调用rollback进行事务回退
     * @param transfer
     */
    @Override
    public boolean transfer(Transfer transfer) throws SQLException {
        dao.outMoney(transfer.getAccount(), transfer.getBalance());
        dao.inMoney(transfer.getTargetAccount(), transfer.getBalance());
        transferDao.insert(transfer.setTransferType(1)
                .setTradingTime(tool.setTime(System.currentTimeMillis())));
        return true;
    }

    @Override
    public boolean procError(String account) throws SQLException {
        dao.procError(account); // error + 1
        int error = dao.selectError(account);
        if(error == 3) {
            dao.freezing(account);
            dao.solveError(account);
            return true;
        }
        return false;
    }

    /**
     * 实现用户登录
     * @param user
     * @return token | null
     * @throws Exception
     */
    @Override
    public String login(Account user) throws Exception {
        String account = dao.login(user);
        if(account == null) return null;
        else {
            dao.solveError(account);
            return utils.getToken(account);
        }
    }

    @Override
    public double getBalance(String account) throws SQLException {

        double balance = dao.selectBalance(account);
        return balance;
    }

    /**
     * tradType = 2 存钱
     * @param transact
     * @throws SQLException
     */
    @Override
    public void saveMoney(Transact transact) throws SQLException {
        atmDao.inMoney(transact.getMachine(), transact.getBalance()); // atm加钱
        dao.inMoney(transact.getAccount(), transact.getBalance()); // 个人加钱
        transact.setTradingTime(tool.setTime(System.currentTimeMillis()))
                        .setTradType(2);
        transactDao.insert(transact); // 产生交易记录
    }

    /**
     * tradType = 3 存钱
     * @param transact
     * @throws SQLException
     */
    @Override
    public void catchMoney(Transact transact) throws SQLException {
        atmDao.outMoney(transact.getMachine(), transact.getBalance()); // atm减钱
        dao.outMoney(transact.getAccount(), transact.getBalance()); // 个人减钱
        transact.setTradingTime(tool.setTime(System.currentTimeMillis()))
                .setTradType(3);
        transactDao.insert(transact); // 产生交易记录
    }
}
