package com.bankboot.dao;

import com.bankboot.domain.Operation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * 业务员操作记录实
 */
@Mapper
public interface OperationDao {
    /**
     * opType = 0;
     * 插入操作记录
     * @param operation
     * @return 影响行数
     */
    @Insert("insert into Operation(jobNo, machine, opType, balance, operatingTime) values(#{jobNo}, #{machine}, #{opType}, #{balance}, #{operationTime})")
    int insert(Operation operation) throws SQLException;

    /**
     * 查询单台机器操作记录
     * @param machine
     * @return 列表
     */
    @Select("")
    List<Operation> select(String machine) throws SQLException;
}
