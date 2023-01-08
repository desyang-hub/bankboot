package com.bankboot.dao;

import com.bankboot.domain.Transact;
import com.bankboot.domain.Transfer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * ATM交易记录实体
 */
@Mapper
public interface TransactDao {
    /**
     * 实现插入交易记录
     * @param transact
     * @return 影响行数
     */
    @Insert("insert into Transact(account, machine, tradType, balance, tradingTime) values(#{account}, #{machine}, #{tradType}, #{balance}, #{tradingTime})")
    int insert(Transact transact) throws SQLException;

    /**
     * 查询所有交易记录
     * @return 记录列表
     */
    @Select("select * from Transact")
    List<Transact> selectAll() throws SQLException;

    /**
     * 查询个人交易记录
     * @return 记录列表
     */
    @Select("select * from Transact where account = #{account}")
    List<Transact> selectOne(String account) throws SQLException;

    /**
     * 查询单台ATM机的交易记录
     * @param machine
     * @return 记录列表
     */
    @Select("select * from Transact where machine = #{machine}")
    List<Transact> selectMachine(String machine) throws SQLException;
}
