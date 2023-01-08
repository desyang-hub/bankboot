package com.bankboot.dao;

import com.bankboot.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface Procedure {

    @Select("select * from Account")
    List<Account> execProc() throws SQLException;


}
