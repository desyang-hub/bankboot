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
public class Transact {
    Integer tradId; // 交易ID
    String account;
    String machine;
    Integer tradType;
    Integer balance;
    Timestamp tradingTime;
}
