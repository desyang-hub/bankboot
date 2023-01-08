package com.bankboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Operation {
    Integer optionId;
    String jobNo;
    String machine;
    Integer opType;
    Integer balance;
    Timestamp operationTime;
}
