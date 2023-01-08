package com.bankboot.dao;

import com.bankboot.domain.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * 转账记录实体
 */
@Mapper
public interface TransferDao {
    /**
     * *插入转账记录
     * @param transfer
     * @return
     * @throws SQLException
     */
    @Insert("insert into transfer(account, targetAccount, balance, transferType, tradingTime, machine) values(#{account}, #{targetAccount}, #{balance}, #{transferType}, #{tradingTime}, #{machine})")
    int insert(Transfer transfer) throws SQLException;

    /**
     * 查询所有转账记录
     * @return List<Transfer>
     */
    @Select("select * from Transfer")
    List<Transfer> selectAll() throws SQLException;

    /**
     * 查询所有个人转账记录
     * @return List<Transfer>
     */
    @Select("select * from Transfer where account = #{account}")
    List<Transfer> select(String account) throws SQLException;

}
