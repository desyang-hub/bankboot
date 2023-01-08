package com.bankboot.server.impl;

import com.bankboot.component.TimestampTool;
import com.bankboot.dao.*;
import com.bankboot.domain.*;
import com.bankboot.server.SalesmanServer;
import com.bankboot.utils.JWTUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class SalesmanServerImpl implements SalesmanServer {
    SalesmanDao salesmanDao;
    JWTUtils jwtUtils;
    ATMDao atmDao;
    OperationDao operationDao;
    TimestampTool tool;
    TransactDao transactDao;
    TransferDao transferDao;
    AccountDao accountDao;

    public SalesmanServerImpl(SalesmanDao salesmanDao, JWTUtils jwtUtils, ATMDao atmDao, OperationDao operationDao, TimestampTool tool,
                              TransactDao transactDao, TransferDao transferDao, AccountDao accountDao) {
        this.salesmanDao = salesmanDao;
        this.jwtUtils = jwtUtils;
        this.atmDao = atmDao;
        this.operationDao = operationDao;
        this.tool = tool;
        this.transactDao = transactDao;
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @Override
    public String login(Salesman salesman) throws SQLException {
        String jobNo = salesmanDao.login(salesman);
        if(jobNo == null) {
            return null;
        }
        else {
            return jwtUtils.getToken(jobNo);
        }
    }

    @Override
    public void inMoney(Operation operation) throws SQLException {
        atmDao.inMoney(operation.getMachine(), operation.getBalance()); // atm加钱
        operationDao.insert(operation
                .setOpType(0)
                .setOperationTime(tool.setTime(System.currentTimeMillis()))); // 产生记录
    }

    @Override
    public void outMoney(Operation operation) throws SQLException {
        atmDao.outMoney(operation.getMachine(), operation.getBalance()); // atm减钱
        operationDao.insert(operation
                .setOpType(1)
                .setOperationTime(tool.setTime(System.currentTimeMillis()))); // 产生记录
    }

    @Override
    public List<Transact> getAllTransact() throws SQLException {
        return transactDao.selectAll();
    }

    @Override
    public List<Transact> getOneTransact(String account) throws SQLException {
        return transactDao.selectOne(account);
    }

    @Override
    public List<Transfer> getAllTransfer() throws SQLException {
        return transferDao.selectAll();
    }

    @Override
    public List<Transfer> getOneTransfer(String account) throws SQLException {
        return transferDao.select(account);
    }

    @Override
    public List<Account> getAllAccount() throws SQLException {
        return accountDao.selectAll();
    }

    @Override
    public Account getAccount(String account) throws SQLException {
        return accountDao.selectByAccount(account);
    }

    @Override
    public List<Account> getUserAccount(String userId) throws SQLException {
        return accountDao.selectByUserId(userId);
    }

    @Override
    public void freezeAccount(String account) throws SQLException {
        accountDao.freezing(account);
    }
}
