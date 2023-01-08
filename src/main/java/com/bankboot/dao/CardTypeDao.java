package com.bankboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

@Mapper
public interface CardTypeDao {
    /**
     * 通过银行卡类型查询额度
     * @param cardId
     * @return 额度
     */
    @Select("")
    Integer select(Integer cardId) throws SQLException;
}
