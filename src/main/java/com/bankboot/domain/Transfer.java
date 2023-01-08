package com.bankboot.domain;

import com.bankboot.component.DoubleSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class Transfer {
    Integer transferId;
    String account;
    String targetAccount;
    @JsonSerialize(using = DoubleSerialize.class)
    double balance;
    Integer transferType;
    Timestamp tradingTime;
    String machine;
}
