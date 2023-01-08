package com.bankboot.dao;

import com.bankboot.domain.Salesman;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

/**
 * 业务员实体类
 */
@Mapper
public interface SalesmanDao {
    /**
     * 业务员登录
     * @param salesman
     * @return 业务员工号jobNo
     */
    @Select("select jobNo from Salesman where jobNo = #{jobNo} and password = #{password}")
    String login(Salesman salesman) throws SQLException;

    /**
     * 验证指定salesman
     * @param jobNo
     * @return
     */
    @Select("select * from Salesman where jobNo = #{jobNo}")
    Salesman selectByJobNo(String jobNo) throws SQLException;
}
