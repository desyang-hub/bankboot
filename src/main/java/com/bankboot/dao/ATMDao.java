package com.bankboot.dao;

import com.bankboot.domain.ATM;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;

/**
 * ATM实体
 */
@Mapper
public interface ATMDao {
    /**
     * 查询机器信息
     * @param machine
     * @return
     * @throws SQLException
     */
    @Select("select * from ATM where machine = #{machine}")
    ATM selectOne(String machine) throws SQLException;

    /**
     * 查询atm余额
     * @return
     * @throws SQLException
     */
    @Select("select atmBalance from ATM where machine = #{machine}")
    Integer getBalance(String machine) throws SQLException;

    /**
     * 启动atm服务
     * @param atm
     * @return
     */
    @Select("select machine from ATM where machine=#{machine} and password=#{password}")
    String login(ATM atm) throws SQLException;

    /**
     * ATM机加钱操作
     * @param machine
     * @param balance
     * @return  行数
     * @throws SQLException
     */
    @Update("update ATM set atmBalance = atmBalance + #{balance} where machine = #{machine}")
    int inMoney(@Param("machine") String machine, @Param("balance") Integer balance) throws SQLException;

    /**
     * ATM机减钱操作
     * @param machine
     * @return 行数
     * @throws SQLException
     */
    @Update("update ATM set atmBalance = atmBalance - #{balance} where machine = #{machine}")
    int outMoney(@Param("machine") String machine, @Param("balance") double balance) throws SQLException;
}
