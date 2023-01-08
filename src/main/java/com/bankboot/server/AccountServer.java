package com.bankboot.server;

import com.bankboot.domain.Account;
import com.bankboot.domain.Transact;
import com.bankboot.domain.Transfer;

import java.sql.SQLException;

public interface AccountServer {
    /**
     * 转账
     * @param transfer
     * @return 执行状态
     */
    boolean transfer(Transfer transfer) throws SQLException;

    /**
     * 登录
     * @param account
     * @return
     * @throws Exception
     */
    String login(Account account) throws Exception;

    /**
     * 获取余额
     * @param account
     * @return
     * @throws SQLException
     */
    double getBalance(String account) throws SQLException;

    /**
     * 存钱
     * @param transact
     */
    void saveMoney(Transact transact) throws SQLException;

    void catchMoney(Transact transact) throws SQLException;

    /**
     * 密码错误处理
     * @param account
     */
    boolean procError(String account) throws SQLException;
}
