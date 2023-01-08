package com.bankboot.server;

import com.bankboot.domain.*;

import java.sql.SQLException;
import java.util.List;

public interface SalesmanServer {
    /**
     * 业务员登录
     * @param salesman
     * @throws SQLException
     */
    String login(Salesman salesman) throws SQLException;

    /**
     * opType = 0
     * 加钞
     * @param operation
     * @throws SQLException
     */
    void inMoney(Operation operation) throws SQLException;

    /**
     * opType = 1
     * 减钞
     * @param operation
     * @throws SQLException
     */
    void outMoney(Operation operation) throws SQLException;

    /**
     * 查询所有交易记录
     * @return
     * @throws SQLException
     */
    List<Transact> getAllTransact() throws SQLException;

    /**
     * 查询个人交易记录
     * @return
     * @throws SQLException
     */
    List<Transact> getOneTransact(String account) throws SQLException;

    /**
     * 获取所有转账记录
     * @return
     * @throws SQLException
     */
    List<Transfer> getAllTransfer() throws SQLException;

    /**
     * 获取个人转账记录
     * @return
     * @throws SQLException
     */
    List<Transfer> getOneTransfer(String account) throws  SQLException;

    /**
     * 获取所有账户信息
     * @return
     * @throws SQLException
     */
    List<Account> getAllAccount() throws SQLException;

    /**
     * 获取储户的账户信息
     * @param account
     * @return
     * @throws SQLException
     */
    Account getAccount(String account) throws SQLException;

    /**
     * 获取用户账户信息
     * @param userId
     * @return
     * @throws SQLException
     */
    List<Account> getUserAccount(String userId) throws SQLException;

    /**
     * 冻结或解冻账户
     * @param account
     * @throws SQLException
     */
    void freezeAccount(String account) throws SQLException;

}
