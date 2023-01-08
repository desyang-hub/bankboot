package com.bankboot.dao;

import com.bankboot.domain.Account;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 储户实体
 */
@Mapper
public interface AccountDao {
    /**
     * *实现用户登录
     * @param user
     * @return 账户 | null
     * @throws SQLException
     */
    @Select("select account from Account where account = #{account} and password = #{password}")
    String login(Account user) throws SQLException;

    /**
     * *查询账户列表
     * @return
     */
    @Select("select * from Account")
    List<Account> selectAll() throws SQLException;

    /**
     * 查询单个账户信息
     * @param account
     * @return
     * @throws SQLException
     */
    @Select("select * from Account where account = #{account}")
    Account selectByAccount(String account) throws SQLException;

    /**
     * 查询单用户账户信息
     * @param userId
     * @return
     * @throws SQLException
     */
    @Select("select * from Account where userId = #{userId}")
    List<Account> selectByUserId(String userId) throws SQLException;


    /**
     * *新建账户
     * @param acc
     * @return
     */
    @Insert("insert into account(account, userid, password, balance, phoneNumber, freeze) " +
            "values(#{account}, #{userId}, #{password}, #{balance}, #{phoneNumber}, #{freeze})")
    int insert(Account acc) throws SQLException;

    /**
     * *账户金额减少
     * @param account
     * @param balance
     * @return
     */
    @Update("update Account set balance = balance - #{balance} where account=#{account}")
    int outMoney(@Param("account") String account, @Param("balance") double balance) throws SQLException;

    /**
     * *账户金额增加
     * @param account
     * @param balance
     * @return
     */
    @Update("update Account set balance = balance + #{balance} where account=#{account}")
    int inMoney(@Param("account") String account,@Param("balance") double balance) throws SQLException;

    /**
     * *查询账户余额
     * @param account
     * @return
     */
    @Select("select balance from Account where account = #{account}")
    double selectBalance(String account) throws SQLException;

    /**
     * 对指定账户实施冻结 或解冻 进行异或运算 即 freeze = (freeze ^ 1)
     * @param account
     * @return
     */
    @Update("update Account set freeze = (freeze ^ 1) where account = #{account}")
    int freezing(String account) throws SQLException;

    /**
     * 查询账户状态
     * @param account
     * @return
     * @throws SQLException
     */
    @Select("select freeze from Account where account = #{account}")
    Boolean getStatus(String account) throws SQLException;

    @Update("update Account set error = error + 1 where account = #{account}")
    int procError(String account) throws SQLException;

    @Update("update Account set error = 0 where account = #{account}")
    int solveError(String account) throws SQLException ;

    @Select("select error from Account where account = #{account}")
    int selectError(String account) throws SQLException;
}
