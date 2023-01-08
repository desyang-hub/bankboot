package com.bankboot.dao;

import com.bankboot.domain.BankUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BankUserDao {
    /**
     * 查询用户列表
     * @return List<BankUser>
     */
    @Select("select * from BankUser")
    List<BankUser>  selectAll() throws SQLException;

    /**
     * 查询单用户信息
     * @return BankUser实体
     */
    @Select("实现")
    BankUser selectOne(String account) throws SQLException;


}
